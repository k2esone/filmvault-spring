package pl.ccteamone.filmvault.user.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.user.User;
import pl.ccteamone.filmvault.user.dto.CreateUserRequest;
import pl.ccteamone.filmvault.user.dto.UpdateUserResponse;
import pl.ccteamone.filmvault.user.dto.UserResponse;
import pl.ccteamone.filmvault.user.mapper.UserMapper;
import pl.ccteamone.filmvault.user.repository.UserRepository;

import java.util.List;
import java.util.UUID;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUserName(userName);
//
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
//
//        return user.map(MyUserDetails::new).get();
//    }



    public UserResponse createUser(CreateUserRequest request) {
//        Optional<User> users = userRepository.findByUserName(request.getUsernameR());
//        if (users.isPresent()) {
//            return null;
//        }
        User user = User.builder()
                .email(request.getEmailR())
                .password(request.getPasswordR())
                .username(request.getUsernameR())
                .name(request.getNameR())
                .surname(request.getSurnameR())
                .birthDate(request.getBirthDateR())
                .gender(request.getGenderR())
                .region(request.getRegionR())
                .profilePic(request.getProfilePicR())
                .role(request.getRoleR())
                .isActive(request.isActiveR())
                .createdAt(request.getCreatedAtR())
                .lastActivity(request.getLastActivityR())
                .movies(request.getMoviesR())
                .tvSeries(request.getTvSeriesR())
                .vodPlatforms(request.getVodPlatformsR())
                .build();

        userRepository.save(user);

        return UserMapper.mapUserToUserResponse(user);
    }

    public List<UserResponse> getUsersList() {
        return userRepository.findAll()
                .stream().map(UserMapper::mapUserToUserResponse)
                .toList();
    }

    public UserResponse getUserById(UUID userId) {
        return userRepository.findById(userId)
                .stream().map(UserMapper::mapUserToUserResponse)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User not found, id: " + userId));
    }



    public UpdateUserResponse updateUser(UUID userId, CreateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found, id: " + userId));
        user.setEmail(request.getEmailR());
        user.setPassword(request.getPasswordR());
        user.setName(request.getNameR());
        user.setSurname(request.getSurnameR());
        user.setBirthDate(request.getBirthDateR());
        user.setGender(request.getGenderR());
        user.setRegion(request.getRegionR());
        user.setProfilePic(request.getProfilePicR());
        user.setRole(request.getRoleR());
        user.setActive(request.isActiveR());
        user.setLastActivity(request.getLastActivityR());
        user.setMovies(request.getMoviesR());
        user.setTvSeries(request.getTvSeriesR());
        user.setVodPlatforms(request.getVodPlatformsR());

        user = userRepository.save(user);

        return UserMapper.userToUserResponse(user);
    }

    public void deleteUserById(UUID userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("User not found, id: " + userId);
        }
    }

}
