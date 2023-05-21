package pl.ccteamone.filmvault.vodplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;
import pl.ccteamone.filmvault.vodplatform.service.VODPlatformService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/platforms")
public class VODPlatformController {

    private final VODPlatformService platformService;

    @GetMapping("/list")
    public List<VODPlatformDto> getPlatformList() {
        return platformService.getVODPlatformDtoList();
    }

    @GetMapping("/{id}")
    public VODPlatformDto getPlatform(@PathVariable("id") String id) {
        return platformService.getVODPlatformDtoById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/update/{id}")
    public void updateVODPlatform(@PathVariable("id") String id, @RequestBody VODPlatformDto platformDto) {
        platformService.updateFromDto(id, platformDto);
    }

}
