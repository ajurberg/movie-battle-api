package br.com.letscode.java.moviebattleapi.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizClientAnswer {

    private String userIdQuiz;
    private String password;
    private String answer;
}
