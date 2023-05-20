package pl.ccteamone.filmvault.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private long id;
    private String title;
    private int rating;
    private String description;
    private int year;
    private String genre;
    //poster (file)
    //vodPlaforms (Set)
    //users (Set)
}
