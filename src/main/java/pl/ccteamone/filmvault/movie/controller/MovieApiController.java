package pl.ccteamone.filmvault.movie.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.service.MovieApiService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieApiController {

    private final MovieApiService movieApiService;

    @Operation(summary = "Returns movie DTO")
    @GetMapping("/api/movie/{movId}")
    public MovieDto getApiMovie(@PathVariable Long movId) {
        return movieApiService.getApiMovie(movId);
    }

    @GetMapping("/api/movies/discovery")
    public List<MovieDto> getApiMovies(@RequestParam("page") Integer page) {
        if(page == null) {
            return movieApiService.getMovieDiscoverList(1);
        }
        return movieApiService.getMovieDiscoverList(page);
    }

}
