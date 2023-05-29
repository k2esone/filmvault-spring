package pl.ccteamone.filmvault.vodplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.util.Optional;

public interface VODPlatformRepository extends JpaRepository<VODPlatform, Long> {

//    Optional<VODPlatform> findByApiID(String apiID);

}
