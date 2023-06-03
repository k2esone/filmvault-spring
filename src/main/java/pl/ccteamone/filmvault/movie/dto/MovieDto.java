package pl.ccteamone.filmvault.movie.dto;


import lombok.Builder;
import lombok.Data;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;

import java.util.Set;

@Data
@Builder
public class MovieDto {
    private Long id;
    private String title;
    private String posterPath;
    private String overview;
    private String releaseDate;
    private String runtime;
    private String credits;
    private Double rating;
    private Long apiID;
    private Set<AppUserDto> appUsers;
    private Set<VODPlatformDto> vodPlatforms;
    private Region region;
}
