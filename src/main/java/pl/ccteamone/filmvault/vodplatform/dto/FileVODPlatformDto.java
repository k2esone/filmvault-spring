package pl.ccteamone.filmvault.vodplatform.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


    //https://image.tmdb.org/t/p/original/url.jpg
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileVODPlatformDto {

    private Long id;
    @JsonProperty("provider_name")
    private String name;
    @JsonProperty("logo_path")
    private String logoPath;
    private String vodURL;
    private boolean active;


}
