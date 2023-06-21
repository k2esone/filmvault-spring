package pl.ccteamone.filmvault.vodplatform.mapper;

import org.mapstruct.Mapper;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;
import pl.ccteamone.filmvault.vodplatform.dto.FileVODPlatformDto;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface VODPlatformMapper {

    VODPlatformDto mapToVODPlatformDto(VODPlatform platform);

    VODPlatform mapToVODPlatform(VODPlatformDto platformDto);

    VODPlatformDto mapToVODPlatformDto(FileVODPlatformDto fileVODPlatformDto);
    List<VODPlatformDto> mapToVODPlatformDtoList(List<FileVODPlatformDto> fileVODPlatformDtoList);

    Set<VODPlatform> mapToVODPlatformSet(Set<VODPlatformDto> vodPlatformDtoSet);
    Set<VODPlatformDto> mapToVODPlatformDtoSet(Set<VODPlatform> vodPlatformSet);
}
