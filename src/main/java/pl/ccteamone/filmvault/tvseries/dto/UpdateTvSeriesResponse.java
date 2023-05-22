package pl.ccteamone.filmvault.tvseries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.user.MyUser;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTvSeriesResponse {

    private UUID idR;
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
    private Set<UUID> platformsR;
    private Set<UUID> myUsers;

    private Long apiIDR;
}
