package pl.ccteamone.filmvault.tvseries.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ccteamone.filmvault.tvseries.TvSeries;

import java.util.List;

public interface TvSeriesRepository extends CrudRepository<TvSeries, Long> {
    List<TvSeries> findByNameContainingIgnoreCase(String query);


}
