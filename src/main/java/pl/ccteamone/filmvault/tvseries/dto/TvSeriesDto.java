package pl.ccteamone.filmvault.tvseries.dto;


import lombok.Builder;
import lombok.Data;
import pl.ccteamone.filmvault.appuser.AppUser;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.region.dto.RegionDto;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class TvSeriesDto {
    private Long id;
    private String name;
    private String description;
    private String genre;
    private String poster;
    private boolean adult;
    private String origin;
    private LocalDate firstAirDate;
    private LocalDate lastAirDate;
    private int seasons;

    private Region region;
    private Set<VODPlatformDto> vodPlatforms;
    private Set<AppUserDto> appUsers;

    private Long apiID;
}
