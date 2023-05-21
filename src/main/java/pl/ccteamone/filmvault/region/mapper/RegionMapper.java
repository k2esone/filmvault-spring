package pl.ccteamone.filmvault.region.mapper;

import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.region.dto.UpdateRegionResponse;
import pl.ccteamone.filmvault.region.dto.RegionResponse;

import java.util.stream.Collectors;

public class RegionMapper {

    public static RegionResponse mapLocationToLocationResponse(Region region) {
        return new RegionResponse(
                region.getId(),
                region.getCity(),
                region.getCountry(),
                region.getFlag(),
                region.getMyUsers().stream().map(i -> i.getId()).collect(Collectors.toSet())
        );
    }

    public static UpdateRegionResponse locationToLocationResponse(Region region) {
        return new UpdateRegionResponse(
                region.getId(),
                region.getCity(),
                region.getCountry(),
                region.getFlag(),
                region.getMyUsers().stream().map(i->i.getId()).collect(Collectors.toSet())
        );
    }
}
