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
    private Long id;
    private String name;
    private String logoPath;
    private String vodURL;
    private boolean active;
    private String apiID;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "vodPlatforms")
    private Set<AppUser> appUsers;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "vodPlatforms")
    private Set<Movie> movies;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "vodPlatforms")
    private Set<TvSeries> tvSeries;

}
