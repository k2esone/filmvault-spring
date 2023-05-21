package pl.ccteamone.filmvault.region.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.region.dto.CreateRegionRequest;
import pl.ccteamone.filmvault.region.dto.RegionResponse;
import pl.ccteamone.filmvault.region.dto.UpdateRegionResponse;
import pl.ccteamone.filmvault.region.service.RegionService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/location")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping()
    public RegionResponse createLocation(@RequestBody CreateRegionRequest request) {
        log.info("location addition has been triggered: {}", request);
        return regionService.createLocation(request);
    }

    @GetMapping()
    public List<RegionResponse> getLocationsList() {
        log.info("someone asked for a locations list");
        return regionService.getLocationsList();
    }

    @GetMapping("/{locationId}")
    public RegionResponse getLocationById(@PathVariable UUID locationId) {
        log.info("someone asked for a location with id - {}", locationId);
        return regionService.getLocationById(locationId);
    }

    @PatchMapping("/{locationId}")
    public UpdateRegionResponse updateLocation (@PathVariable UUID locationId, @RequestBody CreateRegionRequest request) {
        log.info("location update with id - {} has been triggered, data: {}", locationId, request);
        return regionService.updateLocation(locationId, request);
    }

    @DeleteMapping("/{locationId")
        public void deleteLocationById(UUID locationId) {
        log.info("someone ask to delete location with id - {}", locationId);
        regionService.deleteLocationById(locationId);
    }
}
