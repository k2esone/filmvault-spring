package pl.ccteamone.filmvault.movie.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.CreateMovieRequest;
import pl.ccteamone.filmvault.movie.dto.MovieResponse;
import pl.ccteamone.filmvault.movie.dto.UpdateMovieResponse;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.movie.repository.MovieRepository;
import pl.ccteamone.filmvault.user.MyUser;
import pl.ccteamone.filmvault.user.dto.CreateUserRequest;
import pl.ccteamone.filmvault.user.dto.UpdateUserResponse;
import pl.ccteamone.filmvault.user.dto.UserResponse;
import pl.ccteamone.filmvault.user.mapper.UserMapper;


import java.util.List;
import java.util.UUID;


@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public MovieResponse createMovie(CreateMovieRequest request) {
        Movie movie = Movie.builder()
                .title(request.getTitleR())
                .posterPath(request.getPosterPathR())
                .overview(request.getOverviewR())
                .releaseDate(request.getReleaseDateR())
                .runtime(request.getRuntimeR())
                .credits(request.getCreditsR())
                .rating(request.getRatingR())
                .myUsers(request.getMyUsers())
                .vodPlatforms(request.getVodPlatforms())
                .build();

        movieRepository.save(movie);

        return MovieMapper.mapMovieToMovieResponse(movie);
    }

    public List<MovieResponse> getMovieList() {
        return movieRepository.findAll()
                .stream().map(MovieMapper::mapMovieToMovieResponse)
                .toList();
    }

    public MovieResponse getMovieById(UUID movieId) {
        return movieRepository.findById(movieId)
                .stream().map(MovieMapper::mapMovieToMovieResponse)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Movie not found, id: " + movieId));
    }


    public UpdateMovieResponse updateMovie(UUID movieId, CreateMovieRequest request) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found, id: " + movieId));
        movie.setTitle(request.getTitleR());
        movie.setPosterPath(request.getPosterPathR());
        movie.setOverview(request.getOverviewR());
        movie.setReleaseDate(request.getReleaseDateR());
        movie.setRuntime(request.getRuntimeR());
        movie.setCredits(request.getCreditsR());
        movie.setRating(request.getRatingR());
        movie.setMyUsers(request.getMyUsers());
        movie.setVodPlatforms(request.getVodPlatforms());

        movie = movieRepository.save(movie);

        return MovieMapper.movieToMovieResponse(movie);
    }

    public void deleteMovieById(UUID movieId) {
        try {
            movieRepository.deleteById(movieId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Movie not found, id: " + movieId);
        }
    }

}