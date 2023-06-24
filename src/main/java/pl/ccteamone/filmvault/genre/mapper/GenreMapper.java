package pl.ccteamone.filmvault.genre.mapper;

import org.mapstruct.Mapper;
import pl.ccteamone.filmvault.genre.Genre;
import pl.ccteamone.filmvault.genre.dto.GenreDto;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    Genre mapToGenre(GenreDto genreDto);

    GenreDto mapToGenreDto(Genre genre);

    Set<Genre> mapToGenreSet(Set<GenreDto> genreDtoSet);

    Set<GenreDto> mapToGenreDtoSet(Set<Genre> genres);


}