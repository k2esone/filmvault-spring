package pl.ccteamone.filmvault.region.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RegionDto {
    private UUID id;
    private String city;
    private String country;
    private String flag;
}
