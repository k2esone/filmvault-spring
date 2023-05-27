package pl.ccteamone.filmvault.tvseries.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import java.util.UUID;

public interface TvSeriesRepository extends CrudRepository<TvSeries, Long> {


}
