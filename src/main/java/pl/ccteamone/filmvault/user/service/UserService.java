package pl.ccteamone.filmvault.user.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.user.User;
import pl.ccteamone.filmvault.user.dto.UserCreationDto;
import pl.ccteamone.filmvault.user.dto.UserDto;
import pl.ccteamone.filmvault.user.mapper.UserMapper;
import pl.ccteamone.filmvault.user.repository.UserRepository;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUserName(userName);
//
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
//
//        return user.map(MyUserDetails::new).get();
//    }



    public UserCreationDto createUser(UserCreationDto userCreationDto) {
//        Optional<User> users = userRepository.findByUserName(userCreationDto.getUsernameR());
//        if (users.isPresent()) {
//            return null;
//        }
        User user = User.builder()
                .email(userCreationDto.getEmail())
                .password(userCreationDto.getPassword())
                .username(userCreationDto.getUsername())
                .build();

        userRepository.save(user);

        return userMapper.mapToUserCreationDto(user);
    }

    public List<UserDto> getUsersList() {
        return userRepository.findAll()
                .stream().map(userMapper::mapToUserDto)
                .toList();
    }

    public UserDto getUserById(UUID userId) {
        return userRepository.findById(userId)
                .stream().map(userMapper::mapToUserDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User not found, id: " + userId));
    }



    public UserDto updateUser(UUID userId, UserDto request) {
        User user = userMapper.mapToUser(request);
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    public void deleteUserById(UUID userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("User not found, id: " + userId);
        }
    }

}
