package pl.ccteamone.filmvault.user.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.user.User;
import pl.ccteamone.filmvault.user.dto.UserCreationDto;
import pl.ccteamone.filmvault.user.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "movies", target = "movies")
    @Mapping(source = "tvSeries", target = "tvSeries")
    @Mapping(source = "vodPlatforms", target = "vodPlatforms")
    UserDto mapToUserDto(User user);


    UserCreationDto mapToUserCreationDto(User user);

    @InheritInverseConfiguration(name = "mapToUserCreationDto")
    User mapToUser(UserCreationDto userCreationDto);

    @InheritInverseConfiguration(name = "mapToUserDto")
    User mapToUser(UserDto userDto);

}
