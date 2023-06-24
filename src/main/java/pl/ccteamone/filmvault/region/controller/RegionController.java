package pl.ccteamone.filmvault.region.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.region.dto.RegionDto;
import pl.ccteamone.filmvault.region.service.RegionService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/regions")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping("/add")
    public RegionDto createRegion(@RequestBody RegionDto request) {
        log.info("region addition has been triggered: {}", request);
        return regionService.createRegion(request);
    }

    @GetMapping()
    public List<RegionDto> getRegionList() {
        log.info("someone asked for a region list");
        return regionService.getRegionList();
    }

    @GetMapping("/{regionId}")
    public RegionDto getRegionById(@PathVariable Long regionId) {
        log.info("someone asked for a region with id - {}", regionId);
        return regionService.getRegionById(regionId);
    }

    @PatchMapping("/{regionId}")
    public RegionDto updateRegion(@PathVariable Long regionId, @RequestBody RegionDto request) {
        log.info("region update with id - {} has been triggered, data: {}", regionId, request);
        return regionService.updateRegion(regionId, request);
    }

    @DeleteMapping("/{regionId}")
        public void deleteRegionById(@PathVariable Long regionId) {
        log.info("someone ask to delete region with id - {}", regionId);
        regionService.deleteRegionById(regionId);
    }
}
