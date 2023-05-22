package pl.ccteamone.filmvault.movie.mapper;

import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.MovieResponse;
import pl.ccteamone.filmvault.movie.dto.UpdateMovieResponse;

import java.util.stream.Collectors;

public class MovieMapper {

    public static MovieResponse mapMovieToMovieResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getPosterPath(),
                movie.getOverview(),
                movie.getReleaseDate(),
                movie.getRuntime(),
                movie.getCredits(),
                movie.getRating(),
                movie.getMyUsers().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                movie.getVodPlatforms().stream().map(i -> i.getId()).collect(Collectors.toSet())

        );
    }
    public static UpdateMovieResponse movieToMovieResponse(Movie movie) {
        return new UpdateMovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getPosterPath(),
                movie.getOverview(),
                movie.getReleaseDate(),
                movie.getRuntime(),
                movie.getCredits(),
                movie.getRating(),
                movie.getMyUsers().stream().map(i ->i.getId()).collect(Collectors.toSet()),
                movie.getVodPlatforms().stream().map(i ->i.getId()).collect(Collectors.toSet())
        );
    }
}
