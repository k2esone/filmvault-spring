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
        Movie movieFromDto = movieMapper.mapToMovie(create);
        Movie movie = Movie.builder()
                .title(movieFromDto.getTitle())
                .posterPath(movieFromDto.getPosterPath())
                .overview(movieFromDto.getOverview())
                .releaseDate(movieFromDto.getReleaseDate())
                .runtime(movieFromDto.getRuntime())
                .credits(movieFromDto.getCredits())
                .rating(movieFromDto.getRating())
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

        if(movieUpdateFromDto.getTitle() != null) {
            movie.setTitle(movieUpdateFromDto.getTitle());
        }
        if(movieUpdateFromDto.getPosterPath() != null) {
            movie.setPosterPath(movieUpdateFromDto.getPosterPath());
        }
        if(movieUpdateFromDto.getOverview() != null) {
            movie.setOverview(movieUpdateFromDto.getOverview());
        }
        if(movieUpdateFromDto.getReleaseDate() != null) {
            movie.setReleaseDate(movieUpdateFromDto.getReleaseDate());
        }
        if(movieUpdateFromDto.getRuntime() != null) {
            movie.setRuntime(movieUpdateFromDto.getRuntime());
        }
        if(movieUpdateFromDto.getCredits() != null) {
            movie.setCredits(movieUpdateFromDto.getCredits());
        }
        if(movieUpdateFromDto.getRating() != null) {
            movie.setRating(movieUpdateFromDto.getRating());
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
}
