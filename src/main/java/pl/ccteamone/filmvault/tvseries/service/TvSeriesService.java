package pl.ccteamone.filmvault.tvseries.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.CreateMovieRequest;
import pl.ccteamone.filmvault.movie.dto.MovieResponse;
import pl.ccteamone.filmvault.movie.dto.UpdateMovieResponse;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.CreateTvSeriesRequest;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesResponse;
import pl.ccteamone.filmvault.tvseries.dto.UpdateTvSeriesResponse;
import pl.ccteamone.filmvault.tvseries.mapper.TvSeriesMapper;
import pl.ccteamone.filmvault.tvseries.repository.TvSeriesRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TvSeriesService {
    private final TvSeriesRepository tvRepository;

    public TvSeriesService(TvSeriesRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    public TvSeriesResponse createTvSeries(CreateTvSeriesRequest request) {
        TvSeries tvSeries = TvSeries.builder()
                .name(request.getNameR())
                .description(request.getDescriptionR())
                .genre(request.getGenreR())
                .poster(request.getPosterR())
                .adult(request.isAdultR())
                .origin(request.getOriginR())
                .firstAirDate(request.getFirstAirDateR())
                .lastAirDate(request.getLastAirDateR())
                .seasons(request.getSeasonsR())
                .region(request.getRegionR())
                .platforms(request.getPlatformsR())
                .myUsers(request.getMyUsers())
                .apiID(request.getApiIDR())
                .build();

        tvRepository.save(tvSeries);

        return TvSeriesMapper.mapTvSeriesToTvSeriesResponse(tvSeries);
    }

    public List<TvSeriesResponse> getTvSeriesList() {
        return tvRepository.findAll()
                .stream().map(TvSeriesMapper::mapTvSeriesToTvSeriesResponse)
                .toList();
    }

    public TvSeriesResponse getTvSeriesById(UUID tvSeriesId) {
        return tvRepository.findById(tvSeriesId)
                .stream().map(TvSeriesMapper::mapTvSeriesToTvSeriesResponse)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("tvSeries not found, id: " + tvSeriesId));
    }


    public UpdateTvSeriesResponse updateTvSeries(UUID tvSeriesId, CreateTvSeriesRequest request) {
        TvSeries tvSeries = tvRepository.findById(tvSeriesId)
                .orElseThrow(() -> new EntityNotFoundException("tvSeries not found, id: " + tvSeriesId));
        tvSeries.setName(request.getNameR());
        tvSeries.setDescription(request.getDescriptionR());
        tvSeries.setGenre(request.getGenreR());
        tvSeries.setPoster(request.getPosterR());
        tvSeries.setAdult(request.isAdultR());
        tvSeries.setOrigin(request.getOriginR());
        tvSeries.setFirstAirDate(request.getFirstAirDateR());
        tvSeries.setLastAirDate(request.getLastAirDateR());
        tvSeries.setSeasons(request.getSeasonsR());
        tvSeries.setRegion(request.getRegionR());
        tvSeries.setPlatforms(request.getPlatformsR());
        tvSeries.setMyUsers(request.getMyUsers());
        tvSeries.setApiID(request.getApiIDR());

        tvSeries = tvRepository.save(tvSeries);

        return TvSeriesMapper.tvSeriesToTvSeriesResponse(tvSeries);
    }

    public void deleteTvSeriesById(UUID tvSeriesId) {
        try {
            tvRepository.deleteById(tvSeriesId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("tvSeries not found, id: " + tvSeriesId);
        }
    }

}