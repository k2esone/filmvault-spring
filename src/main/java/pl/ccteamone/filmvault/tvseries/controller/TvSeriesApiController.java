package pl.ccteamone.filmvault.tvseries.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.mapper.TvSeriesMapper;
import pl.ccteamone.filmvault.tvseries.repository.TvSeriesRepository;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tvseries")
public class TvSeriesApiController {

    private final TvSeriesApiService tvSeriesApiService;
    private final TvSeriesMapper tvSeriesMapper;


    @GetMapping("/{tvSeriesId}")
    public TvSeriesDto getApiTvSeries(@PathVariable Long tvSeriesId) {
        return tvSeriesApiService.getApiTvSeries(tvSeriesId);
    }
}