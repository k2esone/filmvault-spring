package pl.ccteamone.filmvault.movie.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.genre.dto.GenreDto;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.region.dto.RegionDto;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private String posterPath;
    private String overview;
    private String releaseDate;
    private Integer runtime;
    private Double rating;
    private Integer voteCount;
    private Long apiID;
    private Set<VODPlatformDto> vodPlatforms;
    private Set<RegionDto> regions;
    private Set<GenreDto> genres;
}
