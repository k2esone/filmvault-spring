package pl.ccteamone.filmvault.movie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.dto.ApiMovieDtoPage;
import pl.ccteamone.filmvault.movie.dto.MovieApiDto;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.movie.webclient.ApiMovieClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieApiService {


    private final ApiMovieClient apiMovieClient;
    private final MovieService movieService;
    private final MovieMapper movieMapper;


    public MovieDto getApiMovie(Long movId) {
        MovieApiDto movie = apiMovieClient.getApiMovieByMovieId(movId);
        return movieService.createMovie(movieMapper.mapToMovieDto(movie));
    }

    public List<MovieDto> getMovieDiscoverList(Integer page) {
        ApiMovieDtoPage apiMoviePage = apiMovieClient.getMoviesDiscoverPage(page);
        return movieMapper.mapToMovieDtoList(apiMoviePage.getMovies());
    }
    //TODO: zmienić id -> apiID filmów w stronie List<MovieApiDto>, lub stworzyć nową stronę z MovieDto
    public ApiMovieDtoPage getMovieDiscoverPage(Integer page) {
        return apiMovieClient.getMoviesDiscoverPage(page);
    }


}
