package pl.ccteamone.filmvault.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Gender gender;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Region region;

    private String profilePic;
    private String role;
    private boolean isActive;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime lastActivity;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<Movie> movies = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<TvSeries> tvSeries = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<VODPlatform> vodPlatforms = new HashSet<>();

}





