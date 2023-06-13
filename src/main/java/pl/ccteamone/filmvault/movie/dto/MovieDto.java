package pl.ccteamone.filmvault.movie.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.region.Region;
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
    private String runtime;
    private String credits;
    private Double rating;
    private Long apiID;
    private Set<VODPlatformDto> vodPlatforms;
    private Region region;
}
