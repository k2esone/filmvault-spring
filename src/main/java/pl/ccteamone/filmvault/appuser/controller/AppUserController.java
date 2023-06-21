package pl.ccteamone.filmvault.appuser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.appuser.dto.AppUserCreationDto;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;
import pl.ccteamone.filmvault.appuser.service.AppUserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

//    @PostMapping("/register")
//    public AppUserDto createUser(@RequestBody AppUserCreationDto request) {
//        log.info("user addition has been triggered: {}", request);
//        return appUserService.createAppUser(request);
//    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/{username}/add-movie")
    public AppUserDto addMovieByTitleToUser(@PathVariable String username, @RequestParam String movieTitle) {
        return appUserService.addMovieByTitle(username, movieTitle);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/{username}/add-movieid")
    public AppUserDto addMovieByApiIdToUser(@PathVariable String username, @RequestParam Long movieApiId) {
        return appUserService.addMovieByApiId(username, movieApiId);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/{userid}/add/movie")
    public AppUserDto addMovieByID(@PathVariable(value = "userid") Long userID, @RequestParam("movieid") Long movieID) {
        return appUserService.addMovieByID(userID, movieID);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/userid/add/tvseries")
    public AppUserDto addTvSeriesByID(@PathVariable(value = "userid") Long userID, @RequestParam("tvseriesid") Long tvseriesID) {
        return appUserService.addTvSeriesByID(userID,tvseriesID);
    }

    //TODO: Set public fuction for searching users (public profile)
    // FUTURE -> Change appUserDTO to profileDTO with hidden private informations

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public List<AppUserDto> getUsersList() {
        log.info("someone asked for an appUsers list");
        return appUserService.getUsersList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{userId}")
    public AppUserDto getUserById(@PathVariable Long userId) {
        log.info("someone asked for user with id - {}", userId);
        return appUserService.getUserById(userId);
    }

    //TODO: logic for authorization
    @PreAuthorize("hasAuthority('USER')")
    @PatchMapping("/{userId}")
    public AppUserDto updateUser(@PathVariable Long userId, @RequestBody AppUserDto request) {
        log.info("user update with id - {} has been triggered, data: {}", userId, request);
        return appUserService.updateUser(userId, request);

    }

    //TODO: logic and scope of deleted entities and inapp content (set movie tables/ratings created by Anonymous)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        log.info("someone ask to delete user with id - {}", userId);
        appUserService.deleteUserById(userId);
    }
}
