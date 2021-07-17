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

    private String title;
    private Integer year;
    private String imdbId;

}
