package pl.ccteamone.filmvault.tvseries.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ccteamone.filmvault.tvseries.dto.ApiTvSeriesDto;
import pl.ccteamone.filmvault.tvseries.dto.ApiTvSeriesDtoPage;

@Component
public class ApiTvSeriesClient {
        /*
    https://api.themoviedb.org/3/tv/11?api_key=2cf008cfced14e2935757fdbc052768b
     */

    private static final String API_TVSERIES_URL = "https://api.themoviedb.org/3/tv/";
    private static final String API_DISCOVER_URL = "https://api.themoviedb.org/3/discover/tv";
    private static final String API_SEARCH_URL = "https://api.themoviedb.org/3/search/tv";
    private static final String API_KEY = "2cf008cfced14e2935757fdbc052768b";


    private final RestTemplate restTemplate = new RestTemplate();



    public ApiTvSeriesDto getApiTvSeriesByTvSeriesId(Long id) {
        return callGetMethod(API_TVSERIES_URL, "{tvseries_id}?api_key={apiKey}", ApiTvSeriesDto.class, id, API_KEY);
    }
    public ApiTvSeriesDtoPage getTvSeriesDiscoverPage(Integer page) {
        return callGetMethod(API_DISCOVER_URL,"?api_key={apiKey}&popular?language=en-US&page={page}", ApiTvSeriesDtoPage.class, API_KEY,page);
    }

    public ApiTvSeriesDtoPage getTvSeriesTitleSearchPage(Integer page, String phrase) {
        return callGetMethod(API_SEARCH_URL, "?api_key={apiKey}&page={page}&query={phrase}", ApiTvSeriesDtoPage.class, API_KEY, page, phrase);
    }

    private <T> T callGetMethod(String TYPE_URL, String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(TYPE_URL + url, responseType, objects);
    }
}
