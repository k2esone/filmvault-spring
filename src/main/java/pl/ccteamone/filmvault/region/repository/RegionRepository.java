package pl.ccteamone.filmvault.region.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ccteamone.filmvault.region.Region;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByCountryCodeIgnoreCase(String code);
    Optional<Region> findByCountryCode(String code);
}
