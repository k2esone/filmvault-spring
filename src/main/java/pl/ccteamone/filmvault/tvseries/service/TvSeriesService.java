package pl.ccteamone.filmvault.tvseries.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.mapper.TvSeriesMapper;
import pl.ccteamone.filmvault.tvseries.repository.TvSeriesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TvSeriesService {
    private final TvSeriesRepository tvRepository;
    private final TvSeriesMapper tvSeriesMapper;


    public TvSeries save (TvSeries tvSeries) {
        return tvRepository.save(tvSeries);
    }

    public TvSeriesDto createTvSeries(TvSeriesDto tvSeriesDto) {
        TvSeries tvSeriesFromDto = tvSeriesMapper.mapToTvSeries(tvSeriesDto);
        TvSeries tvSeries = TvSeries.builder()
                .title(tvSeriesFromDto.getTitle())
                .description(tvSeriesFromDto.getDescription())
                .genre(tvSeriesFromDto.getGenre())
                .posterPath(tvSeriesFromDto.getPosterPath())
                .adult(tvSeriesFromDto.isAdult())
                .originCountry(tvSeriesFromDto.getOriginCountry())
                .firstAirDate(tvSeriesFromDto.getFirstAirDate())
                .lastAirDate(tvSeriesFromDto.getLastAirDate())
                .region(tvSeriesFromDto.getRegion())
                .seasons(tvSeriesFromDto.getSeasons())
                .episodes(tvSeriesFromDto.getEpisodes())
                .vodPlatforms(tvSeriesFromDto.getVodPlatforms())
                .apiID(tvSeriesFromDto.getApiID())
                .build();
        return tvSeriesMapper.mapToTvSeriesDto(tvRepository.save(tvSeries));
    }

    public List<TvSeriesDto> getTvSeriesDtoList() {
        return StreamSupport.stream(tvRepository.findAll().spliterator(), false)
                .map(tvSeriesMapper::mapToTvSeriesDto).collect(Collectors.toList());
    }

    public TvSeriesDto getTvSeriesDtoById(Long id) {
        Optional<TvSeries> tvSeries = tvRepository.findById(id);
        return tvSeriesMapper.mapToTvSeriesDto(tvSeries.orElseThrow(() -> new RuntimeException("Tv Series id=" + id + " not found")));
    }

    public TvSeriesDto updateTvSeries(Long tvseriesId, TvSeriesDto tvSeriesDto) {
        TvSeries tvSeries = tvRepository.findById(tvseriesId)
                .orElseThrow(() -> new EntityNotFoundException("Tv Series id=" + tvseriesId + " not found"));

        TvSeries series = tvSeriesMapper.mapToTvSeries(tvSeriesDto);

        if(series.getTitle() != null) {
            tvSeries.setTitle(series.getTitle());
        }
        if(series.getOverview() != null) {
            tvSeries.setOverview(series.getOverview());
        }
        if(series.getGenre() != null) {
            tvSeries.setGenre(series.getGenre());
        }
        if(series.getPosterPath() != null) {
            tvSeries.setPosterPath(series.getPosterPath());
        }
        if(!series.isAdult()) {
            tvSeries.setAdult(series.isAdult());
        }
        if(series.getOriginCountry() != null) {
            tvSeries.setOriginCountry(series.getOriginCountry());
        }
        if(series.getFirstAirDate() != null) {
            tvSeries.setFirstAirDate(series.getFirstAirDate());
        }
        if(series.getLastAirDate() != null) {
            tvSeries.setLastAirDate(series.getLastAirDate());
        }
        if(series.getSeasons() != 0) {
            tvSeries.setSeasons(series.getSeasons());
        }
        if(series.getEpisodes() != 0) {
            tvSeries.setEpisodes(series.getEpisodes());
        }
        if(series.getRegion() != null) {
            tvSeries.setRegion(series.getRegion());
        }
        if(series.getVodPlatforms() != null) {
            tvSeries.setVodPlatforms(series.getVodPlatforms());
        }
        if(series.getApiID() != null) {
            tvSeries.setApiID(series.getApiID());
        }
        return tvSeriesMapper.mapToTvSeriesDto(tvRepository.save(tvSeries));
    }

    public void deleteTvSeries(Long tvseriesId) {
        try {
            tvRepository.deleteById(tvseriesId);

        } catch (Exception e) {
            throw new EntityNotFoundException("Tv Series id=" + tvseriesId + " not found");
        }
    }
}
