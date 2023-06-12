package pl.ccteamone.filmvault.movie.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.service.MovieApiService;

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
    public List<MovieDto> getApiMovieDiscovery(@RequestParam("page") Integer page) {
        return movieApiService.getMovieDiscoverList(page);
    }

    @GetMapping("/api/movies/search")
    public List<MovieDto> getApiMovieSearch(@RequestParam("page") Integer page, @RequestParam("phrase") String phrase) {
        return movieApiService.getMovieSearchList(page,phrase);
    }

}
