package pl.ccteamone.filmvault.region.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.region.dto.RegionDto;
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
    public RegionDto createLocation(@RequestBody RegionDto request) {
        log.info("location addition has been triggered: {}", request);
        return regionService.createLocation(request);
    }

    @GetMapping()
    public List<RegionDto> getLocationsList() {
        log.info("someone asked for a locations list");
        return regionService.getLocationsList();
    }

    @GetMapping("/{locationId}")
    public RegionDto getLocationById(@PathVariable("locationId") Long regionId) {
        log.info("someone asked for a location with id - {}", regionId);
        return regionService.getLocationById(regionId);
    }

    @PatchMapping("/{locationId}")
    public RegionDto updateLocation (@PathVariable("locationId") Long regionId, @RequestBody RegionDto request) {
        log.info("location update with id - {} has been triggered, data: {}", regionId, request);
        return regionService.updateLocation(regionId, request);
    }

    @DeleteMapping("/{locationId}")
        public void deleteLocationById(@PathVariable("locationId") Long regionId) {
        log.info("someone ask to delete location with id - {}", regionId);
        regionService.deleteLocationById(regionId);
    }
}
