package pl.ccteamone.filmvault.region;

import jakarta.persistence.*;
import lombok.*;
import pl.ccteamone.filmvault.appuser.AppUser;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String country;
    private String flag;

/*    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "region")
    private Set<AppUser> appUsers;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "region")
    private Set<TvSeries> tvSeries;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "region")
    private Set<Movie> movies;*/
}

