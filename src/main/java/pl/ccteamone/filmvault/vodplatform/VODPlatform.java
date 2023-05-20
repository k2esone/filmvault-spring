package pl.ccteamone.filmvault.vodplatform;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VODPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Setter(AccessLevel.PRIVATE)
    private UUID id;
    private String name;
    private String logoPath;
    private String vodURL;
    private boolean isAvailable;
    private String apiID;



}
