package pl.ccteamone.filmvault.tvseries.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TvSeriesDtoPage {

    private Long page;
    private Long totalPages;
    private Long totalResults;
    private List<TvSeriesDto> tvSeries = new ArrayList<>();
}
