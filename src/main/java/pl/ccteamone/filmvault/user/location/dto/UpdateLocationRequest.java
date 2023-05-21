package pl.ccteamone.filmvault.user.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.user.user.User;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLocationRequest {
    private String cityR;
    private String countryR;
    private String flagR;
    private Set<User> usersR;
}
