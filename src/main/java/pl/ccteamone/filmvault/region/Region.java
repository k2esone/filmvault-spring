package pl.ccteamone.filmvault.region;

import jakarta.persistence.*;
import lombok.*;
import pl.ccteamone.filmvault.appuser.AppUser;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String city;
    private String country;
    private String flag;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "region")
    private Set<AppUser> appUsers;
}

