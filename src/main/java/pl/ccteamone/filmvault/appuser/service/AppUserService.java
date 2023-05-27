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
//        Optional<AppUser> appUsers = userRepository.findByUserName(appUserCreationDto.getUsernameR());
//        if (appUsers.isPresent()) {
//            return null;
//        }
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



    public AppUserDto updateUser(Long userId, AppUserDto request) {
        AppUser appUser = appUserMapper.mapToAppUser(request);
        return appUserMapper.mapToAppUserDto(appUserRepository.save(appUser));
    }

    public void deleteUserById(Long userId) {
        try {
            appUserRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("AppUser not found, id: " + userId);
        }
    }

}
