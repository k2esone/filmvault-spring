package pl.ccteamone.filmvault.appuser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.appuser.dto.AppUserCreationDto;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;
import pl.ccteamone.filmvault.appuser.service.AppUserService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public AppUserDto createUser(@RequestBody AppUserCreationDto request) {
        log.info("user addition has been triggered: {}", request);
        return appUserService.createAppUser(request);
    }

    @PostMapping("/{username}/add-movie")
    public AppUserDto addMovieByTitleToUser(@PathVariable String username, @RequestParam String movieTitle) {
        return appUserService.addMovieByTitle(username, movieTitle);
    }

    @PostMapping("/{username}/add-movieid")
    public AppUserDto addMovieByApiIdToUser(@PathVariable String username, @RequestParam Long movieApiId) {
        return appUserService.addMovieByApiId(username, movieApiId);
    }

    @PostMapping("/{username}/add/movie")
    public AppUserDto addMovieByID(@PathVariable(value = "username") String username, @RequestParam(name = "movieid") Long movieID) {
        return appUserService.addMovieByID(username, movieID);
    }

    @PostMapping("/username/add/tvseries")
    public AppUserDto addTvSeriesByID(@PathVariable(name = "username") String username, @RequestParam("tvseriesid") Long tvseriesID) {
        return appUserService.addTvSeriesByID(username, tvseriesID);
    }

    //TODO: Set public fuction for searching users (public profile)
    @GetMapping()
    public List<AppUserDto> getUsersList() {
        log.info("someone asked for an appUsers list");
        return appUserService.getUsersList();
    }

    @GetMapping("/{username}")
    public AppUserDto getUserByName(@PathVariable(name = "username") String username) {
        return appUserService.getUserDtoByUsername(username);
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
