package pl.ccteamone.filmvault.region.dto;

import lombok.Builder;
import lombok.Data;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;

import java.util.Set;

@Data
@Builder
public class RegionDto {
    private Long id;
    private String city;
    private String country;
    private String flag;
}
