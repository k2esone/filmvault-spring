package pl.ccteamone.filmvault.movieApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movieApi.webclient.apiMovie.ApiMovieClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieApiService {


    private final ApiMovieClient apiMovieClient;


    public Movie getApiMovie(Long movId) {
        return apiMovieClient.getApiMovieForMovieId(movId);
    }


}
