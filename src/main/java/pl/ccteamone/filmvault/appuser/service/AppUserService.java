package pl.ccteamone.filmvault.appuser.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.appuser.AppUser;
import pl.ccteamone.filmvault.appuser.dto.AppUserDto;
import pl.ccteamone.filmvault.appuser.dto.AppUserProfileDto;
import pl.ccteamone.filmvault.appuser.mapper.AppUserMapper;
import pl.ccteamone.filmvault.appuser.repository.AppUserRepository;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.repository.MovieRepository;
import pl.ccteamone.filmvault.movie.service.MovieService;
import pl.ccteamone.filmvault.tvseries.dto.TvSeriesDto;
import pl.ccteamone.filmvault.tvseries.service.TvSeriesService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final MovieRepository movieRepository;
    private final MovieService movieService;
    private final TvSeriesService tvSeriesService;

//    public AppUserDto createAppUser(AppUserCreationDto appUserCreationDto) {
//        AppUser appUser = AppUser.builder()
//                .email(appUserCreationDto.getEmail())
//                .password(appUserCreationDto.getPassword())
//                .username(appUserCreationDto.getUsername())
//                .build();
//        return appUserMapper.mapToAppUserDto(appUserRepository.save(appUser));
//    }

    public AppUserDto addMovieByTitle(String username, String movieTitle) {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found, username: " + username));
        Movie movie = movieRepository.findByTitleContainingIgnoreCase(movieTitle)
                .stream().findAny()
                .orElseThrow(() -> new EntityNotFoundException("Movie not found, title: " + movieTitle));
        appUser.getMovies().add(movie);
        appUserRepository.save(appUser);
        return appUserMapper.mapToAppUserDto(appUser);
    }

    public AppUserDto addMovieByMovieIdToUser(String username, Long movieId) {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found, username: " + username));
        Movie movie = movieRepository.findById(movieId)
                .stream().findAny()
                .orElseThrow(() -> new EntityNotFoundException("Movie not found, title: " + movieId));
        appUser.getMovies().add(movie);
        appUserRepository.save(appUser);
        return appUserMapper.mapToAppUserDto(appUser);
    }

    public AppUserDto addMovieByApiId(String username, Long movieApiId) {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found, username: " + username));
        Movie movie = movieRepository.findByApiID(movieApiId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found, title: " + movieApiId));
        appUser.getMovies().add(movie);
        appUserRepository.save(appUser);
        return appUserMapper.mapToAppUserDto(appUser);
    }

    public List<AppUserProfileDto> getUsersList() {
        return appUserRepository.findAll()
                .stream().map(appUserMapper::mapToAppUserProfileDto)
                .toList();
    }

    public AppUserDto getUserById(Long userId) {
        return appUserRepository.findById(userId)
                .stream().map(appUserMapper::mapToAppUserDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found, id: " + userId));
    }

    public AppUserDto getUserByName(String username) {
        return appUserRepository.findByUsername(username)
                .stream().map(appUserMapper::mapToAppUserDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found, id: " + username));
    }

    public AppUserDto updateUser(String username, AppUserDto appUserDto) {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("AppUser not found, name: " + username));

        AppUser appUser = appUserMapper.mapToAppUser(appUserDto);
        if (appUser.getEmail() != null) {
            user.setEmail(appUser.getEmail());
        }
        if (appUser.getName() != null) {
            user.setName(appUser.getName());
        }
        if (appUser.getPassword() != null) {
            user.setPassword(appUser.getPassword());
        }
        if (appUser.getSurname() != null) {
            user.setSurname(appUser.getSurname());
        }
        if (appUser.getBirthDate() != null) {
            user.setBirthDate(appUser.getBirthDate());
        }
        if (appUser.getGender() != null) {
            user.setGender(appUser.getGender());
        }
        if (appUser.getProfilePic() != null) {
            user.setProfilePic(appUser.getProfilePic());
        }
        if (appUser.getRegion() != null) {
            user.setRegion(appUser.getRegion());
        }
        if (appUser.getMovies() != null) {
            user.setMovies(appUser.getMovies());
        }
        if (appUser.getVodPlatforms() != null) {
            user.setVodPlatforms(appUser.getVodPlatforms());
        }
        if (appUser.getTvSeries() != null) {
            user.setTvSeries(appUser.getTvSeries());
        }
        return appUserMapper.mapToAppUserDto(appUserRepository.save(user));
    }

    public void deleteUserById(Long userId) {
        try {
            appUserRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("AppUser not found, id: " + userId);
        }
    }

    public AppUserDto addMovieByID(String username, Long movieID) {
        AppUserDto user = getUserByName(username);
        MovieDto movie = movieService.getMovieById(movieID);
        Set<MovieDto> userMovies = user.getMovies();
        if (userMovies != null) {
            userMovies.add(movie);
        } else {
            userMovies = new HashSet<>();
            userMovies.add(movie);
            user.setMovies(userMovies);
        }
        return updateUser(username, user);
    }

    public AppUserDto addTvSeriesByID(String username, Long tvseriesID) {
        AppUserDto user = getUserByName(username);
        TvSeriesDto tvSeries = tvSeriesService.getTvSeriesById(tvseriesID);
        Set<TvSeriesDto> userSeries = user.getTvSeries();
        if(userSeries != null) {
            userSeries.add(tvSeries);
        } else {
            userSeries = new HashSet<>();
            userSeries.add(tvSeries);
            user.setTvSeries(userSeries);
        }
        return updateUser(username,user);
    }
}
