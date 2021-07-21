package br.com.letscode.java.moviebattleapi.quiz.ranking;

import br.com.letscode.java.moviebattleapi.quiz.QuizClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@RequestMapping("/ranking")
@RestController
public class RankingRestController {

    private final RankingService rankingService;

    @GetMapping
    public Stream<QuizClient> viewRanking() throws IOException {
        return rankingService.getRanking();
    }


//    List<QuizClient> rankingList = new ArrayList<>();
//    QuizClient quizClient = new QuizClient();
//        quizClient.setScore(30);
//        quizClient.setTotalOfMoves(20);
//        quizClient.setLife(0);
//        quizClient.setUserIdQuiz("Desi");
//
//    QuizClient quizClient1 = new QuizClient();
//        quizClient1.setUserIdQuiz("Gabriel");
//        quizClient1.setScore(20);
//        quizClient1.setTotalOfMoves(30);
//        quizClient.setLife(0);
//
//        rankingList.add(quizClient);
//        rankingList.add(quizClient1);
//
//        return rankingList;



    //get para requisição do ranking
}
