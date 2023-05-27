package pl.ccteamone.filmvault.movie.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.movie.repository.MovieRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    public MovieDto createMovie(MovieDto create) {

        Movie movie = Movie.builder()
                .title(create.getTitle())
                .posterPath(create.getPosterPath())
                .overview(create.getOverview())
                .releaseDate(create.getReleaseDate())
                .runtime(create.getRuntime())
                .credits(create.getCredits())
                .rating(create.getRating())
                .build();
        movieRepository.save(movie);
        return movieMapper.mapToMovieDto(movie);
    }

    public List<MovieDto> getMovieList() {
        return movieRepository.findAll().stream().map(movieMapper::mapToMovieDto).collect(Collectors.toList());
    }

    public MovieDto getMovieById(Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movieMapper.mapToMovieDto(movie.orElseThrow(() -> new RuntimeException("Movie id=" + movieId + " not found")));
    }


    public void updateMovie(Long movieId, MovieDto update) {

        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie id=" + movieId + " not found"));

        movie.setTitle(update.getTitle());
        movie.setPosterPath(update.getPosterPath());
        movie.setOverview(update.getOverview());
        movie.setReleaseDate(update.getReleaseDate());
        movie.setRuntime(update.getRuntime());
        movie.setCredits(update.getCredits());
        movie.setRating(update.getRating());

        movieRepository.save(movie);
//      return MovieMapper.toMovieDto(movie);
    }

    public void deleteMovieById(Long movieId) {
        try {
            movieRepository.deleteById(movieId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Movie not found with id: " + movieId);
        }
    }
}
