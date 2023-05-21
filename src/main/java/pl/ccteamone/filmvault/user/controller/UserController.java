package pl.ccteamone.filmvault.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.user.dto.CreateUserRequest;
import pl.ccteamone.filmvault.user.dto.UpdateUserResponse;
import pl.ccteamone.filmvault.user.dto.UserResponse;
import pl.ccteamone.filmvault.user.service.UserService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        log.info("user addition has been triggered: {}", request);
        return userService.createUser(request);
    }

    @GetMapping()
    public List<UserResponse> getUsersList() {
        log.info("someone asked for an users list");
        return userService.getUsersList();
    }
    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable UUID userId) {
        log.info("someone asked for user with id - {}", userId);
        return userService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public UpdateUserResponse updateUser (@PathVariable UUID userId, @RequestBody CreateUserRequest request) {
        log.info("user update with id - {} has been triggered, data: {}", userId, request);
        return userService.updateUser(userId, request);

    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(UUID userId) {
        log.info("someone ask to delete user with id - {}", userId);
        userService.deleteUserById(userId);
    }
}
