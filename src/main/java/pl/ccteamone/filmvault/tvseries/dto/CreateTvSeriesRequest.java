package pl.ccteamone.filmvault.tvseries.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.user.MyUser;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTvSeriesRequest {


    private String nameR;
    private String descriptionR;
    private String genreR;
    private String posterR;
    private boolean adultR;
    private String originR;
    private LocalDate firstAirDateR;
    private LocalDate lastAirDateR;
    private int seasonsR;

    private Region regionR;
    private Set<VODPlatform> platformsR;
    private Set<MyUser> myUsers;

    private Long apiIDR;
}
