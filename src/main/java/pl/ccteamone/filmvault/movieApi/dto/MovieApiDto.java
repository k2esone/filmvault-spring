package pl.ccteamone.filmvault.movieApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieApiDto {

    @JsonProperty("original_title")
    private String title;

    private String overview;
//    @JsonProperty("belongs_to_collection")
//    private BelongsToCollection nalezydokolekcji;

    @JsonProperty("original_language")
    private String language;

    public static class BelongsToCollection {
        private String name;

        public BelongsToCollection() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
