package pl.ccteamone.filmvault.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.user.Gender;
import pl.ccteamone.filmvault.user.MyUser;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private UUID idR;
    private String titleR;
    private String posterPathR;
    private String overviewR;
    private String releaseDateR;
    private String runtimeR;
    private String creditsR;
    private double ratingR;

    private Set<UUID> myUsers;

    private Set<UUID> vodPlatforms;

}
