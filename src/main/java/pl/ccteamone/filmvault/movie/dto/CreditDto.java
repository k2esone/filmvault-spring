package pl.ccteamone.filmvault.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditDto {


    @JsonProperty("cast")
    private Cast[] cast;

    @Getter
    public static class Cast {
        private String name;
        private String character;

    }

}
