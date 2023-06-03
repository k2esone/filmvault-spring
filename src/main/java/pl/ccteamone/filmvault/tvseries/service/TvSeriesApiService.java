package pl.ccteamone.filmvault.tvseries.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.webclient.ApiTvSeriesClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class TvSeriesApiService {

    private final ApiTvSeriesClient apiTvSeriesClient;

    public TvSeries getApiTvSeries(Long tvSeriesId) {
        return apiTvSeriesClient.getApiTvSeriesForTvSeriesId(tvSeriesId);
    }
}
