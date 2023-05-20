package pl.ccteamone.filmvault.user.dto;

import lombok.*;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.user.Gender;
import pl.ccteamone.filmvault.user.location.Location;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String emailR;

    private String passwordR;

    private String usernameR;
    private String nameR;
    private String surnameR;
    private LocalDate birthDateR;
    private Gender genderR;

    private Location locationR;

    private String profilePicR;
    private String roleR;
    private boolean isActiveR;

    private LocalDateTime createdAtR;

    private LocalDateTime lastActivityR;

    private Set<Movie> moviesR;

    private Set<TvSeries> tvSeriesR;

    private Set<VODPlatform> vodPlatformsR;
}
