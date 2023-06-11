package pl.ccteamone.filmvault.tvseries.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesApiService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tvseries")
public class TvSeriesApiController {

    private final TvSeriesApiService tvSeriesApiService;


    @GetMapping("/{tvSeriesId}")
    public TvSeriesDto getApiTvSeries(@PathVariable Long tvSeriesId) {
        return tvSeriesApiService.getApiTvSeries(tvSeriesId);
    }

    @GetMapping("/discovery")
    public List<TvSeriesDto> getApiTvSeriesDisvovery(@RequestParam("page") Integer page) {
        if(page == null) {
            return tvSeriesApiService.getTvSeriesDiscoverList(1);
        }
        return tvSeriesApiService.getTvSeriesDiscoverList(page);
    }
}