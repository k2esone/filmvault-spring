package pl.ccteamone.filmvault.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ccteamone.filmvault.movie.Movie;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
//    Optional<Movie> findByTitle(String apiID);
}
