package pl.ccteamone.filmvault.movie.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ccteamone.filmvault.movie.dto.CreditDto;

import pl.ccteamone.filmvault.movie.dto.ApiMovieDtoPage;
import pl.ccteamone.filmvault.movie.dto.ApiMovieDto;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;

import java.util.Set;

@Component
@Slf4j
public class ApiMovieClient {

    /*
    https://api.themoviedb.org/3/movie/22?api_key=2cf008cfced14e2935757fdbc052768b
    https://api.themoviedb.org/3/search/movie?api_key=2cf008cfced14e2935757fdbc052768b&query=
    https://api.themoviedb.org/3/movie/22/watch/providers?api_key=2cf008cfced14e2935757fdbc052768b

     */
    private static final String API_MOVIE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_DISCOVER_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String API_SEARCH_URL = "https://api.themoviedb.org/3/search/movie";
    private static final String API_KEY = "2cf008cfced14e2935757fdbc052768b";

    private RestTemplate restTemplate = new RestTemplate();


    public CreditDto getApiCreditsByMovieId(Long id) {
        return callGetMethod(API_MOVIE_URL,"{movie_id}/credits?api_key={apiKey}", CreditDto.class, id, API_KEY);
    }
    public ApiMovieDto getApiMovieByMovieId(Long id) {
        return callGetMethod(API_MOVIE_URL, "{movie_id}?api_key={apiKey}", ApiMovieDto.class, id, API_KEY);
    }

    public ApiMovieDtoPage getMoviesDiscoverPage(Integer page) {
        return callGetMethod(API_DISCOVER_URL, "?api_key={apiKey}&popular?language=en-US&page={page}", ApiMovieDtoPage.class, API_KEY, page);
    }

    public ApiMovieDtoPage getMoviesTitleSearchPage(Integer page, String phrase) {
        return callGetMethod(API_SEARCH_URL, "?api_key={apiKey}&page={page}&query={phrase}", ApiMovieDtoPage.class, API_KEY, page, phrase);
    }

    private <T> T callGetMethod(String TYPE_URL, String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(TYPE_URL + url, responseType, objects);
    }

    /*    public ApiMovieDtoPage getApiMovieByMovieTitle(String title) throws JsonProcessingException {

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

    }*/

}
