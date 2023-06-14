package pl.ccteamone.filmvault.movie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.service.MovieApiService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieApiController {

    private final MovieApiService movieApiService;
    @Operation(summary = "Returns movie DTO")
    @GetMapping("/{movId}")
    public MovieDto getApiMovie(@PathVariable Long movId) {
        return movieApiService.getApiMovie(movId);
    }

    @GetMapping("/api/movies/discovery")
    public List<MovieDto> getApiMovieDiscovery(@RequestParam("page") Integer page) {
        return movieApiService.getMovieDiscoverList(page);
    }
    @GetMapping()
    public List<MovieDto> getApiMovieForTitle(@RequestParam String title) throws JsonProcessingException {
        List<Movie> movies = movieApiService.getApiMovieForTitle(title);
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie : movies) {
            movieDtos.add(movieMapper.mapToMovieDto(movie));
        }

        return movieDtos;
    }
    @GetMapping("credits/{movId}")
    public CreditDto getApiCreditsForMovieId(@PathVariable Long movId) {
        return movieApiService.getApiCreditsForMovie(movId);
    }

    @GetMapping("/api/movies/search")
    public List<MovieDto> getApiMovieSearch(@RequestParam("page") Integer page, @RequestParam("phrase") String phrase) {
        return movieApiService.getMovieSearchList(page,phrase);
    }
}
