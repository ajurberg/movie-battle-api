package br.com.letscode.java.moviebattleapi.movie;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MovieDTO {

    private String imdbId;
    private String title;
    private Integer year;


}
