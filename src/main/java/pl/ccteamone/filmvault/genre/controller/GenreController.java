package pl.ccteamone.filmvault.genre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ccteamone.filmvault.genre.dto.GenreDto;
import pl.ccteamone.filmvault.genre.service.GenreService;

import java.util.Set;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/list")
    public Set<GenreDto> getGenreList() {
        return genreService.getGenreSet();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public Set<GenreDto> createGenre(@RequestBody GenreDto genre) {
        genreService.createGenre(genre);
        return genreService.getGenreSet();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public Set<GenreDto> updateGenre(@PathVariable Long id, @RequestBody GenreDto genre) {
        genreService.updateGenre(id, genre);
        return genreService.getGenreSet();
    }

    @PreAuthorize("hasAuthority('ADMIN')")

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenreById(id);
    }


}
