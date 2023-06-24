package pl.ccteamone.filmvault.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.movie.dto.CreditDto;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.service.MovieService;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/{movieId}/credits")
    public CreditDto getCreditsByMovieApiID(@PathVariable Long movieId) {
        return movieService.getCreditsByApiID(movieId);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/{movieId}")
    public MovieDto getMovieById(@PathVariable Long movieId) {
        log.info("request for movie id - {}", movieId);
        return movieService.getMovieById(movieId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{movieId}")
    public MovieDto updateMovie(@PathVariable Long movieId, @RequestBody MovieDto update) {
        log.info("update movie with id - {}, data: {}", movieId, update);
        return movieService.updateMovie(movieId, update);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{movieId}")
    public void deleteMovieById(@PathVariable Long movieId) {
        log.info("delete movie with id - {}", movieId);
        movieService.deleteMovieById(movieId);
    }

    @GetMapping("/search")
    public Set<MovieDto> searchMovies(@RequestParam("query") String query) {
        return movieService.findMovieByQuery(query);
    }

    @GetMapping("/discover")
    public List<MovieDto> getNewestMovieList(@RequestParam(defaultValue = "1", required = false) Integer page) {
        return movieService.getNewestMovieList(page);
    }

    @GetMapping("/popular")
    public List<MovieDto> getPopularMovieList(@RequestParam(defaultValue = "1", required = false) Integer page,
                                              @RequestParam(defaultValue = "en-US", required = false) String lang) {
        return movieService.getPopularMovieList(page,lang);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PatchMapping("/add/rating/{movieId}")
    public MovieDto addRating (@PathVariable Long movieId, @RequestParam int rating) {
        return movieService.addRating(movieId, rating);
    }

}
