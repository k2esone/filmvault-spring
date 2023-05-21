package pl.ccteamone.filmvault.region.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.user.User;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRegionRequest {
    private String cityR;
    private String countryR;
    private String flagR;
    private Set<User> usersR;
}
