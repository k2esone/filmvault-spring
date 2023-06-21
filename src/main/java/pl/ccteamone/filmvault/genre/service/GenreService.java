package pl.ccteamone.filmvault.genre.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ccteamone.filmvault.genre.Genre;
import pl.ccteamone.filmvault.genre.dto.GenreDto;
import pl.ccteamone.filmvault.genre.mapper.GenreMapper;
import pl.ccteamone.filmvault.genre.repository.GenreRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreDto createGenre(GenreDto genreDto) {
        Genre genreToUpdate = genreRepository.save(genreMapper.mapToGenre(genreDto));
        return genreMapper.mapToGenreDto(genreToUpdate);
    }

    public GenreDto getGenreById(Long id) {
        Genre genreResult = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre id " + id + " not found"));
        return genreMapper.mapToGenreDto(genreResult);
    }

    public Set<GenreDto> getGenreSet() {
        return genreMapper.mapToGenreDtoSet(genreRepository.findAll().stream().collect(Collectors.toSet()));
    }


    public GenreDto updateGenre(Long id, GenreDto genreDto) {
        Genre toUpdate = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre id " + id + " not found"));
        if (genreDto.getName() != null) {
            toUpdate.setName(genreDto.getName());
        }
        return genreMapper.mapToGenreDto(genreRepository.save(toUpdate));
    }

    public GenreDto findByGenreName(String name) {
        Genre genreResult = genreRepository.findByName(name).orElseThrow(() -> new RuntimeException("Genre \"" + name + "\" not found"));
        return genreMapper.mapToGenreDto(genreResult);
    }

    public boolean existsByGenreName(String name) {
        return genreRepository.existsByNameIgnoreCase(name);
    }

    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }


}
