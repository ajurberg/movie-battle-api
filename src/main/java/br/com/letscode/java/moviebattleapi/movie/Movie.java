package br.com.letscode.java.moviebattleapi.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Movie {

    private String imdbId;
    private String title;
    private Integer year;
    //private String genre;
    private Double rating;
    private Long votes;


    public Movie(String imdbId, String title, Integer year, Double rating, Long votes) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.votes = votes;
    }

}
