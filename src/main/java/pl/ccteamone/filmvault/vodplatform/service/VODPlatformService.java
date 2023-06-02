package pl.ccteamone.filmvault.vodplatform.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;
import pl.ccteamone.filmvault.vodplatform.mapper.VODPlatformMapper;
import pl.ccteamone.filmvault.vodplatform.repository.VODPlatformRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class VODPlatformService {
    private final VODPlatformRepository platformRepository;
    private final VODPlatformMapper vodPlatformMapper;

    public VODPlatform save(VODPlatform platform) {
        return platformRepository.save(platform);
    }

    public VODPlatformDto createVODPlatform(VODPlatformDto platformDto) {
        VODPlatform platform = VODPlatform.builder()
                .name(platformDto.getName())
                .logoPath(platformDto.getLogoPath())
                .vodURL(platformDto.getVodURL())
                .active(platformDto.isActive())
                .apiID(platformDto.getApiID())
                .build();
        return vodPlatformMapper.mapToVODPlatformDto(platformRepository.save(platform));
    }

    public List<VODPlatformDto> getVODPlatformDtoList() {
        return StreamSupport.stream(platformRepository.findAll().spliterator(), false)
                .map(vodPlatformMapper::mapToVODPlatformDto).collect(Collectors.toList());
    }
    //TODO: create custom exception for handling missing VOD Platform

    public VODPlatformDto getVODPlatformDtoById(Long id) {
        Optional<VODPlatform> platform = platformRepository.findById(id);
        return vodPlatformMapper.mapToVODPlatformDto(platform.orElseThrow(() -> new RuntimeException("VOD Platform id=" + id + " not found")));
    }

    public VODPlatformDto updateVODPlatform(Long id, VODPlatformDto platformDto) {
        VODPlatform platform = platformRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VOD Platform apiID=" + id + " not found"));

        VODPlatform vodPlatform = vodPlatformMapper.mapToVODPlatform(platformDto);

        if (vodPlatform.getName() != null) {
            platform.setName(vodPlatform.getName());
        }
        if (vodPlatform.getLogoPath() != null) {
            platform.setLogoPath(vodPlatform.getLogoPath());
        }
        if (vodPlatform.getVodURL() != null) {
            platform.setVodURL(vodPlatform.getVodURL());
        }
        if (!vodPlatform.isActive()) {
            platform.setActive(vodPlatform.isActive());
        }
        if (vodPlatform.getApiID() != null) {
            platform.setApiID(vodPlatform.getApiID());
        }
        return vodPlatformMapper.mapToVODPlatformDto(platformRepository.save(platform));
    }

    public void deleteVODPlatformById(Long vodPlatformId) {
        try {
            platformRepository.deleteById(vodPlatformId);
        } catch (Exception e) {
            throw  new EntityNotFoundException("VODPlatform id =" + vodPlatformId + " not found");
        }

    }
}
