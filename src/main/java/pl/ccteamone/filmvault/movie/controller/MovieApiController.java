package pl.ccteamone.filmvault.movie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.movie.repository.MovieRepository;
import pl.ccteamone.filmvault.movie.service.MovieApiService;

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
        movieRepository.save(movieApiService.getApiMovie(movId));
        return movieMapper.mapToMovieDto(movie);
    }
}
