package pl.ccteamone.filmvault.movie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.webclient.ApiMovieClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieApiService {


    private final ApiMovieClient apiMovieClient;


    public Movie getApiMovie(Long movId) {
        return apiMovieClient.getApiMovieForMovieId(movId);
    }


}
