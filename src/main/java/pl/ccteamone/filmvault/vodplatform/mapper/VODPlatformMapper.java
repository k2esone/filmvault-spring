package pl.ccteamone.filmvault.vodplatform.mapper;

import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.MovieResponse;
import pl.ccteamone.filmvault.movie.dto.UpdateMovieResponse;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;
import pl.ccteamone.filmvault.vodplatform.dto.UpdateVODPlatformResponse;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformResponse;

import java.util.stream.Collectors;

public class VODPlatformMapper {

    public static VODPlatformResponse mapVODPlatformToVODPlatformResponse(VODPlatform vodPlatform) {
        return new VODPlatformResponse(
                vodPlatform.getId(),
                vodPlatform.getName(),
                vodPlatform.getLogoPath(),
                vodPlatform.getVodURL(),
                vodPlatform.isAvailable(),
                vodPlatform.getApiID(),
                vodPlatform.getMyUsers().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                vodPlatform.getMovies().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                vodPlatform.getTvSeries().stream().map(i -> i.getId()).collect(Collectors.toSet())

        );
    }
    public static UpdateVODPlatformResponse vodPlatformToVODPlatformResponse(VODPlatform vodPlatform) {
        return new UpdateVODPlatformResponse(
                vodPlatform.getId(),
                vodPlatform.getName(),
                vodPlatform.getLogoPath(),
                vodPlatform.getVodURL(),
                vodPlatform.isAvailable(),
                vodPlatform.getApiID(),
                vodPlatform.getMyUsers().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                vodPlatform.getMovies().stream().map(i -> i.getId()).collect(Collectors.toSet()),
                vodPlatform.getTvSeries().stream().map(i -> i.getId()).collect(Collectors.toSet())

        );
    }
}