package pl.ccteamone.filmvault.user.location.mapper;

import pl.ccteamone.filmvault.user.location.Location;
import pl.ccteamone.filmvault.user.location.dto.LocationResponse;
import pl.ccteamone.filmvault.user.location.dto.UpdateLocationResponse;

import java.util.stream.Collectors;

public class LocationMapper {

    public static LocationResponse mapLocationToLocationResponse(Location location) {
        return new LocationResponse(
                location.getId(),
                location.getCity(),
                location.getCountry(),
                location.getFlag(),
                location.getUsers().stream().map(i -> i.getId()).collect(Collectors.toSet())
        );
    }

    public static UpdateLocationResponse locationToLocationResponse(Location location) {
        return new UpdateLocationResponse(
                location.getId(),
                location.getCity(),
                location.getCountry(),
                location.getFlag(),
                location.getUsers().stream().map(i->i.getId()).collect(Collectors.toSet())
        );
    }
}
