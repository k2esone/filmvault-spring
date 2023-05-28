package pl.ccteamone.filmvault.movie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.movie.dto.CreateMovieRequest;
import pl.ccteamone.filmvault.movie.dto.MovieResponse;
import pl.ccteamone.filmvault.movie.dto.UpdateMovieRequest;
import pl.ccteamone.filmvault.movie.dto.UpdateMovieResponse;
import pl.ccteamone.filmvault.movie.service.MovieService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping()
    public MovieResponse createMovie(@RequestBody CreateMovieRequest request) {
        log.info("movie addition has been triggered: {}", request);
        return movieService.createMovie(request);
    }

    @GetMapping()
    public List<MovieResponse> getMovieList() {
        log.info("someone asked for a movie list");
        return movieService.getMovieList();
    }
    @GetMapping("/{movieId}")
    public MovieResponse getUserById(@PathVariable UUID movieId) {
        log.info("someone asked for a movie with id - {}", movieId);
        return movieService.getMovieById(movieId);
    }

    @PatchMapping("/{movieId}")
    public UpdateMovieResponse updateUser (@PathVariable UUID movieId, @RequestBody UpdateMovieRequest request) {
        log.info("movie update with id - {} has been triggered, data: {}", movieId, request);
        return movieService.updateMovie(movieId, request);

    }

    @DeleteMapping("/{movieId}")
    public void deleteUserById(UUID movieId) {
        log.info("someone ask to delete movie with id - {}", movieId);
        movieService.deleteMovieById(movieId);
    }
}
