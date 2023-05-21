package pl.ccteamone.filmvault.user.mapper;

import pl.ccteamone.filmvault.user.MyUser;
import pl.ccteamone.filmvault.user.dto.UpdateUserResponse;
import pl.ccteamone.filmvault.user.dto.UserResponse;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponse mapUserToUserResponse(MyUser myUser) {
        return new UserResponse(
                myUser.getId(),
                myUser.getEmail(),
                myUser.getPassword(),
                myUser.getUsername(),
                myUser.getName(),
                myUser.getSurname(),
                myUser.getBirthDate(),
                myUser.getGender(),
                myUser.getRegion(),
                myUser.getProfilePic(),
                myUser.getRole(),
                myUser.isActive(),
                myUser.getCreatedAt(),
                myUser.getLastActivity(),
                myUser.getMovies().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                myUser.getTvSeries().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                myUser.getVodPlatforms().stream().map(i -> i.getId()).collect(Collectors.toSet())
        );
    }
    public static UpdateUserResponse userToUserResponse(MyUser myUser) {
        return new UpdateUserResponse(
                myUser.getId(),
                myUser.getEmail(),
                myUser.getPassword(),
                myUser.getUsername(),
                myUser.getName(),
                myUser.getSurname(),
                myUser.getBirthDate(),
                myUser.getGender(),
                myUser.getRegion(),
                myUser.getProfilePic(),
                myUser.getRole(),
                myUser.isActive(),
                myUser.getCreatedAt(),
                myUser.getLastActivity(),
                myUser.getMovies().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                myUser.getTvSeries().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                myUser.getVodPlatforms().stream().map(i -> i.getId()).collect(Collectors.toSet())
        );
    }
}
