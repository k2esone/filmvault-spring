package pl.ccteamone.filmvault.appuser;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.region.Region;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;
    private String name;
    private String surname;
    private LocalDate birthDate;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String profilePic;
    private String role;
    private boolean isActive;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime lastActivity;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Region region;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<Movie> movies;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<TvSeries> tvSeries;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<VODPlatform> vodPlatforms;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleType.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public Region getRegion() {
        return region;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public Set<TvSeries> getTvSeries() {
        return tvSeries;
    }

    public Set<VODPlatform> getVodPlatforms() {
        return vodPlatforms;
    }

    public RoleType getRoleType() {
        return roleType;
    }
}





