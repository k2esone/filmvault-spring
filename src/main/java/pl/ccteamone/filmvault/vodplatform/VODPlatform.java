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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VODPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String logoPath;
    private String vodURL;
    private boolean isAvailable;
    private String apiID;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "vodPlatforms")
    private Set<MyUser> myUsers;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "vodPlatforms")
    private Set<Movie> movies;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "platforms")
    private Set<TvSeries> tvSeries;

}
