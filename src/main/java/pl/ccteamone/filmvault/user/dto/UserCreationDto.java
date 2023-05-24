package pl.ccteamone.filmvault.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreationDto {
    private String email;
    private String password;
    private String username;
}
