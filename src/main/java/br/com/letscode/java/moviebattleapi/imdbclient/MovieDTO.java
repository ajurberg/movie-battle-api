package br.com.letscode.java.moviebattleapi.imdbclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private String imdbId;
    private String title;
    private Integer year;
    private String genre;
    private Double rating;
    private Long votes;

}
