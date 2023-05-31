package pl.ccteamone.filmvault.appuser.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.appuser.AppUser;
import pl.ccteamone.filmvault.appuser.dto.AppUserCreationDto;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserDto mapToAppUserDto(AppUser appUser);

    AppUserCreationDto mapToAppUserCreationDto(AppUser appUser);

/*    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "tvSeries", ignore = true)
    @Mapping(target = "vodPlatforms", ignore = true)
    AppUser mapUpdateAppUserFromDto(AppUserDto appUserDto);*/

    @InheritInverseConfiguration(name = "mapToAppUserCreationDto")
    AppUser mapToAppUser(AppUserCreationDto appUserCreationDto);

    @InheritInverseConfiguration(name = "mapToAppUserDto")
    AppUser mapToAppUser(AppUserDto appUserDto);
 
/*    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "tvSeries", ignore = true)
    @Mapping(target = "vodPlatforms", ignore = true)*/
    Set<AppUserDto> mapToAppUserDtoSet(Set<AppUser> appUserSet);

    @InheritInverseConfiguration(name = "mapToAppUserDtoSet")
    Set<AppUser> mapToAppUserSet(Set<AppUserDto> appUserDtoSet);

}
