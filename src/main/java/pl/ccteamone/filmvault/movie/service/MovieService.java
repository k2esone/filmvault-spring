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


import java.text.DecimalFormat;
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
        return movieMapper.mapToMovieDto(movieRepository.save(movieFromDto));
    }

    public List<MovieDto> getFullMovieList() {
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

        if (movieUpdateFromDto.getRating() != null) {
            movie.setRating(movieUpdateFromDto.getRating());
        }
        if (movieUpdateFromDto.getVoteCount() != null) {
            movie.setVoteCount(movieUpdateFromDto.getVoteCount());
        }
        if (movieUpdateFromDto.getApiID() != null) {
            movie.setApiID(movieUpdateFromDto.getApiID());
        }
        if (movieUpdateFromDto.getVodPlatforms() != null) {
            movie.setVodPlatforms(movieUpdateFromDto.getVodPlatforms());
        }
        if (movieUpdateFromDto.getRegions() != null) {
            movie.setRegions(movieUpdateFromDto.getRegions());
        }
        if (movieUpdateFromDto.getGenres() != null) {
            movie.setGenres(movieUpdateFromDto.getGenres());
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

    public List<MovieDto> findMoviePredictions(String query) {
        getApiMovieBatchByPhrase(query);

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

            if (similarMovies.size() == 20) {
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

    public void getMovie(Integer page, Integer size, String phrase, String releaseDate, Integer runtime, Double rating) {

    }

    public List<MovieDto> getDiscoverMovieList(Integer page) {
/*        if (page == null || page == 0) {
            page = 1;
        }*/
        List<MovieDto> movies = movieApiService.getMovieDiscoverList(page);
        return persistMovieDtoList(movies);
    }

    private void getApiMovieBatchByPhrase(String phrase) {
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

    public MovieDto addRating(Long movieId, int rating) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + movieId));

        try {
            if (1 <= rating && rating <= 10) {
                if (movie.getRating() == 0.0 || movie.getVoteCount() == 0) {
                    movie.setRating((double) rating);
                    movie.setVoteCount(1);
                    return movieMapper.mapToMovieDto(movieRepository.save(movie));
                }
                DecimalFormat df = new DecimalFormat("#.##");
                double v = movie.getRating() * movie.getVoteCount(); // sum of votes
                double newRatingCount = v + rating;
                int newVoteCount = movie.getVoteCount() + 1;
                double newRating = newRatingCount / newVoteCount;
                String formattedNumber = df.format(newRating);
                movie.setRating(df.parse(formattedNumber).doubleValue());
                movie.setVoteCount(newVoteCount);
            } else {
                throw new Exception("wrong rating given");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return movieMapper.mapToMovieDto(movieRepository.save(movie));
    }
}
