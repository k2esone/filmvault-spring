package pl.ccteamone.filmvault.genre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ccteamone.filmvault.genre.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String genre);
    boolean existsByNameIgnoreCase(String name);
}
