package pl.ccteamone.filmvault.tvseries;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TvSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 512)
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;
    private String genre;
    private boolean adult;
    @JsonProperty("original_language")
    private String originCountry;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "dd/MM/yyyy")
    @JsonProperty("first_air_date")
    private LocalDate firstAirDate;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "dd/MM/yyyy")
    @JsonProperty("last_air_date")
    private LocalDate lastAirDate;
    @JsonProperty("number_of_seasons")
    private int seasons;
    private int episodes;
    private Long apiID;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Region region;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<VODPlatform> vodPlatforms;



}
