package pl.ccteamone.filmvault.vodplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;
import pl.ccteamone.filmvault.vodplatform.service.VODPlatformService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/providers")
public class VODPlatformController {

    private final VODPlatformService platformService;

    @PostMapping("/add")
    public VODPlatformDto createVODPlatform(@RequestBody VODPlatformDto vodPlatformDto) {
        return platformService.createVODPlatform(vodPlatformDto);
    }
    @GetMapping()
    public List<VODPlatformDto> getActivePlatformList() {
        return platformService.getVODPlatformActiveList();
    }

    @GetMapping("/{id}")
    public VODPlatformDto getPlatform(@PathVariable Long id) {
        return platformService.getVODPlatformDtoById(id);
    }

    @GetMapping("/full")
    public List<VODPlatformDto> getFullPlatformList(){
        return platformService.getVODPlatformDtoFullList();
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
