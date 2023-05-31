package pl.ccteamone.filmvault.movieApi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.movie.repository.MovieRepository;
import pl.ccteamone.filmvault.movieApi.service.MovieApiService;

@RestController
@RequiredArgsConstructor
public class MovieApiController {

    private final MovieApiService movieApiService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @GetMapping("/api/movie/{movId}")
    public MovieDto getApiMovie(@PathVariable Long movId) {
        Movie movie = movieApiService.getApiMovie(movId);
        movieRepository.save(movieApiService.getApiMovie(movId));
        return movieMapper.mapToMovieDto(movie);
    }
}
