package pl.ccteamone.filmvault.movie.dto;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MovieDto {
    private UUID id;
    private String title;
    private String posterPath;
    private String overview;
    private String releaseDate;
    private String runtime;
    private String credits;
    private double rating;
}
