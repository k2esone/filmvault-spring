package pl.ccteamone.filmvault.region.dto;

import lombok.Builder;
import lombok.Data;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;

import java.util.Set;

@Data
@Builder
public class RegionDto {
    private Long id;
    private String city;
    private String country;
    private String flag;
    private Set<AppUserDto> appUsers;
}
