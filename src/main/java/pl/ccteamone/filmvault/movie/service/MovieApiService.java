package pl.ccteamone.filmvault.movie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.webclient.ApiMovieClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieApiService {


    private final ApiMovieClient apiMovieClient;
    private final MovieService movieService;


    public MovieDto getApiMovie(Long movId) {
        MovieDto movie = apiMovieClient.getApiMovieForMovieId(movId);
        return movieService.createMovie(movie);
    }

    public List<MovieDto> getMovieDiscoverList(Integer page) {
        return apiMovieClient.getMovieDiscoverList(page);
    }


}
