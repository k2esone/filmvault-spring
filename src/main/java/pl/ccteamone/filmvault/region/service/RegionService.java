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

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public RegionDto createRegion(RegionDto request) {
        Region region = Region.builder()
                .city(request.getCity())
                .country(request.getCountry())
                .flag(request.getFlag())
                .build();
        regionRepository.save(region);

        return regionMapper.mapToRegionDto(region);
    }

    public List<RegionDto> getRegionList() {
        return regionRepository.findAll()
                .stream().map(regionMapper::mapToRegionDto)
                .toList();
    }

    public RegionDto getRegionById(Long region) {
        return regionRepository.findById(region)
                .stream().map(regionMapper::mapToRegionDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Region not found, id: " + region));
    }

    public RegionDto updateRegion(Long regionId, RegionDto request) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new EntityNotFoundException("Region not found, id:" + regionId));
        if(request.getCity() != null) {
            region.setCity(request.getCity());
        }
        if(request.getCountry() != null) {
            region.setCountry(request.getCountry());
        }
        if(request.getFlag() != null) {
            region.setFlag(request.getFlag());
        }
        return regionMapper.mapToRegionDto(regionRepository.save(region));
    }

    public void deleteRegionById(Long regionId) {
        try {
            regionRepository.deleteById(regionId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Region not found, id: " + regionId);
        }

    }
}
