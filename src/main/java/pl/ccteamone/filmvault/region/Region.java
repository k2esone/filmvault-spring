package pl.ccteamone.filmvault.region;

import jakarta.persistence.*;
import lombok.*;
import pl.ccteamone.filmvault.user.User;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String city;
    private String country;
    private String flag;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "location")
    private Set<User> users;
}

