package pl.ccteamone.filmvault.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import pl.ccteamone.filmvault.appuser.AppUser;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String title;
    @JsonProperty("poster_path")
    private String posterPath;
    @Column(length = 512)
    private String overview; //opis do filmu
    @JsonProperty("release_date")
    private String releaseDate;
    private String runtime; //czas trwania filmu
    private String credits; //moze byc kolekcją - aktorzy

    //TODO: create rating implementation logic
    @JsonProperty("vote_average")
    private Double rating;

    private Long apiID;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<VODPlatform> vodPlatforms;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Region region;

}

