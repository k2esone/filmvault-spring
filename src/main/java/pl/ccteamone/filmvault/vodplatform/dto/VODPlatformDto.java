package pl.ccteamone.filmvault.vodplatform.dto;

import lombok.Builder;
import lombok.Data;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;

import java.util.Set;

@Data
@Builder
public class VODPlatformDto {
    private Long id;
    private String name;
    private String logoPath;
    private String vodURL;
    private boolean active;
    private String apiID;

}
