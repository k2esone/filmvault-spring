package pl.ccteamone.filmvault.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.service.MovieService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/add")
    public MovieDto createMovie(@RequestBody MovieDto create) {
        log.info("adding new movie: {}", create);
        return movieService.createMovie(create);
    }

    @GetMapping
    public List<MovieDto> getMovieList() {
        log.info("request for movies list");
        return movieService.getMovieList();
    }

    @GetMapping("/{movieId}")
    public MovieDto getMovieById(@PathVariable Long movieId) {
        log.info("request for movie id - {}", movieId);
        return movieService.getMovieById(movieId);
    }

    @PatchMapping("/{movieId}")
    public MovieDto updateMovie(@PathVariable Long movieId, @RequestBody MovieDto update) {
        log.info("update movie with id - {}, data: {}", movieId, update);
        return movieService.updateMovie(movieId, update);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovieById(@PathVariable Long movieId) {
        log.info("delete movie with id - {}", movieId);
        movieService.deleteMovieById(movieId);
    }
}
