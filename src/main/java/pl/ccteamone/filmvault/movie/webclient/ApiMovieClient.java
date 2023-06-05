package pl.ccteamone.filmvault.movie.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ccteamone.filmvault.movie.dto.MovieDto;

@Component
public class ApiMovieClient {


    /*
    https://api.themoviedb.org/3/movie/22?api_key=2cf008cfced14e2935757fdbc052768b
     */
    private static final String API_MOVIE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "2cf008cfced14e2935757fdbc052768b";

    private RestTemplate restTemplate = new RestTemplate();

    public MovieDto getApiMovieForMovieId(Long id) {
        MovieDto movieDto = callGetMethod("{movie_id}?api_key={apiKey}", MovieDto.class, id, API_KEY);
        return MovieDto.builder()
                .apiID(movieDto.getApiID())
                .title(movieDto.getTitle())
                .posterPath(movieDto.getPosterPath())
                .overview(movieDto.getOverview())
                .releaseDate(movieDto.getReleaseDate())
                .runtime(movieDto.getRuntime())
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
