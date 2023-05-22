package pl.ccteamone.filmvault.vodplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.user.MyUser;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VODPlatformResponse {

    private UUID idR;
    private String nameR;
    private String logoPathR;
    private String vodURLR;
    private boolean isAvailableR;
    private String apiIDR;

    private Set<UUID> myUsersR;
    private Set<UUID> moviesR;
    private Set<UUID> tvSeriesR;
}
