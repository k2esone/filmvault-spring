package pl.ccteamone.filmvault.movie.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ccteamone.filmvault.movie.Movie;

@Component
public class ApiMovieClient {


    /*
    https://api.themoviedb.org/3/movie/22?api_key=2cf008cfced14e2935757fdbc052768b
     */
    private static final String API_MOVIE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "2cf008cfced14e2935757fdbc052768b";

    private RestTemplate restTemplate = new RestTemplate();

    public Movie getApiMovieForMovieId(Long id) {
        Movie movie = callGetMethod("{movie_id}?api_key={apiKey}", Movie.class, id, API_KEY);
        return Movie.builder()
                .id(id)
                .title(movie.getTitle())
                .posterPath(movie.getPosterPath())
                .overview(movie.getOverview())
                .releaseDate(movie.getReleaseDate())
                .runtime(movie.getRuntime())
                .build();
    }
//    public String getApiMovieForMovieInfo(int id) {
//        return restTemplate.getForObject(API_MOVIE_URL + "{movie_id}?api_key={apiKey}",
//                String.class, id, API_KEY);
//    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(API_MOVIE_URL + url,
                responseType, objects);
    }

}
