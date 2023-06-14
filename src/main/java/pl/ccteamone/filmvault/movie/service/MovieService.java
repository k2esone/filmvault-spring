package pl.ccteamone.filmvault.movie.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.movie.repository.MovieRepository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieApiService movieApiService;
    private final MovieMapper movieMapper;


    public MovieDto createMovie(MovieDto create) {
        Movie movieFromDto = movieMapper.mapToMovie(create);
        Movie movie = Movie.builder()
                .title(movieFromDto.getTitle())
                .posterPath(movieFromDto.getPosterPath())
                .overview(movieFromDto.getOverview())
                .releaseDate(movieFromDto.getReleaseDate())
                .runtime(movieFromDto.getRuntime())
                .credits(movieFromDto.getCredits())
                .rating(movieFromDto.getRating())
                .apiID(movieFromDto.getApiID())
                .vodPlatforms(movieFromDto.getVodPlatforms())
                .region(movieFromDto.getRegion())
                .build();
        movieRepository.save(movie);
        return movieMapper.mapToMovieDto(movie);
    }

    public List<MovieDto> getMovieList() {
        return movieRepository.findAll().stream().map(movieMapper::mapToMovieDto).collect(Collectors.toList());
    }

    public MovieDto getMovieById(Long movieId) {
        return movieMapper.mapToMovieDto(movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie id=" + movieId + " not found")));
    }

    public MovieDto updateMovie(Long movieId, MovieDto update) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie id=" + movieId + " not found"));

        Movie movieUpdateFromDto = movieMapper.mapToMovie(update);

        if (movieUpdateFromDto.getTitle() != null) {
            movie.setTitle(movieUpdateFromDto.getTitle());
        }
        if (movieUpdateFromDto.getPosterPath() != null) {
            movie.setPosterPath(movieUpdateFromDto.getPosterPath());
        }
        if (movieUpdateFromDto.getOverview() != null) {
            movie.setOverview(movieUpdateFromDto.getOverview());
        }
        if (movieUpdateFromDto.getReleaseDate() != null) {
            movie.setReleaseDate(movieUpdateFromDto.getReleaseDate());
        }
        if (movieUpdateFromDto.getRuntime() != null) {
            movie.setRuntime(movieUpdateFromDto.getRuntime());
        }
        if (movieUpdateFromDto.getCredits() != null) {
            movie.setCredits(movieUpdateFromDto.getCredits());
        }
        if (movieUpdateFromDto.getRating() != null) {
            movie.setRating(movieUpdateFromDto.getRating());
        }
        if (movieUpdateFromDto.getApiID() != null) {
            movie.setApiID(movieUpdateFromDto.getApiID());
        }
        if (movieUpdateFromDto.getVodPlatforms() != null) {
            movie.setVodPlatforms(movieUpdateFromDto.getVodPlatforms());
        }
        if (movieUpdateFromDto.getRegion() != null) {
            movie.setRegion(movieUpdateFromDto.getRegion());
        }
        return movieMapper.mapToMovieDto(movieRepository.save(movie));
    }

    public void deleteMovieById(Long movieId) {
        try {
            movieRepository.deleteById(movieId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Movie not found with id: " + movieId);
        }
    }

    public boolean existsByApiID(Long id) {
        return movieRepository.existsByApiID(id);
    }

    public MovieDto findMovieByApiID(Long id) {
        return movieMapper.mapToMovieDto(movieRepository.findByApiID(id)
                .orElseThrow(() -> new RuntimeException("Movie not found")));
    }

    // SEARCH ->

    public List<MovieDto> searchMovies(String query) {
        getApiMovieBatchBySearch(query);

        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(query.substring(0, 1));
        List<MovieDto> similarMovies = new ArrayList<>();

        for (Movie movie : movies) {
            String title = movie.getTitle().toLowerCase();
            String lowercaseQuery = query.toLowerCase();

            List<String> titleNGrams = generateNGrams(title, 2); // licznik prawdopodobieństwa dla title
            List<String> queryNGrams = generateNGrams(lowercaseQuery, 2);  // licznik prawdopodobieństwa dla query

            int commonNGrams = countCommonNGrams(titleNGrams, queryNGrams);

            if (commonNGrams >= 2) { // <-- Licznik prawdopodobieństwa
                similarMovies.add(movies.stream()
                        .filter(match -> match.getTitle().equalsIgnoreCase(movie.getTitle()))
                        .findFirst()
                        .map(movieMapper::mapToMovieDto)
                        .orElseThrow(() -> new RuntimeException("Unable to match movie by title")));
            }

            if (similarMovies.size() == 5) {
                break;
            }
        }

        return similarMovies;
    }

    private List<String> generateNGrams(String input, int n) {
        List<String> nGrams = new ArrayList<>();

        for (int i = 0; i <= input.length() - n; i++) {
            String nGram = input.substring(i, i + n);
            nGrams.add(nGram);
        }

        return nGrams;
    }

    private int countCommonNGrams(List<String> nGrams1, List<String> nGrams2) {
        Set<String> set1 = new HashSet<>(nGrams1);
        Set<String> set2 = new HashSet<>(nGrams2);

        set1.retainAll(set2);

        return set1.size();
    }

    // <- SEARCH

    public void getApiMoviePageBySearch(Long id, String phrase) {

    }

    public List<MovieDto> getDiscoverMovieList(Integer page) {
        if (page == null || page == 0) {
            page = 1;
        }
        List<MovieDto> movies = movieApiService.getMovieDiscoverList(page);
        return persistMovieDtoList(movies);
    }

    private void getApiMovieBatchBySearch(String phrase) {
        // default 5 pages
        List<MovieDto> movieBatch = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            movieBatch.addAll(movieApiService.getMovieSearchList(i, phrase));
        }
        persistMovieDtoList(movieBatch);
    }

    private List<MovieDto> persistMovieDtoList(List<MovieDto> movies) {
        movies = movies.stream()
                .filter(movieDto -> !existsByApiID(movieDto.getApiID()))
                .toList().stream()
                .map(this::createMovie).toList();

       /* for (MovieDto movie : movies) {
            createMovie(movie);
        }*/
        return movies;
    }

}
