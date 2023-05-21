package pl.ccteamone.filmvault.user.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.user.MyUser;
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
//        Optional<MyUser> user = userRepository.findByUserName(userName);
//
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
//
//        return user.map(MyUserDetails::new).get();
//    }



    public UserResponse createUser(CreateUserRequest request) {
//        Optional<MyUser> myUsers = userRepository.findByUserName(request.getUsernameR());
//        if (myUsers.isPresent()) {
//            return null;
//        }
        MyUser myUser = MyUser.builder()
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

        userRepository.save(myUser);

        return UserMapper.mapUserToUserResponse(myUser);
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
                .orElseThrow(() -> new EntityNotFoundException("MyUser not found, id: " + userId));
    }



    public UpdateUserResponse updateUser(UUID userId, CreateUserRequest request) {
        MyUser myUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("MyUser not found, id: " + userId));
        myUser.setEmail(request.getEmailR());
        myUser.setPassword(request.getPasswordR());
        myUser.setName(request.getNameR());
        myUser.setSurname(request.getSurnameR());
        myUser.setBirthDate(request.getBirthDateR());
        myUser.setGender(request.getGenderR());
        myUser.setRegion(request.getRegionR());
        myUser.setProfilePic(request.getProfilePicR());
        myUser.setRole(request.getRoleR());
        myUser.setActive(request.isActiveR());
        myUser.setLastActivity(request.getLastActivityR());
        myUser.setMovies(request.getMoviesR());
        myUser.setTvSeries(request.getTvSeriesR());
        myUser.setVodPlatforms(request.getVodPlatformsR());

        myUser = userRepository.save(myUser);

        return UserMapper.userToUserResponse(myUser);
    }

    public void deleteUserById(UUID userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("MyUser not found, id: " + userId);
        }
    }

}
