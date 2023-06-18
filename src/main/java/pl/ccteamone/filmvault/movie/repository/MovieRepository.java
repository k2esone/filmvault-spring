package pl.ccteamone.filmvault.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ccteamone.filmvault.movie.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
    Optional<Movie> findByTitleIgnoreCase(String title);
    Optional<Movie> findByApiID(Long id);
    boolean existsByApiID(Long id);
    List<Movie> findByTitleContainingIgnoreCase(String query);

}
