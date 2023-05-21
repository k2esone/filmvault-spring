package pl.ccteamone.filmvault.vodplatform.mapper;

import pl.ccteamone.filmvault.vodplatform.VODPlatform;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;

public class VODPlatformMapper {

    public static VODPlatformDto toVODPlatformDto(VODPlatform platform) {
        return VODPlatformDto.builder()
                .id(platform.getId())
                .name(platform.getName())
                .logoPath(platform.getLogoPath())
                .vodURL(platform.getVodURL())
                .isAvailable(platform.isAvailable())
                .apiID(platform.getApiID())
                .build();
    }
}
