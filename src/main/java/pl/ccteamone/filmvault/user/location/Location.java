package pl.ccteamone.filmvault.user.location;

import jakarta.persistence.*;
import lombok.*;
import pl.ccteamone.filmvault.user.user.User;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

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

