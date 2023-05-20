package pl.ccteamone.filmvault.vodplatform.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class VODPlatformDto {
    private UUID id;
    private String name;
    private String logoPath;
    private String vodURL;
    private boolean isAvailable;
    private String apiID;
}
