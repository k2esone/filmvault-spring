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
        return movieMapper.mapToMovieDto(movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie id=" + movieId + " not found")));
    }


    public MovieDto updateMovie(Long movieId, MovieDto update) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie id=" + movieId + " not found"));
        if(update.getTitle() != null) {
            movie.setTitle(update.getTitle());
        }
        if(update.getPosterPath() != null) {
            movie.setPosterPath(update.getPosterPath());
        }
        if(update.getOverview() != null) {
            movie.setOverview(update.getOverview());
        }
        if(update.getReleaseDate() != null) {
            movie.setReleaseDate(update.getReleaseDate());
        }
        if(update.getRuntime() != null) {
            movie.setRuntime(update.getRuntime());
        }
        if(update.getCredits() != null) {
            movie.setCredits(update.getCredits());
        }
        if(update.getRating() != 0) {
            movie.setRating(update.getRating());
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
}
