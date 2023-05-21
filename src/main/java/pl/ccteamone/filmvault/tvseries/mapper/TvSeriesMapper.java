package pl.ccteamone.filmvault.tvseries.mapper;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.vodplatform.mapper.VODPlatformMapper;

import java.util.stream.Collectors;

public class TvSeriesMapper {

    public static TvSeriesDto tvSeriesToDto(TvSeries tvSeries) {
        return TvSeriesDto.builder()
                .id(tvSeries.getId())
                .name(tvSeries.getName())
                .description(tvSeries.getDescription())
                .genre(tvSeries.getGenre())
                .poster(tvSeries.getPoster())
                .adult(tvSeries.isAdult())
                .origin(tvSeries.getOrigin())
                .firstAirDate(tvSeries.getFirstAirDate())
                .lastAirDate(tvSeries.getLastAirDate())
                .seasons(tvSeries.getSeasons())
                .regions(tvSeries.getRegions())
                .platforms(tvSeries.getPlatforms().stream()
                        .map(VODPlatformMapper::toVODPlatformDto)
                        .collect(Collectors.toSet()))
                .apiID(tvSeries.getApiID())
                .build();
    }
}
