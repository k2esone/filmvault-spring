package pl.ccteamone.filmvault.vodplatform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.vodplatform.dto.CreateVODPlatformRequest;
import pl.ccteamone.filmvault.vodplatform.dto.UpdateVODPlatformResponse;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformResponse;
import pl.ccteamone.filmvault.vodplatform.service.VODPlatformService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/platforms")
public class VODPlatformController {

    private final VODPlatformService platformService;

    public VODPlatformController(VODPlatformService platformService) {
        this.platformService = platformService;
    }

    @PostMapping()
    public VODPlatformResponse createVODPlatform(@RequestBody CreateVODPlatformRequest request) {
        log.info("vodPlatform addition has been triggered: {}", request);
        return platformService.createVODPlatform(request);
    }

    @GetMapping()
    public List<VODPlatformResponse> getVODPlatformList() {
        log.info("someone asked for a vodPlatform list");
        return platformService.getVODPlatformList();
    }
    @GetMapping("/{vodPlatformId}")
    public VODPlatformResponse getVODPlatformById(@PathVariable UUID vodPlatformId) {
        log.info("someone asked for a vodPlatform with id - {}", vodPlatformId);
        return platformService.getVODPlatformById(vodPlatformId);
    }

    @PatchMapping("/{vodPlatformId}")
    public UpdateVODPlatformResponse updateVODPlatform (@PathVariable UUID vodPlatformId, @RequestBody CreateVODPlatformRequest request) {
        log.info("vodPlatform update with id - {} has been triggered, data: {}", vodPlatformId, request);
        return platformService.updateVODPlatform(vodPlatformId, request);

    }

    @DeleteMapping("/{vodPlatformId}")
    public void deleteVODPlatformById(UUID vodPlatformId) {
        log.info("someone ask to delete vodPlatform with id - {}", vodPlatformId);
        platformService.deleteVODPlatformById(vodPlatformId);
    }
}
