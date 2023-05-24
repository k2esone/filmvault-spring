package pl.ccteamone.filmvault.region.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.region.dto.RegionDto;
import pl.ccteamone.filmvault.region.mapper.RegionMapper;
import pl.ccteamone.filmvault.region.repository.RegionRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public RegionDto createLocation(RegionDto request) {
        Region region = Region.builder()
                .city(request.getCity())
                .country(request.getCountry())
                .flag(request.getFlag())
                .build();
        regionRepository.save(region);

        return regionMapper.mapToRegionDto(region);
    }

    public List<RegionDto> getLocationsList() {
        return regionRepository.findAll()
                .stream().map(regionMapper::mapToRegionDto)
                .toList();
    }

    public RegionDto getLocationById(UUID locationId) {
        return regionRepository.findById(locationId)
                .stream().map(regionMapper::mapToRegionDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Location not found, id: " + locationId));
    }

    public RegionDto updateLocation(UUID locationId, RegionDto request) {
        Region region = regionRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found, id:" + locationId));
        region.setCity(request.getCity());
        region.setCountry(request.getCountry());
        region.setFlag(request.getFlag());
        region = regionRepository.save(region);

        return regionMapper.mapToRegionDto(region);
    }

    public void deleteLocationById(UUID locationId) {
        try {
            regionRepository.deleteById(locationId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Location not found, id: " + locationId);
        }

    }
}
