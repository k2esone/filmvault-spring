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

    @GetMapping("/{regionId}")
    public RegionResponse getLocationById(@PathVariable UUID regionId) {
        log.info("someone asked for a location with id - {}", regionId);
        return regionService.getLocationById(regionId);
    }

    @PatchMapping("/{regionId}")
    public UpdateRegionResponse updateLocation (@PathVariable UUID regionId, @RequestBody CreateRegionRequest request) {
        log.info("location update with id - {} has been triggered, data: {}", regionId, request);
        return regionService.updateLocation(regionId, request);
    }

    @DeleteMapping("/{regionId}")
        public void deleteLocationById(UUID regionId) {
        log.info("someone ask to delete location with id - {}", regionId);
        regionService.deleteLocationById(regionId);
    }
}
