package pl.ccteamone.filmvault.vodplatform.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.user.MyUser;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVODPlatformRequest {


    private String nameR;
    private String logoPathR;
    private String vodURLR;
    private boolean isAvailableR;
    private String apiIDR;

    private Set<MyUser> myUsersR;
    private Set<Movie> moviesR;
    private Set<TvSeries> tvSeriesR;
}
