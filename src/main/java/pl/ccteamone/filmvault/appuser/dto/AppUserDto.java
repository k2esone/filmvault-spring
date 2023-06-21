package pl.ccteamone.filmvault.appuser.dto;

import lombok.Builder;
import lombok.Data;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.appuser.Gender;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class AppUserDto {
    private Long id;

    private String email;

    private String password;

    private String username;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Gender gender;

    private Region region;

    private String profilePic;
    private boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime lastActivity;

    private Set<MovieDto> movies;

    private Set<TvSeriesDto> tvSeries;

    private Set<VODPlatformDto> vodPlatforms;
}
