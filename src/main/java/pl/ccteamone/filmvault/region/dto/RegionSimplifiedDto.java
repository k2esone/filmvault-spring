package pl.ccteamone.filmvault.region.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionSimplifiedDto {
    private Long id;
    private String countryCode;
    private String country;
    private String flag;
}
