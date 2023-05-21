package pl.ccteamone.filmvault.user.location.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponse {

    private UUID idR;
    private String cityR;
    private String countryR;
    private String flagR;
    private Set<UUID> usersR;
}
