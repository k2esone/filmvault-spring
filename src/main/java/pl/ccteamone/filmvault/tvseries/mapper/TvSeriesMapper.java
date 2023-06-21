package pl.ccteamone.filmvault.tvseries.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.ApiTvSeriesDto;
import pl.ccteamone.filmvault.tvseries.dto.ApiTvSeriesDtoPage;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDtoPage;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TvSeriesMapper {

    TvSeriesDto mapToTvSeriesDto(TvSeries tvSeries);

    @InheritInverseConfiguration(name = "mapToTvSeriesDto")
    TvSeries mapToTvSeries(TvSeriesDto tvSeriesDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "apiID")
    TvSeriesDto mapToTvSeriesDto(ApiTvSeriesDto apiTvSeriesDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "apiID")
    List<TvSeriesDto> mapToTvSeriesDtoList(List<ApiTvSeriesDto> apiTvSeriesDtoList);

    TvSeriesDtoPage mapToTvSeriesDtoPage(ApiTvSeriesDtoPage apiTvSeriesDtoPage);

    Set<TvSeriesDto> mapToTvSeriesDtoSet(Set<TvSeries> tvSeriesSet);

    @InheritInverseConfiguration(name = "mapToTvSeriesDtoSet")
    Set<TvSeries> mapToTvSeriesSet(Set<TvSeriesDto> tvSeriesDtoSet);
}
