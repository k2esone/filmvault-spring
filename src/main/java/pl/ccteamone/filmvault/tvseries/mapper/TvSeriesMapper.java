package pl.ccteamone.filmvault.tvseries.mapper;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesResponse;
import pl.ccteamone.filmvault.tvseries.dto.UpdateTvSeriesResponse;

import java.util.stream.Collectors;

public class TvSeriesMapper {

    public static TvSeriesResponse mapTvSeriesToTvSeriesResponse(TvSeries tvSeries) {
        return new TvSeriesResponse(
                tvSeries.getId(),
                tvSeries.getName(),
                tvSeries.getDescription(),
                tvSeries.getGenre(),
                tvSeries.getPoster(),
                tvSeries.isAdult(),
                tvSeries.getOrigin(),
                tvSeries.getFirstAirDate(),
                tvSeries.getLastAirDate(),
                tvSeries.getSeasons(),
                tvSeries.getRegion(),
                tvSeries.getPlatforms().stream().map(i->i.getId()).collect(Collectors.toSet()),
                tvSeries.getMyUsers().stream().map(i->i.getId()).collect(Collectors.toSet()),
                tvSeries.getApiID()
        );
    }
    public static UpdateTvSeriesResponse tvSeriesToTvSeriesResponse(TvSeries tvSeries) {
        return new UpdateTvSeriesResponse(
                tvSeries.getId(),
                tvSeries.getName(),
                tvSeries.getDescription(),
                tvSeries.getGenre(),
                tvSeries.getPoster(),
                tvSeries.isAdult(),
                tvSeries.getOrigin(),
                tvSeries.getFirstAirDate(),
                tvSeries.getLastAirDate(),
                tvSeries.getSeasons(),
                tvSeries.getRegion(),
                tvSeries.getPlatforms().stream().map(i->i.getId()).collect(Collectors.toSet()),
                tvSeries.getMyUsers().stream().map(i->i.getId()).collect(Collectors.toSet()),
                tvSeries.getApiID()
        );
    }
}