package br.com.letscode.java.moviebattleapi.quiz.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("password")
    private String password;

}
