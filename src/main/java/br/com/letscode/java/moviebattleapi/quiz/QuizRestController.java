package br.com.letscode.java.moviebattleapi.quiz;

import br.com.letscode.java.moviebattleapi.quiz.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
       QuizClient quizClient = new QuizClient();
       quizClient.setUserIdQuiz(quizClientAnswer.getUserIdQuiz());
       //TODO fazer o metodo abaixo em service
        //quizService.verifyAnswer(quizClient);
       return quizClient;
    }



    //get
    //retorna dois filmes

    //post
    //deve conter senha, usuario e id do filme vencedor encapsulado em requestbody

    //get para ranking
}
