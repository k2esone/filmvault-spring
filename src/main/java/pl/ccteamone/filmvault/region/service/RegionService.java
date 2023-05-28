package pl.ccteamone.filmvault.region.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.region.dto.CreateRegionRequest;
import pl.ccteamone.filmvault.region.dto.RegionResponse;
import pl.ccteamone.filmvault.region.dto.UpdateRegionRequest;
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
                .myUsers(request.getUsersR())
                .tvSeries(request.getTvSeriesR())
                .build();
        regionRepository.save(region);

        return RegionMapper.mapLocationToLocationResponse(region);
    }

    public List<RegionResponse> getLocationsList() {
        return regionRepository.findAll()
                .stream().map(RegionMapper::mapLocationToLocationResponse)
                .toList();
    }

    public RegionResponse getLocationById(UUID regionId) {
        return regionRepository.findById(regionId)
                .stream().map(RegionMapper::mapLocationToLocationResponse)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Location not found, id: " + regionId));
    }

    public UpdateRegionResponse updateLocation(UUID regionId, UpdateRegionRequest request) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found, id:" + regionId));
        region.setCity(request.getCityR());
        region.setCountry(request.getCountryR());
        region.setFlag(request.getFlagR());
        region.setMyUsers(request.getUsersR());
        region.setTvSeries(region.getTvSeries());

        region = regionRepository.save(region);

        return RegionMapper.locationToLocationResponse(region);
    }

    public void deleteLocationById(UUID regionId) {
        try {
            regionRepository.deleteById(regionId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Location not found, id: " + regionId);
        }

    }
}
