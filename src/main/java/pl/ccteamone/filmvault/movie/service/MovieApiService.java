package pl.ccteamone.filmvault.movie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.dto.*;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.movie.webclient.ApiMovieClient;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;
import pl.ccteamone.filmvault.vodplatform.dto.FileVODPlatformDto;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieApiService {


    private final ApiMovieClient apiMovieClient;
    private final MovieMapper movieMapper;

    public MovieDto getApiMovie(Long movId) {
        return movieMapper.mapToMovieDto(apiMovieClient.getApiMovieByMovieId(movId));
    }

    public List<MovieDto> getMovieDiscoverList(Integer page) {
        if (page == null || page == 0) {
            page = 1;
        }
        ApiMovieDtoPage apiMoviePage = apiMovieClient.getMoviesDiscoverPage(page);
        return movieMapper.mapToMovieDtoList(apiMoviePage.getMovies());
    }

    //TODO: still chance for NullPointerException
    public List<MovieDto> getMovieSearchList(Integer page, String phrase) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (phrase == null || phrase.isEmpty()) {
            throw new RuntimeException("No phrase given to initiate search");
        }
        ApiMovieDtoPage apiMoviePage = apiMovieClient.getMoviesTitleSearchPage(page, phrase.trim());
        return movieMapper.mapToMovieDtoList(apiMoviePage.getMovies());
    }

    public CreditDto getApiCreditsForMovie(Long movId) {
        return apiMovieClient.getApiCreditsByMovieId(movId);
    }

    public MovieDtoPage getMovieDiscoverPage(Integer page) {
        return movieMapper.mapToMovieDtoPage(apiMovieClient.getMoviesDiscoverPage(page));
    }

    public MovieDtoPage getMovieTitleSearchPage(Integer page, String phrase) {
        return movieMapper.mapToMovieDtoPage(apiMovieClient.getMoviesTitleSearchPage(page,phrase));
    }

    public Map<String, List<FileVODPlatformDto>> getRegionPlatformMapByID(Long id) {
        return apiMovieClient.getRegionsOfPlatformsByMovieApiID(id);
    }

    /*
        public List<Movie> getApiMovieByTitle(String title) throws JsonProcessingException {
        List<Movie> movies = apiMovieClient.getMoviesTitleSearchPage(title);
        for (Movie movie : movies) {
            if (movieRepository.findByApiID(movie.getApiID()).isEmpty()) {
                movieRepository.save(apiMovieClient.getApiMovieByMovieId(movie.getApiID()));
            }
        }
        return movies;
    }
    */

}
