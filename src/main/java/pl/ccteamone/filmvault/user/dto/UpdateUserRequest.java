package pl.ccteamone.filmvault.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.user.Gender;
import pl.ccteamone.filmvault.user.location.Location;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String emailR;

    private String passwordR;

    private String nameR;
    private String surnameR;
    private LocalDate birthDateR;
    private Gender genderR;

    private Location locationR;

    private String profilePicR;
    private String roleR;
    private boolean isActiveR;


    private LocalDateTime lastActivityR;

    private Set<UUID> moviesR;

    private Set<UUID> tvSeriesR;

    private Set<UUID> vodPlatformsR;
}
