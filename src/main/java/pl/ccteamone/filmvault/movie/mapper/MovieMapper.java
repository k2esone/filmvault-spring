package pl.ccteamone.filmvault.movie.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ccteamone.filmvault.movie.Movie;
import pl.ccteamone.filmvault.movie.dto.MovieDto;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDto mapToMovieDto(Movie movie);

    @InheritInverseConfiguration(name = "mapToMovieDto")
    Movie mapToMovie(MovieDto movieDto);

    Set<MovieDto> mapToMovieDtoSet(Set<Movie> movieSet);

    @InheritInverseConfiguration(name = "mapToMovieDtoSet")
    Set<Movie> mapToMovieSet(Set<MovieDto> movieDtoSet);

}
