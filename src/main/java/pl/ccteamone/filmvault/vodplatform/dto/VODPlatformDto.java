package pl.ccteamone.filmvault.vodplatform.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VODPlatformDto {
    private Long id;
    private String name;
    private String logoPath;
    private String vodURL;
    private boolean active;

}
