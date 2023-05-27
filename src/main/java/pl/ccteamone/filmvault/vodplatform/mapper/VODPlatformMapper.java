package pl.ccteamone.filmvault.vodplatform.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface VODPlatformMapper {

    VODPlatformDto mapToVODPlatformDto(VODPlatform platform);
    @InheritInverseConfiguration(name = "mapToVODPlatformDto")
    VODPlatform mapToVODPlatform(VODPlatformDto platformDto);

    Set<VODPlatformDto> mapToVODPlatformDtoSet(Set<VODPlatform> vodPlatforms);

    @InheritInverseConfiguration(name = "mapToVODPlatformDtoSet")
    Set<VODPlatform> mapToVODPlatformSet(Set<VODPlatformDto> vodPlatformDtos);
}
