package pl.ccteamone.filmvault.region.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.region.dto.RegionDto;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    @Mapping(target = "tvSeries", ignore = true)
    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "appUsers", ignore = true)
    RegionDto mapToRegionDto(Region region);

    @InheritInverseConfiguration(name = "mapToRegionDto")
    Region mapToRegion(RegionDto regionDto);
/*    @Mapping(target = "tvSeries", ignore = true)
    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "appUsers", ignore = true)
    Set<RegionDto> mapToRegionDtoSet(Set<Region> regions);

    @InheritInverseConfiguration(name = "mapToRegionDtoSet")
    Set<Region> mapToRegionSet(Set<RegionDto> regionDtos);*/

}
