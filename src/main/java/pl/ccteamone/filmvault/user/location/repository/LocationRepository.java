package pl.ccteamone.filmvault.user.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ccteamone.filmvault.user.location.Location;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
}
