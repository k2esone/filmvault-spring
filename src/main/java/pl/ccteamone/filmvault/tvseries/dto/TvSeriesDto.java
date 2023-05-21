package pl.ccteamone.filmvault.tvseries.dto;


import lombok.Builder;
import lombok.Data;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
@Data
@Builder
public class TvSeriesDto {
    private UUID id;
    private String name;
    private String description;
    private String genre;
    private String poster;
    private boolean adult;
    private String origin;
    private LocalDate firstAirDate;
    private LocalDate lastAirDate;
    private int seasons;

    private Set<Region> regions;
    private Set<VODPlatformDto> platforms;

    private Long apiID;
}
