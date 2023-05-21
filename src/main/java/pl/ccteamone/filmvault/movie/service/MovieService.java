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


    public MovieDto createMovie(MovieDto create) {

        Movie movie = Movie.builder()
                .title(create.getTitle())
                .posterPath(create.getPosterPath())
                .overview(create.getOverview())
                .vodProviders(create.getVodProviders())
                .releaseDate(create.getReleaseDate())
                .runtime(create.getRuntime())
                .credits(create.getCredits())
                .rating(create.getRating())
                .build();
        movieRepository.save(movie);
        return MovieMapper.toMovieDto(movie);
    }

    public List<MovieDto> getMovieList() {
        return movieRepository.findAll().stream().map(MovieMapper::toMovieDto).collect(Collectors.toList());
    }

    public MovieDto getMovieById(UUID movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        return MovieMapper.toMovieDto(movie.orElseThrow(() -> new RuntimeException("Movie id=" + movieId + " not found")));
    }


    public MovieDto updateMovie(UUID movieId, MovieDto update) {
        Movie movie = Movie.builder()
                .title(update.getTitle())
                .posterPath(update.getPosterPath())
                .overview(update.getOverview())
                .vodProviders(update.getVodProviders())
                .releaseDate(update.getReleaseDate())
                .runtime(update.getRuntime())
                .credits(update.getCredits())
                .rating(update.getRating())
                .build();
        movieRepository.save(movie);
        return MovieMapper.toMovieDto(movie);
    }

    public void deleteMovieById(UUID movieId) {
        try {
            movieRepository.deleteById(movieId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Movie not found with id: " + movieId);
        }
    }
}
