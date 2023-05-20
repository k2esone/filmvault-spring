package pl.ccteamone.filmvault.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ccteamone.filmvault.movie.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
