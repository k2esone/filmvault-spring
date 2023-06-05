package pl.ccteamone.filmvault.movie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.service.MovieApiService;

@RestController
@RequiredArgsConstructor
public class MovieApiController {

    private final MovieApiService movieApiService;


    @GetMapping("/api/movie/{movId}")
    public MovieDto getApiMovie(@PathVariable Long movId) {
        return movieApiService.getApiMovie(movId);
    }
}
