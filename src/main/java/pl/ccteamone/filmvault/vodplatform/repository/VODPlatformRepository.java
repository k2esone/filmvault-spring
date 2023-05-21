package pl.ccteamone.filmvault.vodplatform.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.util.Optional;
import java.util.UUID;

public interface VODPlatformRepository extends CrudRepository<VODPlatform, UUID> {

    Optional<VODPlatform> findByApiID(String apiID);

}
