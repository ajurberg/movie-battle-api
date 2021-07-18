package br.com.letscode.java.moviebattleapi.quiz;

import br.com.letscode.java.moviebattleapi.quiz.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizClient {

    private User user;
    private Integer answer;
    private Integer score;
    private Integer move;

}
