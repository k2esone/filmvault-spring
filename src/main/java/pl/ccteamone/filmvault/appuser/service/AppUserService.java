package pl.ccteamone.filmvault.appuser.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.appuser.AppUser;
import pl.ccteamone.filmvault.appuser.dto.AppUserCreationDto;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;
import pl.ccteamone.filmvault.appuser.mapper.AppUserMapper;
import pl.ccteamone.filmvault.appuser.repository.AppUserRepository;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<AppUser> user = userRepository.findByUserName(userName);
//
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
//
//        return user.map(MyUserDetails::new).get();
//    }



    public AppUserCreationDto createAppUser(AppUserCreationDto appUserCreationDto) {

        AppUser appUser = AppUser.builder()
                .email(appUserCreationDto.getEmail())
                .password(appUserCreationDto.getPassword())
                .username(appUserCreationDto.getUsername())
                .build();

        appUserRepository.save(appUser);
        return appUserMapper.mapToAppUserCreationDto(appUser);
    }

    public List<AppUserDto> getUsersList() {
        return appUserRepository.findAll()
                .stream().map(appUserMapper::mapToAppUserDto)
                .toList();
    }

    public AppUserDto getUserById(Long userId) {
        return appUserRepository.findById(userId)
                .stream().map(appUserMapper::mapToAppUserDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found, id: " + userId));
    }



    public AppUserDto updateUser(Long userId, AppUserDto appUserDto) {
        AppUser user = appUserRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("AppUser not found, id: " + userId));

        AppUser appUser = appUserMapper.mapToAppUser(appUserDto);
        if(appUser.getEmail() != null){
            user.setEmail(appUser.getEmail());
        }
        if(appUser.getName() != null) {
            user.setName(appUser.getName());
        }
        if(appUser.getPassword() != null) {
            user.setPassword(appUser.getPassword());
        }
        if(appUser.getSurname() != null) {
            user.setSurname(appUser.getSurname());
        }
        if(appUser.getBirthDate() != null) {
            user.setBirthDate(appUser.getBirthDate());
        }
        if(appUser.getGender() != null) {
            user.setGender(appUser.getGender());
        }
        if(appUser.getProfilePic() != null) {
            user.setProfilePic(appUser.getProfilePic());
        }
        if(appUser.getRegion() != null) {
            user.setRegion(appUser.getRegion());
        }
        if(appUser.getMovies() != null) {
            user.setMovies(appUser.getMovies());
        }
        if (appUser.getVodPlatforms() != null) {
            user.setVodPlatforms(appUser.getVodPlatforms());
        }
        if (appUser.getTvSeries() != null) {
            user.setTvSeries(appUser.getTvSeries());
        }
        return appUserMapper.mapToAppUserDto(appUserRepository.save(user));
    }

    public void deleteUserById(Long userId) {
        try {
            appUserRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("AppUser not found, id: " + userId);
        }
    }

}
