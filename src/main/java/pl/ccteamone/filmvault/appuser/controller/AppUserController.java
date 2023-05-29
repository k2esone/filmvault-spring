package pl.ccteamone.filmvault.appuser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.appuser.dto.AppUserCreationDto;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;
import pl.ccteamone.filmvault.appuser.service.AppUserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public AppUserCreationDto createUser(@RequestBody AppUserCreationDto request) {
        log.info("user addition has been triggered: {}", request);
        return appUserService.createAppUser(request);
    }

    @PostMapping("/{username}/add-movie")
    public AppUserDto addMovieToUser(@PathVariable String username, @RequestParam String movieTitle) {
        return appUserService.addMovieByTitle(username, movieTitle);
    }

    //TODO: Set public fuction for searching users (public profile)
    @GetMapping()
    public List<AppUserDto> getUsersList() {
        log.info("someone asked for an appUsers list");
        return appUserService.getUsersList();
    }

    @GetMapping("/{userId}")
    public AppUserDto getUserById(@PathVariable Long userId) {
        log.info("someone asked for user with id - {}", userId);
        return appUserService.getUserById(userId);
    }

    //TODO: logic for authorization
    @PatchMapping("/{userId}")
    public AppUserDto updateUser(@PathVariable Long userId, @RequestBody AppUserDto request) {
        log.info("user update with id - {} has been triggered, data: {}", userId, request);
        return appUserService.updateUser(userId, request);

    }

    //TODO: logic and scope of deleted entities and inapp content (set movie tables/ratings created by Anonymous)
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        log.info("someone ask to delete user with id - {}", userId);
        appUserService.deleteUserById(userId);
    }
}
