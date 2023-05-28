package pl.ccteamone.filmvault.tvseries.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.tvseries.dto.CreateTvSeriesRequest;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesResponse;
import pl.ccteamone.filmvault.tvseries.dto.UpdateTvSeriesRequest;
import pl.ccteamone.filmvault.tvseries.dto.UpdateTvSeriesResponse;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/tvseries")
public class TvSeriesController {

    private final TvSeriesService tvService;

    public TvSeriesController(TvSeriesService tvService) {
        this.tvService = tvService;
    }


    @PostMapping()
    public TvSeriesResponse createTvSeries(@RequestBody CreateTvSeriesRequest request) {
        log.info("tvSeries addition has been triggered: {}", request);
        return tvService.createTvSeries(request);
    }

    @GetMapping()
    public List<TvSeriesResponse> getTvSeriesList() {
        log.info("someone asked for a tvSeries list");
        return tvService.getTvSeriesList();
    }
    @GetMapping("/{tvSeriesId}")
    public TvSeriesResponse getTvSeriesById(@PathVariable UUID tvSeriesId) {
        log.info("someone asked for tvSeries with id - {}", tvSeriesId);
        return tvService.getTvSeriesById(tvSeriesId);
    }

    @PatchMapping("/{tvSeriesId}")
    public UpdateTvSeriesResponse updateTvSeries (@PathVariable UUID tvSeriesId, @RequestBody UpdateTvSeriesRequest request) {
        log.info("tvSeries update with id - {} has been triggered, data: {}", tvSeriesId, request);
        return tvService.updateTvSeries(tvSeriesId, request);

    }

    @DeleteMapping("/{tvSeriesId}")
    public void deleteTvSeriesById(UUID tvSeriesId) {
        log.info("someone ask to delete tvSeries with id - {}", tvSeriesId);
        tvService.deleteTvSeriesById(tvSeriesId);
    }
}
