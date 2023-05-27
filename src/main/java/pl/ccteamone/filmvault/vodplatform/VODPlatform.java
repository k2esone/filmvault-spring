package pl.ccteamone.filmvault.vodplatform;

import jakarta.persistence.*;
import lombok.*;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.appuser.AppUser;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VODPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    private Long id;
    private String name;
    private String logoPath;
    private String vodURL;
    private boolean isAvailable;
    private String apiID;

    @ManyToMany(mappedBy = "vodPlatforms")
    private Set<AppUser> appUsers;
    @ManyToMany(mappedBy = "vodPlatforms")
    private Set<Movie> movies;
    @ManyToMany(mappedBy = "platforms")
    private Set<TvSeries> tvSeries;

}
