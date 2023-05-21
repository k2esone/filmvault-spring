package pl.ccteamone.filmvault.user.location.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.user.location.Location;
import pl.ccteamone.filmvault.user.location.dto.CreateLocationRequest;
import pl.ccteamone.filmvault.user.location.dto.LocationResponse;
import pl.ccteamone.filmvault.user.location.dto.UpdateLocationResponse;
import pl.ccteamone.filmvault.user.location.mapper.LocationMapper;
import pl.ccteamone.filmvault.user.location.repository.LocationRepository;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationResponse createLocation(CreateLocationRequest request) {
        Location location = Location.builder()
                .city(request.getCityR())
                .country(request.getCountryR())
                .flag(request.getFlagR())
                .build();
        locationRepository.save(location);

        return LocationMapper.mapLocationToLocationResponse(location);
    }

    public List<LocationResponse> getLocationsList() {
        return locationRepository.findAll()
                .stream().map(LocationMapper::mapLocationToLocationResponse)
                .toList();
    }

    public LocationResponse getLocationById(UUID locationId) {
        return locationRepository.findById(locationId)
                .stream().map(LocationMapper::mapLocationToLocationResponse)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Location not found, id: " + locationId));
    }

    public UpdateLocationResponse updateLocation(UUID locationId, CreateLocationRequest request) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found, id:" + locationId));
        location.setCity(request.getCityR());
        location.setCountry(request.getCountryR());
        location.setFlag(request.getFlagR());
        location.setUsers(request.getUsersR());

        location = locationRepository.save(location);

        return LocationMapper.locationToLocationResponse(location);
    }

    public void deleteLocationById(UUID locationId) {
        try {
            locationRepository.deleteById(locationId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Location not found, id: " + locationId);
        }

    }
}
