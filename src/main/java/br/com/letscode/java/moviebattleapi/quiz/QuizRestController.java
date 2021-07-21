package br.com.letscode.java.moviebattleapi.quiz;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("/quiz")
@AllArgsConstructor
@RestController
public class QuizRestController {

    private final QuizService quizService;

   @GetMapping
    public List createQuiz() throws IOException {
        return quizService.pickTwoMovies();
    }

   @PostMapping
    public QuizClient receiveAnswer(@RequestBody QuizClientAnswer quizClientAnswer){
       return  quizService.verifyAnswer(quizClientAnswer);
    }
}
