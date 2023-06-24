package pl.ccteamone.filmvault.vodplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;
import pl.ccteamone.filmvault.vodplatform.service.VODPlatformService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/providers")
public class VODPlatformController {

    private final VODPlatformService platformService;

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/full")
    public List<VODPlatformDto> getFullPlatformList(){
        return platformService.getVODPlatformDtoFullList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public VODPlatformDto updateVODPlatform(@PathVariable Long id, @RequestBody VODPlatformDto platformDto) {
        return platformService.updateVODPlatform(id, platformDto);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{vodPlatformId}")
    public void deleteVODPlatform(@PathVariable Long vodPlatformId) {
        platformService.deleteVODPlatformById(vodPlatformId);
    }

}
