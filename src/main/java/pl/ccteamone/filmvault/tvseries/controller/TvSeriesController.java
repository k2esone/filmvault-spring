package pl.ccteamone.filmvault.tvseries.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tvseries")
public class TvSeriesController {

    private final TvSeriesService tvService;


    @PostMapping("/add")
    public TvSeriesDto createTvSeries(@RequestBody TvSeriesDto tvSeriesDto) {
        return tvService.createTvSeries(tvSeriesDto);
    }

    @GetMapping
    public List<TvSeriesDto> getTvSeriesList() {
        return tvService.getTvSeriesDtoList();
    }

    @GetMapping("/{id}")
    public TvSeriesDto getTvSeries(@PathVariable Long id) {
        return tvService.getTvSeriesDtoById(id);
    }

    @PatchMapping("/{tvseriesId}")
    public TvSeriesDto updateTvSeries(@PathVariable Long tvseriesId, @RequestBody TvSeriesDto tvSeriesDto) {
        return tvService.updateTvSeries(tvseriesId, tvSeriesDto);
    }

    @DeleteMapping("/{tvseriesId}")
    public void deleteTvSeries(@PathVariable Long tvseriesId) {
        tvService.deleteTvSeries(tvseriesId);
    }



}
