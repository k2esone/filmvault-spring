package pl.ccteamone.filmvault.vodplatform.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.CreateMovieRequest;
import pl.ccteamone.filmvault.movie.dto.MovieResponse;
import pl.ccteamone.filmvault.movie.dto.UpdateMovieResponse;
import pl.ccteamone.filmvault.movie.mapper.MovieMapper;
import pl.ccteamone.filmvault.vodplatform.VODPlatform;
import pl.ccteamone.filmvault.vodplatform.dto.CreateVODPlatformRequest;
import pl.ccteamone.filmvault.vodplatform.dto.UpdateVODPlatformRequest;
import pl.ccteamone.filmvault.vodplatform.dto.UpdateVODPlatformResponse;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformResponse;
import pl.ccteamone.filmvault.vodplatform.mapper.VODPlatformMapper;
import pl.ccteamone.filmvault.vodplatform.repository.VODPlatformRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VODPlatformService {
    private final VODPlatformRepository platformRepository;

    public VODPlatformService(VODPlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public VODPlatformResponse createVODPlatform(CreateVODPlatformRequest request) {
        VODPlatform vodPlatform = VODPlatform.builder()
                .name(request.getNameR())
                .logoPath(request.getLogoPathR())
                .vodURL(request.getVodURLR())
                .isAvailable(request.isAvailableR())
                .apiID(request.getApiIDR())
                .myUsers(request.getMyUsersR())
                .movies(request.getMoviesR())
                .tvSeries(request.getTvSeriesR())
                .build();

        platformRepository.save(vodPlatform);

        return VODPlatformMapper.mapVODPlatformToVODPlatformResponse(vodPlatform);
    }

    public List<VODPlatformResponse> getVODPlatformList() {
        return platformRepository.findAll()
                .stream().map(VODPlatformMapper::mapVODPlatformToVODPlatformResponse)
                .toList();
    }

    public VODPlatformResponse getVODPlatformById(UUID vodPlatformId) {
        return platformRepository.findById(vodPlatformId)
                .stream().map(VODPlatformMapper::mapVODPlatformToVODPlatformResponse)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("vodPlatform not found, id: " + vodPlatformId));
    }


    public UpdateVODPlatformResponse updateVODPlatform(UUID vodPlatformId, UpdateVODPlatformRequest request) {
        VODPlatform vodPlatform = platformRepository.findById(vodPlatformId)
                .orElseThrow(() -> new EntityNotFoundException("vodPlatform not found, id: " + vodPlatformId));
vodPlatform.setName(request.getNameR());
vodPlatform.setLogoPath(request.getLogoPathR());
vodPlatform.setVodURL(request.getVodURLR());
vodPlatform.setAvailable(request.isAvailableR());
vodPlatform.setApiID(request.getApiIDR());
vodPlatform.setMyUsers(request.getMyUsersR());
vodPlatform.setMovies(request.getMoviesR());
vodPlatform.setTvSeries(request.getTvSeriesR());

        vodPlatform = platformRepository.save(vodPlatform);

        return VODPlatformMapper.vodPlatformToVODPlatformResponse(vodPlatform);
    }

    public void deleteVODPlatformById(UUID vodPlatformId) {
        try {
            platformRepository.deleteById(vodPlatformId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("vodPlatform not found, id: " + vodPlatformId);
        }
    }

}