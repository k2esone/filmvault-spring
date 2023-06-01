package pl.ccteamone.filmvault.tvseries.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TvSeriesMapper {

    TvSeriesDto mapToTvSeriesDto(TvSeries tvSeries);

    TvSeries mapToTvSeries(TvSeriesDto tvSeriesDto);

    Set<TvSeriesDto> mapToTvSeriesDtoSet(Set<TvSeries> tvSeriesSet);

    @InheritInverseConfiguration
    Set<TvSeries> mapToTvSeriesSet(Set<TvSeriesDto> tvSeriesDtoSet);
}
