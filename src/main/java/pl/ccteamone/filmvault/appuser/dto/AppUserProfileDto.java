package pl.ccteamone.filmvault.appuser.dto;

import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;

import java.util.Set;

public class AppUserProfileDto {
    private Long id;
    private String email;
    private String username;
    private String name;
    private Set<MovieDto> movies;
    private Set<TvSeriesDto> tvSeries;
}