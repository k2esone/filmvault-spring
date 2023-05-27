package pl.ccteamone.filmvault.tvseries.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.mapper.TvSeriesMapper;
import pl.ccteamone.filmvault.tvseries.repository.TvSeriesRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    public TvSeriesDto saveFromDto (TvSeriesDto tvSeriesDto) {
        TvSeries tvSeries = tvSeriesMapper.mapToTvSeries(tvSeriesDto);
        return tvSeriesMapper.mapToTvSeriesDto(tvRepository.save(tvSeries));
    }

    public List<TvSeriesDto> getTvSeriesDtoList() {
        return StreamSupport.stream(tvRepository.findAll().spliterator(), false).map(tvSeriesMapper::mapToTvSeriesDto).collect(Collectors.toList());
    }

    public TvSeriesDto getTvSeriesDtoById(UUID id) {
        Optional<TvSeries> tvSeries = tvRepository.findById(id);
        return tvSeriesMapper.mapToTvSeriesDto(tvSeries.orElseThrow(() -> new RuntimeException("Tv Series id=" + id + " not found")));
    }

}
