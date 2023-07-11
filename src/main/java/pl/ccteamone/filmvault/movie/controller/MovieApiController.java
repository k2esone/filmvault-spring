package pl.ccteamone.filmvault.movie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.CreditDto;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.service.MovieApiService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/demo/movies")
@CrossOrigin(origins = "*")
public class MovieApiController {



    private final MovieApiService movieApiService;

    @Operation(summary = "Returns movie DTO")
    @GetMapping("/{movId}")
    public MovieDto getApiMovie(@PathVariable Long movId) {
        return movieApiService.getApiMovie(movId);
    }

/*    //TYLKO DO TESTÓW
    @GetMapping("/platform/{id}")
    public String something(@PathVariable("id") Long id) {
        movieApiService.getRegionPlatformMapByID(id);
        return "OK";
    }*/

    @GetMapping("/discovery")
    public List<MovieDto> getApiMovieDiscovery(@RequestParam("page") Integer page) {
        return movieApiService.getMovieDiscoverList(page);
    }

    @GetMapping("/credits/{movId}")
    public CreditDto getApiCreditsForMovieId(@PathVariable Long movId) {
        return movieApiService.getApiCreditsForMovie(movId);
    }

    @GetMapping("/search")
    public List<MovieDto> getApiMovieSearch(@RequestParam("page") Integer page, @RequestParam("phrase") String phrase) {
        return movieApiService.getMovieSearchList(page, phrase);
    }
}
