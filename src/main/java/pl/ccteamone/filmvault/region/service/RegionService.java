package pl.ccteamone.filmvault.region.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.region.dto.CreateRegionRequest;
import pl.ccteamone.filmvault.region.dto.RegionResponse;
import pl.ccteamone.filmvault.region.dto.UpdateRegionResponse;
import pl.ccteamone.filmvault.region.mapper.RegionMapper;
import pl.ccteamone.filmvault.region.repository.RegionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public RegionResponse createLocation(CreateRegionRequest request) {
        Region region = Region.builder()
                .city(request.getCityR())
                .country(request.getCountryR())
                .flag(request.getFlagR())
                .build();
        regionRepository.save(region);

        return RegionMapper.mapLocationToLocationResponse(region);
    }

    public List<RegionResponse> getLocationsList() {
        return regionRepository.findAll()
                .stream().map(RegionMapper::mapLocationToLocationResponse)
                .toList();
    }

    public RegionResponse getLocationById(UUID locationId) {
        return regionRepository.findById(locationId)
                .stream().map(RegionMapper::mapLocationToLocationResponse)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Location not found, id: " + locationId));
    }

    public UpdateRegionResponse updateLocation(UUID locationId, CreateRegionRequest request) {
        Region region = regionRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found, id:" + locationId));
        region.setCity(request.getCityR());
        region.setCountry(request.getCountryR());
        region.setFlag(request.getFlagR());
        region.setUsers(request.getUsersR());

        region = regionRepository.save(region);

        return RegionMapper.locationToLocationResponse(region);
    }

    public void deleteLocationById(UUID locationId) {
        try {
            regionRepository.deleteById(locationId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Location not found, id: " + locationId);
        }

    }
}
