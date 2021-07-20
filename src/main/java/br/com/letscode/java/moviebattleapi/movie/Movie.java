package br.com.letscode.java.moviebattleapi.movie;

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
    private Double votes;
    private Double score;


    public Movie(String imdbId, String title, Integer year, Double rating, Double votes, Double score) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.votes = votes;
        this.score = score;

    }

}
