package pl.ccteamone.filmvault.tvseries;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    private Long id;
    private String name;
    private String description;
    private String genre;
    private String poster;
    private boolean adult;
    private String origin;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "dd/MM/yyyy")
    private LocalDate firstAirDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "dd/MM/yyyy")
    private LocalDate lastAirDate;
    private int seasons;

    @ManyToMany
    private Set<Region> regions;
    @ManyToMany
    private Set<VODPlatform> platforms;

    private Long apiID;
}
