package pl.ccteamone.filmvault.tvseries.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.tvseries.TvSeries;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.mapper.TvSeriesMapper;
import pl.ccteamone.filmvault.tvseries.repository.TvSeriesRepository;

import java.util.*;
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

    public TvSeriesDto createTvSeries(TvSeriesDto create) {
        TvSeries tvSeriesFromDto = tvSeriesMapper.mapToTvSeries(create);
        TvSeries tvSeries = TvSeries.builder()
                .name(tvSeriesFromDto.getName())
                .overview(tvSeriesFromDto.getOverview())
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

    public List<TvSeriesDto> getTvSeriesList() {
        return StreamSupport.stream(tvRepository.findAll().spliterator(), false)
                .map(tvSeriesMapper::mapToTvSeriesDto).collect(Collectors.toList());
    }

    public TvSeriesDto getTvSeriesById(Long id) {
        Optional<TvSeries> tvSeries = tvRepository.findById(id);
        return tvSeriesMapper.mapToTvSeriesDto(tvSeries.orElseThrow(() -> new RuntimeException("Tv Series id=" + id + " not found")));
    }

    public TvSeriesDto updateTvSeries(Long tvseriesId, TvSeriesDto tvSeriesDto) {
        TvSeries tvSeries = tvRepository.findById(tvseriesId)
                .orElseThrow(() -> new EntityNotFoundException("Tv Series id=" + tvseriesId + " not found"));

        TvSeries series = tvSeriesMapper.mapToTvSeries(tvSeriesDto);

        if(series.getName() != null) {
            tvSeries.setName(series.getName());
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

    public boolean existsByApiID(Long id) {
        return tvRepository.existsByApiID(id);
    }

    public TvSeriesDto findTvSeriesByApiID(Long id) {
        return tvSeriesMapper.mapToTvSeriesDto(tvRepository.findByApiID(id)
                .orElseThrow(() -> new RuntimeException("Tv Series not found")));
    }

    public List<TvSeriesDto> searchTvSeries(String query) {
        List<TvSeries> tvSeries = tvRepository.findByNameContainingIgnoreCase(query.substring(0, 1));
        List<TvSeriesDto> similarTvSeries = new ArrayList<>();

        for (TvSeries tvSerie : tvSeries) {
            String name = tvSerie.getName().toLowerCase();
            String lowercaseQuery = query.toLowerCase();

            List<String> nameNGrams = generateNGrams(name, 2); // licznik prawdopodobieństwa dla title
            List<String> queryNGrams = generateNGrams(lowercaseQuery, 2);  // licznik prawdopodobieństwa dla query

            int commonNGrams = countCommonNGrams(nameNGrams, queryNGrams);

            if (commonNGrams >= 2) { // <-- Licznik prawdopodobieństwa
                TvSeries tvSeries1 = new TvSeries();
                tvSeries1.setName(tvSerie.getName());
                similarTvSeries.add(tvSeriesMapper.mapToTvSeriesDto(tvSeries1));
            }

            if (similarTvSeries.size() == 5) {
                break;
            }
        }

        return similarTvSeries;
    }

    private List<String> generateNGrams(String input, int n) {
        List<String> nGrams = new ArrayList<>();

        for (int i = 0; i <= input.length() - n; i++) {
            String nGram = input.substring(i, i + n);
            nGrams.add(nGram);
        }

        return nGrams;
    }

    private int countCommonNGrams(List<String> nGrams1, List<String> nGrams2) {
        Set<String> set1 = new HashSet<>(nGrams1);
        Set<String> set2 = new HashSet<>(nGrams2);

        set1.retainAll(set2);

        return set1.size();
    }
}
