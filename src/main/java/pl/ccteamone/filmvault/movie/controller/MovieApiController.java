package pl.ccteamone.filmvault.movie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.CreditDto;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.movie.repository.MovieRepository;
import pl.ccteamone.filmvault.movie.service.MovieApiService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieApiController {

    private final MovieApiService movieApiService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @GetMapping("/{movId}")
    public MovieDto getApiMovie(@PathVariable Long movId) {
        Movie movie = movieApiService.getApiMovie(movId);
        return movieMapper.mapToMovieDto(movie);
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
}
