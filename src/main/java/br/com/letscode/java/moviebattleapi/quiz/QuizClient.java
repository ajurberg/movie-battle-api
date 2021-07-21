package br.com.letscode.java.moviebattleapi.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizClient {

    private String userIdQuiz;
    private Integer score;
    private Integer totalOfMoves;
    private Integer life;

}
