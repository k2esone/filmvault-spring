package pl.ccteamone.filmvault.movie.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.CreditDto;
import pl.ccteamone.filmvault.movie.repository.MovieRepository;
import pl.ccteamone.filmvault.movie.webclient.ApiMovieClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieApiService {


    private final ApiMovieClient apiMovieClient;
    private final MovieRepository movieRepository;


    public Movie getApiMovie(Long movId) {
        Movie movie = apiMovieClient.getApiMovieForMovieId(movId);
        movieRepository.save(movie);
        return movie;
    }
    public List<Movie> getApiMovieForTitle(String title) throws JsonProcessingException {
        List<Movie> movies = apiMovieClient.getApiMovieForMovieTitle(title);
        for (Movie movie : movies) {
            if (movieRepository.findByApiID(movie.getApiID()).isEmpty()) {
                movieRepository.save(apiMovieClient.getApiMovieForMovieId(movie.getApiID()));
            }
        }
        return movies;
    }

    public CreditDto getApiCreditsForMovie(Long movId) {
        return apiMovieClient.getApiCreditsForMovieId(movId);
    }


}
