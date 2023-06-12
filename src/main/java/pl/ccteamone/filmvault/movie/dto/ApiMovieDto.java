package pl.ccteamone.filmvault.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiMovieDto {

    private Long id;
    private boolean adult;
    private String title;
    private String overview;
    private Integer runtime;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("original_language")
    private String originalLanguage;
    private String status;


}
