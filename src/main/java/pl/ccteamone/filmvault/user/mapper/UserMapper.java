package pl.ccteamone.filmvault.user.mapper;

import pl.ccteamone.filmvault.user.User;
import pl.ccteamone.filmvault.user.dto.UpdateUserResponse;
import pl.ccteamone.filmvault.user.dto.UserResponse;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponse mapUserToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getBirthDate(),
                user.getGender(),
                user.getRegion(),
                user.getProfilePic(),
                user.getRole(),
                user.isActive(),
                user.getCreatedAt(),
                user.getLastActivity(),
                user.getMovies().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                user.getTvSeries().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                user.getVodPlatforms().stream().map(i -> i.getId()).collect(Collectors.toSet())
        );
    }
    public static UpdateUserResponse userToUserResponse(User user) {
        return new UpdateUserResponse(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getBirthDate(),
                user.getGender(),
                user.getRegion(),
                user.getProfilePic(),
                user.getRole(),
                user.isActive(),
                user.getCreatedAt(),
                user.getLastActivity(),
                user.getMovies().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                user.getTvSeries().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                user.getVodPlatforms().stream().map(i -> i.getId()).collect(Collectors.toSet())
        );
    }
}
