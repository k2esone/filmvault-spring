package pl.ccteamone.filmvault.appuser.security.authentication;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.appuser.AppUser;
import pl.ccteamone.filmvault.appuser.RoleType;
import pl.ccteamone.filmvault.appuser.repository.AppUserRepository;
import pl.ccteamone.filmvault.appuser.security.config.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;

    public String register(RegisterRequest request) {
        AppUser user = AppUser.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .email(request.getEmail())
                .roleType(RoleType.USER)
                .build();
        String username = user.getUsername();
        if (appUserRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }
        appUserRepository.save(user);

        return jwtService.generateToken(user);
    }

    public String authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        AppUser user = appUserRepository.findByUsername(authRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found."));
        return jwtService.generateToken(user);
    }
}
