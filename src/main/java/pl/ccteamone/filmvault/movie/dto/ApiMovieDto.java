package pl.ccteamone.filmvault.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import pl.ccteamone.filmvault.genre.Genre;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiMovieDto {

    private Long id;
    private boolean adult;
    private String title;
    private String overview;
    @JsonProperty("genres")
    private Genre[] genres;
    @JsonProperty("runtime")
    private Integer runtime;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("vote_average")
    private Double rating;
    @JsonProperty("vote_count")
    private Integer voteCount;
    private String status;


}
