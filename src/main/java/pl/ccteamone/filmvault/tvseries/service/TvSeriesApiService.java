package pl.ccteamone.filmvault.tvseries.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.webclient.ApiTvSeriesClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class TvSeriesApiService {

    private final ApiTvSeriesClient apiTvSeriesClient;
    private final TvSeriesService tvSeriesService;
    public TvSeriesDto getApiTvSeries(Long tvSeriesId) {
        TvSeriesDto tvSeries = apiTvSeriesClient.getApiTvSeriesForTvSeriesId(tvSeriesId);
        return tvSeriesService.createTvSeries(tvSeries);
    }
}