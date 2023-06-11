package pl.ccteamone.filmvault.movie.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ccteamone.filmvault.movie.dto.ApiMovieDtoPage;
import pl.ccteamone.filmvault.movie.dto.MovieApiDto;

@Component
@Slf4j
public class ApiMovieClient {


    /*
    https://api.themoviedb.org/3/movie/22?api_key=2cf008cfced14e2935757fdbc052768b
     */
    private static final String API_MOVIE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_DISCOVER_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String API_KEY = "2cf008cfced14e2935757fdbc052768b";

    private RestTemplate restTemplate = new RestTemplate();

    public MovieApiDto getApiMovieByMovieId(Long id) {
        return callGetMethod("{movie_id}?api_key={apiKey}", MovieApiDto.class, id, API_KEY);
    }
//    public String getApiMovieForMovieInfo(int id) {
//        return restTemplate.getForObject(API_MOVIE_URL + "{movie_id}?api_key={apiKey}",
//                String.class, id, API_KEY);
//    }

    public ApiMovieDtoPage getMoviesDiscoverPage(Integer page) {
        if(page == null || page == 0) {
            return callGetMethodWithDiscover("?api_key={apiKey}&popular?language=en-US", ApiMovieDtoPage.class, API_KEY);
        }
        return callGetMethodWithDiscover("?api_key={apiKey}&popular?language=en-US&page={page}", ApiMovieDtoPage.class, API_KEY, page);
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(API_MOVIE_URL + url, responseType, objects);
    }

    private <T> T callGetMethodWithDiscover(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(API_DISCOVER_URL + url, responseType, objects);
    }

}
