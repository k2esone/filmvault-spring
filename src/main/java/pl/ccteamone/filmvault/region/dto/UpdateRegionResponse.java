package pl.ccteamone.filmvault.region.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRegionResponse {

    private UUID idR;
    private String cityR;
    private String countryR;
    private String flagR;
    private Set<UUID> usersR;
    private Set<UUID> tvSeriesR;

}
