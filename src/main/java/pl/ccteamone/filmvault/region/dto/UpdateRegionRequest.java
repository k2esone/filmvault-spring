package pl.ccteamone.filmvault.region.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.user.MyUser;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRegionRequest {
    private String cityR;
    private String countryR;
    private String flagR;
    private Set<MyUser> usersR;
    private Set<TvSeries> tvSeriesR;

}
