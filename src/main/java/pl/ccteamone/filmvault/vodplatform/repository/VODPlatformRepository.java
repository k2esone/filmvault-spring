package pl.ccteamone.filmvault.vodplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VODPlatformRepository extends JpaRepository<VODPlatform, UUID> {

    Optional<VODPlatform> findByApiID(String apiID);

}
