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

    public RegionDto getLocationById(Long region) {
        return regionRepository.findById(region)
                .stream().map(regionMapper::mapToRegionDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Location not found, id: " + region));
    }

    public RegionDto updateLocation(Long regionId, RegionDto request) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found, id:" + regionId));
        region.setCity(request.getCity());
        region.setCountry(request.getCountry());
        region.setFlag(request.getFlag());
        region = regionRepository.save(region);

        return regionMapper.mapToRegionDto(region);
    }

    public void deleteLocationById(Long regionId) {
        try {
            regionRepository.deleteById(regionId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Location not found, id: " + regionId);
        }

    }
}
