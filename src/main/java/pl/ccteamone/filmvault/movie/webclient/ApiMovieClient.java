package pl.ccteamone.filmvault.movie.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.CreditDto;
import pl.ccteamone.filmvault.movie.dto.MovieDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ApiMovieClient {


    /*
    https://api.themoviedb.org/3/movie/22?api_key=2cf008cfced14e2935757fdbc052768b
     */
    private static final String API_MOVIE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_MOVIE_SEARCH_URL = "https://api.themoviedb.org/3/search/movie";
    private static final String API_KEY = "2cf008cfced14e2935757fdbc052768b";

    private RestTemplate restTemplate = new RestTemplate();

    public Movie getApiMovieForMovieId(Long id) {
        Movie movie = callGetMethod("{movie_id}?api_key={apiKey}", Movie.class, id, API_KEY);
        return Movie.builder()
                .title(movie.getTitle())
                .posterPath(movie.getPosterPath())
                .overview(movie.getOverview())
                .releaseDate(movie.getReleaseDate())
                .runtime(movie.getRuntime())
                .apiID(id)
                .build();
    }

    public List<Movie> getApiMovieForMovieTitle(String title) throws JsonProcessingException {
        String json = callGetMethodForSearch("?query={title}&api_key={apiKey}", String.class, title, API_KEY);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(json);
        List<Movie> newMovies = new ArrayList<>();

        JsonNode results = root.get("results");
        if (results.isArray()) {
            for (JsonNode result : results) {
                Movie movie = Movie.builder()
                        .title(result.get("original_title").asText())
                        .posterPath(result.get("poster_path").asText())
                        .overview(result.get("overview").asText())
                        .releaseDate(result.get("release_date").asText())
//                        .runtime(result.getRuntime())
                        .apiID(result.get("id").asLong())
                        .build();
                newMovies.add(movie);
            }
        }
        return newMovies;

    }

    public CreditDto getApiCreditsForMovieId(Long id) {
        CreditDto creditDto = callGetMethod("{movie_id}/credits?api_key={apiKey}", CreditDto.class, id, API_KEY);
        return CreditDto.builder()
                .cast(creditDto.getCast())
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

    private <T> T callGetMethodForSearch(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(API_MOVIE_SEARCH_URL + url,
                responseType, objects);
    }

}
