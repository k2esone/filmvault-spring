package pl.ccteamone.filmvault.tvseries.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesApiService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demo/tvseries")
@CrossOrigin(origins = "*")
public class TvSeriesApiController {

    private final TvSeriesApiService tvSeriesApiService;

    @Operation(summary = "Returns tvseries DTO")
    @GetMapping("/{tvSeriesId}")
    public TvSeriesDto getApiTvSeries(@PathVariable Long tvSeriesId) {
        return tvSeriesApiService.getApiTvSeries(tvSeriesId);
    }

    @GetMapping("/discover")
    public List<TvSeriesDto> getApiTvSeriesDiscover(@RequestParam(defaultValue = "1", required = false) Integer page) {
        return tvSeriesApiService.getTvSeriesDiscoverList(page);
    }

    @GetMapping("/search")
    public List<TvSeriesDto> getApiTvSeriesSearch(@RequestParam(defaultValue = "1", required = false) Integer page, @RequestParam("phrase") String phrase) {
        return tvSeriesApiService.getTvSeriesSearchList(page,phrase);
    }
}