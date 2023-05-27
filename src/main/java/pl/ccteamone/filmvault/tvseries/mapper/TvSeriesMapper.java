package pl.ccteamone.filmvault.tvseries.mapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TvSeriesMapper {


    @Mapping(source = "regions", target = "regions")
    @Mapping(source = "platforms", target = "platforms")
    TvSeriesDto mapToTvSeriesDto(TvSeries tvSeries);

    @InheritInverseConfiguration(name = "mapToTvSeriesDto")
    TvSeries mapToTvSeries(TvSeriesDto tvSeriesDto);

    Set<TvSeriesDto> mapToTvSeriesDtoSet(Set<TvSeries> tvSeries);

    @InheritInverseConfiguration
    Set<TvSeries> mapToTvSeriesSet(Set<TvSeriesDto> tvSeriesDtos);
}
