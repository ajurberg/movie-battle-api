package br.com.letscode.java.moviebattleapi.quiz.ranking;

import br.com.letscode.java.moviebattleapi.quiz.QuizClient;
import br.com.letscode.java.moviebattleapi.quiz.QuizClientAnswer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/ranking")
@RestController
public class RankingRestController {


    @GetMapping
    public List viewRanking(){
        List<QuizClient> rankingList = new ArrayList<>();
        QuizClient quizClient = new QuizClient();
        quizClient.setScore(30);
        quizClient.setMove(20);
        quizClient.setLifes(0);
        quizClient.setUserIdQuiz("Desi");

        QuizClient quizClient1 = new QuizClient();
        quizClient1.setUserIdQuiz("Gabriel");
        quizClient1.setScore(20);
        quizClient1.setMove(30);
        quizClient.setLifes(0);

        rankingList.add(quizClient);
        rankingList.add(quizClient1);

        return rankingList;
    }




    //get para requisição do ranking
}
