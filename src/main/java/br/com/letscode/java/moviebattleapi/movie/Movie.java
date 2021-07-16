package br.com.letscode.java.moviebattleapi.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {

    private String title;
    private Integer year;
    private String imdbId;
    private String type;
    private Double rating;
    private Long votes;


}
