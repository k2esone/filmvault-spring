package pl.ccteamone.filmvault.tvseries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.genre.Genre;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiTvSeriesDto {

    private Long id;
    private String name;
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;
    private boolean adult;
    @JsonProperty("genres")
    private Genre[] genres;
    @JsonProperty("original_language")
    private String originLanguage;
    private String originCountry;
    @JsonProperty("first_air_date")
    private LocalDate firstAirDate;
    @JsonProperty("last_air_date")
    private LocalDate lastAirDate;
    @JsonProperty("number_of_seasons")
    private Integer seasons;
    @JsonProperty("number_of_episodes")
    private Integer episodes;
}
