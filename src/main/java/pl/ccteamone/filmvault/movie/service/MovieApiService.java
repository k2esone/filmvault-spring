package pl.ccteamone.filmvault.movie.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.ApiMovieDtoPage;
import pl.ccteamone.filmvault.movie.dto.ApiMovieDto;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.dto.MovieDtoPage;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.movie.webclient.ApiMovieClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieApiService {


    private final ApiMovieClient apiMovieClient;
    private final MovieMapper movieMapper;

    private final MovieRepository movieRepository;

    public MovieDto getApiMovie(Long movId) {
        return movieMapper.mapToMovieDto(apiMovieClient.getApiMovieByMovieId(movId));
    }

    public List<MovieDto> getMovieDiscoverList(Integer page) {
        if(page == null || page == 0) {
            page = 1;
        }
        ApiMovieDtoPage apiMoviePage = apiMovieClient.getMoviesDiscoverPage(page);
        return movieMapper.mapToMovieDtoList(apiMoviePage.getMovies());
    }
    //TODO: still chance for NullPointerException
    public List<MovieDto> getMovieSearchList(Integer page, String phrase) {
        if(page == null || page == 0) {
            page = 1;
        }
        if(phrase == null || phrase.isEmpty()) {
            throw new RuntimeException("No phrase given to initiate search");
        }
        ApiMovieDtoPage apiMoviePage = apiMovieClient.getMoviesTitleSearchPage(page,phrase.trim());
        return movieMapper.mapToMovieDtoList(apiMoviePage.getMovies());
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

    //
    public MovieDtoPage getMovieDiscoverPage(Integer page) {
        return movieMapper.mapToMovieDtoPage(apiMovieClient.getMoviesDiscoverPage(page));
    }


}
