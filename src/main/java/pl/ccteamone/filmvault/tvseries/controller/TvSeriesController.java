package pl.ccteamone.filmvault.tvseries.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tvseries")
public class TvSeriesController {

    private final TvSeriesService tvService;

    @GetMapping("/list")
    public List<TvSeriesDto> getTvSeriesList() {
        return tvService.getTvSeriesDtoList();
    }

    @GetMapping("/{id}")
    public TvSeriesDto getTvSeries(@PathVariable("id") Long id) {
        return tvService.getTvSeriesDtoById(id);
    }



}
