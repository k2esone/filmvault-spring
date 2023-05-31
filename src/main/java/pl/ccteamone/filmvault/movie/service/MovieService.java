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

        Movie movie1 = movieMapper.mapToMovie(update);

        if(movie1.getTitle() != null) {
            movie.setTitle(movie1.getTitle());
        }
        if(movie1.getPosterPath() != null) {
            movie.setPosterPath(movie1.getPosterPath());
        }
        if(movie1.getOverview() != null) {
            movie.setOverview(movie1.getOverview());
        }
        if(movie1.getReleaseDate() != null) {
            movie.setReleaseDate(movie1.getReleaseDate());
        }
        if(movie1.getRuntime() != null) {
            movie.setRuntime(movie1.getRuntime());
        }
        if(movie1.getCredits() != null) {
            movie.setCredits(movie1.getCredits());
        }
        if(movie1.getRating() != null) {
            movie.setRating(movie1.getRating());
        }
        if (movie1.getAppUsers() != null) {
            movie.setAppUsers(movie1.getAppUsers());
        }
        if (movie1.getVodPlatforms() != null) {
            movie.setVodPlatforms(movie1.getVodPlatforms());
        }
        if (movie1.getRegion() != null) {
            movie.setRegion(movie1.getRegion());
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
