package pl.ccteamone.filmvault.movie.mapper;

import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.MovieDto;

public class MovieMapper {

    public static MovieDto toMovieDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .posterPath(movie.getPosterPath())
                .overview(movie.getOverview())
                .releaseDate(movie.getReleaseDate())
                .runtime(movie.getRuntime())
                .credits(movie.getCredits())
                .rating(movie.getRating())
                .build();
    }
}
