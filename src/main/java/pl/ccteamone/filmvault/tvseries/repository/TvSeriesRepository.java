package pl.ccteamone.filmvault.tvseries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import java.util.UUID;

@Repository
public interface TvSeriesRepository extends JpaRepository<TvSeries, UUID> {


}
