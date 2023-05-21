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

    public List<TvSeriesDto> getTvSeriesDtoList() {
        return StreamSupport.stream(tvRepository.findAll().spliterator(), false).map(TvSeriesMapper::tvSeriesToDto).collect(Collectors.toList());
    }

    public TvSeriesDto getTvSeriesDtoById(UUID id) {
        Optional<TvSeries> tvSeries = tvRepository.findById(id);
        return TvSeriesMapper.tvSeriesToDto(tvSeries.orElseThrow(() -> new RuntimeException("Tv Series id=" + id + " not found")));
    }
}
