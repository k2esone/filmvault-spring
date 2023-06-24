package pl.ccteamone.filmvault.region.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.region.dto.FileRegionDto;
import pl.ccteamone.filmvault.region.dto.RegionDto;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    RegionDto mapToRegionDto(Region region);

    @InheritInverseConfiguration(name = "mapToRegionDto")
    Region mapToRegion(RegionDto regionDto);

    RegionDto mapToRegionDto (FileRegionDto fileRegionDto);
    List<RegionDto> mapToRegionDtoList(List<FileRegionDto> fileRegionDtoList);
    Set<RegionDto> mapToRegionDtoSet(Set<Region> regionSet);
    Set<Region> mapToRegionSet(Set<RegionDto> regionDtoSet);

}
