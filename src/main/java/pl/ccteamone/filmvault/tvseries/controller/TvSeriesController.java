package pl.ccteamone.filmvault.tvseries.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesService;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tvseries")
public class TvSeriesController {

    private final TvSeriesService tvService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public TvSeriesDto createTvSeries(@RequestBody TvSeriesDto create) {
        log.info("adding new tvseries: {}", create);
        return tvService.createTvSeries(create);
    }

    @GetMapping
    public List<TvSeriesDto> getTvSeriesList() {
        log.info("request for tvsesies list");
        return tvService.getTvSeriesList();
    }

    @GetMapping("/{tvseriesid}")
    public TvSeriesDto getTvSeries(@PathVariable Long tvseriesid) {
        log.info("request for tvseries id - {}", tvseriesid);
        return tvService.getTvSeriesById(tvseriesid);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{tvseriesId}")
    public TvSeriesDto updateTvSeries(@PathVariable Long tvseriesId, @RequestBody TvSeriesDto update) {
        log.info("update tvseries with id - {}, data: {}", tvseriesId, update);
        return tvService.updateTvSeries(tvseriesId, update);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{tvseriesId}")
    public void deleteTvSeries(@PathVariable Long tvseriesId) {
        log.info("delete movie with id - {}", tvseriesId);
        tvService.deleteTvSeries(tvseriesId);
    }

    @GetMapping("/search")
    public Set<TvSeriesDto> searchTvSeries(@RequestParam("query") String query) {
        return tvService.findTvSeriesByQuery(query);
    }

    @GetMapping("/discover")
    public List<TvSeriesDto> getNewestTvSeriesList(@RequestParam(defaultValue = "1", required = false) Integer page) {
        return tvService.getNewestTvSeriesList(page);
    }

}
