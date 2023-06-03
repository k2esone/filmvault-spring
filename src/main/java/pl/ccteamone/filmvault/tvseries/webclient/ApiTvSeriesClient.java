package pl.ccteamone.filmvault.tvseries.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
//import pl.ccteamone.filmvault.tvSeries.TvSeries;
import pl.ccteamone.filmvault.tvseries.TvSeries;

@Component
public class ApiTvSeriesClient {
        /*
    https://api.themoviedb.org/3/tv/11?api_key=2cf008cfced14e2935757fdbc052768b
     */

    private static final String API_TVSERIES_URL = "https://api.themoviedb.org/3/tv/";
    private static final String API_KEY = "2cf008cfced14e2935757fdbc052768b";

    private final RestTemplate restTemplate = new RestTemplate();

    public TvSeries getApiTvSeriesForTvSeriesId(Long id) {
        TvSeries tvSeries = callGetMethod("{movie_id}?api_key={apiKey}", TvSeries.class, id, API_KEY);
        return TvSeries.builder()
                .id(id)
                .name(tvSeries.getName())
                .overview(tvSeries.getOverview())
                .posterPath(tvSeries.getPosterPath())
                .genres(tvSeries.getGenres())
                .origin(tvSeries.getOrigin())
                .firstAirDate(tvSeries.getFirstAirDate())
                .lastAirDate(tvSeries.getLastAirDate())
                .seasons(tvSeries.getSeasons())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(API_TVSERIES_URL + url,
                responseType, objects);
    }
}
