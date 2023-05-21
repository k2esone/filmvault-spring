package pl.ccteamone.filmvault.movie;

import jakarta.persistence.*;
import lombok.*;
import pl.ccteamone.filmvault.user.User;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    @Column(nullable = false)

    private UUID id;
    private String title;
    private String posterPath;
    private String overview;
    private String vodProviders;
    private String releaseDate;
    private String runtime;
    private String credits;
    private double rating;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<User> users;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<VODPlatform> vodPlatforms;

}

