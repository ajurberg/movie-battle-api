package br.com.letscode.java.moviebattleapi.quiz.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("password")
    private String password;

}
