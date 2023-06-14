package pl.ccteamone.filmvault.tvseries.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/tvseries")
public class TvSeriesController {

    private final TvSeriesService tvService;


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
    public TvSeriesDto getTvSeries(@PathVariable Long Id) {
        log.info("request for tvseries id - {}", Id);
        return tvService.getTvSeriesById(Id);
    }

    @PatchMapping("/{tvseriesId}")
    public TvSeriesDto updateTvSeries(@PathVariable Long tvseriesId, @RequestBody TvSeriesDto update) {
        log.info("update tvseries with id - {}, data: {}", tvseriesId, update);
        return tvService.updateTvSeries(tvseriesId, update);
    }

    @DeleteMapping("/{tvseriesId}")
    public void deleteTvSeries(@PathVariable Long tvseriesId) {
        log.info("delete movie with id - {}", tvseriesId);
        tvService.deleteTvSeriesById(tvseriesId);
    }

    @GetMapping("/search")
    public List<TvSeriesDto> searchTvSeries(@RequestParam("query") String query) {
        return tvService.searchTvSeries(query);
    }




}
