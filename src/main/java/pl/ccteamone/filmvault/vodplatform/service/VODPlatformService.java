package pl.ccteamone.filmvault.vodplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;
import pl.ccteamone.filmvault.vodplatform.mapper.VODPlatformMapper;
import pl.ccteamone.filmvault.vodplatform.repository.VODPlatformRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class VODPlatformService {
    private final VODPlatformRepository platformRepository;

    public VODPlatform save(VODPlatform platform) {
        return platformRepository.save(platform);
    }

    public VODPlatformDto saveFromDto(VODPlatformDto platformDto) {
        VODPlatform platform = VODPlatform.builder()
                .name(platformDto.getName())
                .logoPath(platformDto.getLogoPath())
                .vodURL(platformDto.getVodURL())
                .isAvailable(platformDto.isAvailable())
                .apiID(platformDto.getApiID())
                .build();
        return VODPlatformMapper.toVODPlatformDto(platformRepository.save(platform));
    }

    public boolean existsById(String id) {
        return platformRepository.existsById(UUID.fromString(id));
    }

    public List<VODPlatformDto> getVODPlatformDtoList() {
        return StreamSupport.stream(platformRepository.findAll().spliterator(), false).map(VODPlatformMapper::toVODPlatformDto).collect(Collectors.toList());
    }

    //TODO: create custom exception for handling missing VOD Platform
    public VODPlatformDto getVODPlatformDtoById(String id) {
        Optional<VODPlatform> platform = platformRepository.findById(UUID.fromString(id));
        return VODPlatformMapper.toVODPlatformDto(platform.orElseThrow(() -> new RuntimeException("VOD Platform id=" + id + " not found")));
    }

    public VODPlatformDto getVODPlatformDtoByApiID(String id) {
        Optional<VODPlatform> platform = platformRepository.findByApiID(id);
        return VODPlatformMapper.toVODPlatformDto(platform.orElseThrow(() -> new RuntimeException("VOD Platform apiID=" + id + " not found")));
    }

    public void updateFromDto(String id, VODPlatformDto platformDto) {
        VODPlatform platform = platformRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("VOD Platform apiID=" + id + " not found"));
        platform.setName(platformDto.getName());
        platform.setLogoPath(platformDto.getLogoPath());
        platform.setVodURL(platformDto.getVodURL());
        platform.setAvailable(platformDto.isAvailable());
        platform.setApiID(platformDto.getApiID());
        platformRepository.save(platform);
    }
}
