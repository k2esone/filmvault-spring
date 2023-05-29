package pl.ccteamone.filmvault.vodplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;
import pl.ccteamone.filmvault.vodplatform.service.VODPlatformService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vodplatforms")
public class VODPlatformController {

    private final VODPlatformService platformService;

    @PostMapping("/add")
    public VODPlatformDto createVODPlatform(@RequestBody VODPlatformDto vodPlatformDto) {
        return platformService.createVODPlatform(vodPlatformDto);
    }
    @GetMapping()
    public List<VODPlatformDto> getPlatformList() {
        return platformService.getVODPlatformDtoList();
    }

    @GetMapping("/{id}")
    public VODPlatformDto getPlatform(@PathVariable Long id) {
        return platformService.getVODPlatformDtoById(id);
    }

    @PatchMapping("/{id}")
    public VODPlatformDto updateVODPlatform(@PathVariable Long id, @RequestBody VODPlatformDto platformDto) {
        return platformService.updateVODPlatform(id, platformDto);
    }

    @DeleteMapping("/{vodPlatformId}")
    public void deleteVODPlatform(@PathVariable Long vodPlatformId) {
        platformService.deleteVODPlatformById(vodPlatformId);
    }

}
