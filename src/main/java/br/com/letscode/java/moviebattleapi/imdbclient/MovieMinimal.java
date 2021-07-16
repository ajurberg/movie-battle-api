package br.com.letscode.java.moviebattleapi.imdbclient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieMinimal {

    @JsonProperty("imdbID")
    private String imdbId;
    @JsonProperty("Title")
    private String title;
    private Integer year;

    @JsonProperty
    public void setYear(String year) {
        //TODO
    }


}
