package pl.ccteamone.filmvault.tvseries.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TvSeriesMapper {

    /*@Mapping(target = "appUsers", source = "appUsers", ignore = true)
    @Mapping(target = "vodPlatforms", source = "appUsers", ignore = true)*/
    TvSeriesDto mapToTvSeriesDto(TvSeries tvSeries);

    //@InheritInverseConfiguration(name = "mapToTvSeriesDto")
    TvSeries mapToTvSeries(TvSeriesDto tvSeriesDto);

    //@Mapping(target = "vodPlatforms", ignore = true)
   // @Mapping(target = "appUsers", ignore = true)
    Set<TvSeriesDto> mapToTvSeriesDtoSet(Set<TvSeries> tvSeriesSet);

    @InheritInverseConfiguration
    Set<TvSeries> mapToTvSeriesSet(Set<TvSeriesDto> tvSeriesDtoSet);
}
