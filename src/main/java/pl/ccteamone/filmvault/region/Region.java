package pl.ccteamone.filmvault.region;

import jakarta.persistence.*;
import lombok.*;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryCode;
    private String country;
    private String flag;
    @ManyToMany
    private Set<VODPlatform> vodPlatforms;
}