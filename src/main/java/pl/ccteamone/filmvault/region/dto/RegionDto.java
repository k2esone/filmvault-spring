package pl.ccteamone.filmvault.region.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegionDto {
    private Long id;
    private String countryCode;
    private String country;
    private String flag;
}
