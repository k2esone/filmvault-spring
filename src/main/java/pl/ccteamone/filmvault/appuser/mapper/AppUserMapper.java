package pl.ccteamone.filmvault.appuser.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.ccteamone.filmvault.appuser.AppUser;
import pl.ccteamone.filmvault.appuser.dto.AppUserCreationDto;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;
import pl.ccteamone.filmvault.appuser.dto.AppUserProfileDto;


import java.util.Set;

@Mapper(componentModel = "spring")
public interface AppUserMapper {


    AppUserDto mapToAppUserDto(AppUser appUser);
    AppUserProfileDto mapToAppUserProfileDto(AppUser appUser);

    AppUserProfileDto mapToAppUserProfileDto(AppUserDto appUserDto);

    AppUserCreationDto mapToAppUserCreationDto(AppUser appUser);

    @InheritInverseConfiguration(name = "mapToAppUserCreationDto")
    AppUser mapToAppUser(AppUserCreationDto appUserCreationDto);

    @InheritInverseConfiguration(name = "mapToAppUserDto")
    AppUser mapToAppUser(AppUserDto appUserDto);

    Set<AppUserDto> mapToAppUserDtoSet(Set<AppUser> appUserSet);

    @InheritInverseConfiguration(name = "mapToAppUserDtoSet")
    Set<AppUser> mapToAppUserSet(Set<AppUserDto> appUserDtoSet);

}
