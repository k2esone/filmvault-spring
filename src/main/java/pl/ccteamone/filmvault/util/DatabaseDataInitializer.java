package pl.ccteamone.filmvault.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.dto.MovieDtoPage;
import pl.ccteamone.filmvault.movie.service.MovieApiService;
import pl.ccteamone.filmvault.movie.service.MovieService;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDtoPage;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesApiService;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesService;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseDataInitializer implements CommandLineRunner {
    private final TvSeriesApiService tvSeriesApiService;
    private final TvSeriesService tvSeriesService;
    private final MovieApiService movieApiService;
    private final MovieService movieService;


    @Scheduled(cron = "0 0 0 * * 0")
    private void updateDatabaseRecords() {
        List<MovieDto> movieUpdate = supplyDiscoverMovieDtoList();
        List<TvSeriesDto> tvSeriesUpdate = supplyDiscoverTvSeriesDtoList();

        if(!movieUpdate.isEmpty()) {
            for (int i = 0; i < movieUpdate.size(); i++) {
                MovieDto movie = movieUpdate.get(i);

                if(movie.getApiID() != null && !movieService.existsByApiID(movie.getApiID())) {
                    movie = movieApiService.getApiMovie(movie.getApiID());
                    movieService.createMovie(movie);
                }
            }
        }

        if(!tvSeriesUpdate.isEmpty()) {
            for (int i = 0; i < tvSeriesUpdate.size(); i++) {
                TvSeriesDto tvSeries = tvSeriesUpdate.get(i);

                if(tvSeries.getId() != null && !tvSeriesService.existsByApiID(tvSeries.getId())) {
                    tvSeries = tvSeriesApiService.getApiTvSeries(tvSeries.getId());
                    tvSeriesService.createTvSeries(tvSeries);
                }
            }
        }
        log.info("Auto Update Method: done");
    }

    private List<MovieDto> supplyDiscoverMovieDtoList() {
        List<MovieDto> movieUpdateList = new ArrayList<>();
        MovieDtoPage page;
        for (int i = 1; i < 11; i++) {
            page = movieApiService.getMovieDiscoverPage(i);
            if(page == null) {
                break;
            }
            movieUpdateList.addAll(page.getMovies());
        }
        return movieUpdateList;
    }

    private List<TvSeriesDto> supplyDiscoverTvSeriesDtoList() {
        List<TvSeriesDto> tvSeriesUpdateList = new ArrayList<>();
        TvSeriesDtoPage page;
        for (int i = 1; i < 11; i++) {
            page = tvSeriesApiService.getTvSeriesDiscoverPage(i);
            if(page == null) {
                break;
            }
            tvSeriesUpdateList.addAll(page.getTvSeries());
        }
        return tvSeriesUpdateList;


    }
    //TODO: uncomment to fill the DB -  commented to spare API usage
    @Override
    public void run(String... args) throws Exception {
        //updateDatabaseRecords();
    }
}
