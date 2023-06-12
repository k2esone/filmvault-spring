package pl.ccteamone.filmvault.movie.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.ApiMovieDto;
import pl.ccteamone.filmvault.movie.dto.ApiMovieDtoPage;
import pl.ccteamone.filmvault.movie.dto.MovieDto;
import pl.ccteamone.filmvault.movie.dto.MovieDtoPage;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDto mapToMovieDto(Movie movie);

    @InheritInverseConfiguration(name = "mapToMovieDto")
    Movie mapToMovie(MovieDto movieDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "apiID")
    MovieDto mapToMovieDto(ApiMovieDto apiMovieDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "apiID")
    List<MovieDto> mapToMovieDtoList(List<ApiMovieDto> apiMovieDtoList);

    MovieDtoPage mapToMovieDtoPage(ApiMovieDtoPage apiMovieDtoPage);

    Set<MovieDto> mapToMovieDtoSet(Set<Movie> movieSet);

    @InheritInverseConfiguration(name = "mapToMovieDtoSet")
    Set<Movie> mapToMovieSet(Set<MovieDto> movieDtoSet);

}
