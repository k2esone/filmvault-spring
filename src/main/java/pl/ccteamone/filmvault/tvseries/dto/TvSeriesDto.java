package pl.ccteamone.filmvault.tvseries.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.region.Region;
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
    @JsonProperty("poster_path")
    private String posterPath;
    private String genre;
    private boolean adult;
    @JsonProperty("original_language")
    private String originCountry;
    @JsonProperty("first_air_date")
    private LocalDate firstAirDate;
    @JsonProperty("last_air_date")
    private LocalDate lastAirDate;
    @JsonProperty("number_of_seasons")
    private int seasons;
    private int episodes;
    private Long apiID;

    private Region region;
    private Set<VODPlatformDto> vodPlatforms;

}
