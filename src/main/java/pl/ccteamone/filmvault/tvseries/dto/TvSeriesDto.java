package pl.ccteamone.filmvault.tvseries.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.genre.dto.GenreDto;
import pl.ccteamone.filmvault.region.dto.RegionDto;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TvSeriesDto {
    private Long id;
    private String name;
    private String overview;
    private String posterPath;
    private boolean adult;
    private String originLanguage;
    private String originCountry;
    private LocalDate firstAirDate;
    private LocalDate lastAirDate;
    private int seasons;
    private int episodes;
    private LocalDate lastUpdate;
    private Long apiID;
    private Set<VODPlatformDto> vodPlatforms;
    private Set<RegionDto> regions;
    private Set<GenreDto> genres;

}
