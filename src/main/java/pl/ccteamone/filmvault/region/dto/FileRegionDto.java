package pl.ccteamone.filmvault.region.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileRegionDto {
    private Long id;
    @JsonProperty("iso_3166_1")
    private String countryCode;
    @JsonProperty("english_name")
    private String country;
    private String flag;
}
