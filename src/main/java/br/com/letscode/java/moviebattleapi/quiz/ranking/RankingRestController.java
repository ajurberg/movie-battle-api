package br.com.letscode.java.moviebattleapi.quiz.ranking;

import br.com.letscode.java.moviebattleapi.quiz.QuizClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
}
