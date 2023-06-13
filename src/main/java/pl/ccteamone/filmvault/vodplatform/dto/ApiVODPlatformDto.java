package pl.ccteamone.filmvault.vodplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiVODPlatformDto {

    private Long id;
    private String name;
    private String logoPath;
    private String vodURL;
    private boolean active;


}
