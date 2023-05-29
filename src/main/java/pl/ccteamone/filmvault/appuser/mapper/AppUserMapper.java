package pl.ccteamone.filmvault.appuser.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.appuser.AppUser;
import pl.ccteamone.filmvault.appuser.dto.AppUserCreationDto;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserDto mapToAppUserDto(AppUser appUser);


    AppUserCreationDto mapToAppUserCreationDto(AppUser appUser);

    @InheritInverseConfiguration(name = "mapToAppUserCreationDto")
    AppUser mapToAppUser(AppUserCreationDto appUserCreationDto);

    @InheritInverseConfiguration(name = "mapToAppUserDto")
    AppUser mapToAppUser(AppUserDto appUserDto);

}
