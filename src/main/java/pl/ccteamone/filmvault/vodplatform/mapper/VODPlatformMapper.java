package pl.ccteamone.filmvault.vodplatform.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface VODPlatformMapper {

    @Mapping(target = "tvSeries", ignore = true)
    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "appUsers", ignore = true)
    VODPlatformDto mapToVODPlatformDto(VODPlatform platform);
    //@InheritInverseConfiguration(name = "mapToVODPlatformDto")
    VODPlatform mapToVODPlatform(VODPlatformDto platformDto);
/*
    @Mapping(target = "tvSeries", ignore = true)
    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "appUsers", ignore = true)
    Set<VODPlatformDto> mapToVODPlatformDtoSet(Set<VODPlatform> vodPlatforms);

    @InheritInverseConfiguration(name = "mapToVODPlatformDtoSet")
    Set<VODPlatform> mapToVODPlatformSet(Set<VODPlatformDto> vodPlatformDtos);*/
}
