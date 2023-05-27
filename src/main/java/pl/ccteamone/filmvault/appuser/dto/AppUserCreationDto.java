package pl.ccteamone.filmvault.appuser.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserCreationDto {
    private String email;
    private String password;
    private String username;
}
