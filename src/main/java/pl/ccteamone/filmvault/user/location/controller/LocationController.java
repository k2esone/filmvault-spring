package pl.ccteamone.filmvault.user.location.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.user.location.dto.CreateLocationRequest;
import pl.ccteamone.filmvault.user.location.dto.LocationResponse;
import pl.ccteamone.filmvault.user.location.dto.UpdateLocationResponse;
import pl.ccteamone.filmvault.user.location.service.LocationService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping()
    public LocationResponse createLocation(@RequestBody CreateLocationRequest request) {
        log.info("location addition has been triggered: {}", request);
        return locationService.createLocation(request);
    }

    @GetMapping()
    public List<LocationResponse> getLocationsList() {
        log.info("someone asked for a locations list");
        return locationService.getLocationsList();
    }

    @GetMapping("/{locationId}")
    public LocationResponse getLocationById(@PathVariable UUID locationId) {
        log.info("someone asked for a location with id - {}", locationId);
        return locationService.getLocationById(locationId);
    }

    @PatchMapping("/{locationId}")
    public UpdateLocationResponse updateLocation (@PathVariable UUID locationId, @RequestBody CreateLocationRequest request) {
        log.info("location update with id - {} has been triggered, data: {}", locationId, request);
        return locationService.updateLocation(locationId, request);
    }

    @DeleteMapping("/{locationId")
        public void deleteLocationById(UUID locationId) {
        log.info("someone ask to delete location with id - {}", locationId);
        locationService.deleteLocationById(locationId);
    }
}
