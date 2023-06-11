package pl.ccteamone.filmvault.tvseries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ApiTvSeriesDtoList {

    private Long page;
    @JsonProperty("total_pages")
    private Long totalPages;
    @JsonProperty("total_results")
    private Long totalResults;
    @JsonProperty("results")
    private List<TvSeriesDto> tvSeries = new ArrayList<>();
}
