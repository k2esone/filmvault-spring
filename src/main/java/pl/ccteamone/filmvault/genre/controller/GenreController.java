package pl.ccteamone.filmvault.genre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.genre.dto.GenreDto;
import pl.ccteamone.filmvault.genre.service.GenreService;

import java.util.Set;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/list")
    public Set<GenreDto> getGenreList() {
        return genreService.getGenreSet();
    }

    @PostMapping("/create")
    public Set<GenreDto> createGenre(@RequestBody GenreDto genre) {
        genreService.createGenre(genre);
        return genreService.getGenreSet();
    }

    @PatchMapping("/{id}")
    public Set<GenreDto> updateGenre(@PathVariable Long id, @RequestBody GenreDto genre) {
        genreService.updateGenre(id, genre);
        return genreService.getGenreSet();
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenreById(id);
    }


}
