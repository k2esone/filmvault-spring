package pl.ccteamone.filmvault.tvseries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;

import java.util.Optional;

import java.util.List;

public interface TvSeriesRepository extends JpaRepository<TvSeries,Long> {
    Optional<TvSeries> findByName(String name);
    Optional<TvSeries> findByNameIgnoreCase(String name);
    Optional<TvSeries> findByApiID(Long id);
    boolean existsByApiID(Long id);
    List<TvSeries> findByNameContainingIgnoreCase(String query);


}
