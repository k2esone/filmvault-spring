package pl.ccteamone.filmvault.user.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.user.User;
import pl.ccteamone.filmvault.user.dto.CreateUserRequest;
import pl.ccteamone.filmvault.user.dto.UpdateUserResponse;
import pl.ccteamone.filmvault.user.dto.UserResponse;
import pl.ccteamone.filmvault.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private UserResponse mapUserToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getBirthDate(),
                user.getGender(),
                user.getLocation(),
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
                .location(request.getLocationR())
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

        return mapUserToUserResponse(user);
    }

    public List<UserResponse> getUsersList() {
        return userRepository.findAll()
                .stream().map(user -> mapUserToUserResponse(user))
                .toList();
    }

    public UserResponse getUserById(Long userId) {
        return userRepository.findById(userId)
                .stream().map(user -> mapUserToUserResponse(user))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User not found, id: " + userId));
    }

    private UpdateUserResponse userToUserResponse(User user) {
        return new UpdateUserResponse(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getBirthDate(),
                user.getGender(),
                user.getLocation(),
                user.getProfilePic(),
                user.getRole(),
                user.isActive(),
                user.getCreatedAt(),
                user.getLastActivity(),
                user.getMovies(),
                user.getTvSeries(),
                user.getVodPlatforms()
        );
    }

    public UpdateUserResponse updateUser(Long userId, CreateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found, id: " + userId));
        user.setEmail(request.getEmailR());
        user.setPassword(request.getPasswordR());
        user.setName(request.getNameR());
        user.setSurname(request.getSurnameR());
        user.setBirthDate(request.getBirthDateR());
        user.setGender(request.getGenderR());
        user.setLocation(request.getLocationR());
        user.setProfilePic(request.getProfilePicR());
        user.setRole(request.getRoleR());
        user.setActive(request.isActiveR());
        user.setLastActivity(request.getLastActivityR());
        user.setMovies(request.getMoviesR());
        user.setTvSeries(request.getTvSeriesR());
        user.setVodPlatforms(request.getVodPlatformsR());

        user = userRepository.save(user);

        return userToUserResponse(user);
    }

    public void deleteUserById(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("User not found, id: " + userId);
        }
    }
}
