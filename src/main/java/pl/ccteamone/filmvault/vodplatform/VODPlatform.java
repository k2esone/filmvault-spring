package pl.ccteamone.filmvault.vodplatform;

import jakarta.persistence.*;
import lombok.*;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.user.MyUser;

import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VODPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.PRIVATE)
    private UUID id;
    private String name;
    private String logoPath;
    private String vodURL;
    private boolean isAvailable;
    private String apiID;

    @ManyToMany(mappedBy = "vodPlatforms")
    private Set<MyUser> myUsers;
    @ManyToMany(mappedBy = "vodPlatforms")
    private Set<Movie> movies;
    @ManyToMany(mappedBy = "platforms")
    private Set<TvSeries> tvSeries;

}
