package pl.ccteamone.filmvault.util.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ccteamone.filmvault.genre.dto.GenreDto;
import pl.ccteamone.filmvault.genre.mapper.GenreMapper;
import pl.ccteamone.filmvault.genre.service.GenreService;
import pl.ccteamone.filmvault.util.DataInitializer;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GenreFileUploader implements DataInitializer {

    private final GenreService genreService;
    private static final String JSON_FILE_PATH = "/app/imports/genre.json";


    public void initializeGenreJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File genresJson = new File(JSON_FILE_PATH);
            List<GenreDto> genres = mapper.readValue(genresJson, new TypeReference<List<GenreDto>>() {});
            for (GenreDto genre : genres) {
                genre.setId(null);
                if(!genreService.existsByGenreName(genre.getName())) {
                    genreService.createGenre(genre);
                }
            }
        } catch (IOException e) {
            log.warn("Unable to find json");
        }
    }

    @Override
    public void initializeData() {
        initializeGenreJsonFile();

    }
}
