package pl.ccteamone.filmvault.vodplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;

import java.util.Optional;
import java.util.Set;

public interface VODPlatformRepository extends JpaRepository<VODPlatform, Long> {
    boolean existsByNameIgnoreCase(String name);
    Set<VODPlatform> findAllByActiveIsTrue();
    Optional<VODPlatform> findByNameAndActiveIsTrue(String name);
    boolean existsByNameIgnoreCaseAndActiveIsTrue(String name);

}
