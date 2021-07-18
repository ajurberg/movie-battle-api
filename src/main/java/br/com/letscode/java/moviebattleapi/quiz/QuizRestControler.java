package br.com.letscode.java.moviebattleapi.quiz;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/quiz")
@AllArgsConstructor
@NoArgsConstructor
@RestController
public class QuizRestControler {

    private QuizService quizService;

   @GetMapping
    public QuizClient createQuiz(){
       // quizService.createQuiz();
        QuizClient quizClient = new QuizClient();
        quizClient.setScore(50);

        return quizClient;
        //trocar o return para uma lista quando acertar o restante
    }



    //get
    //retorna dois filmes

    //post
    //deve conter senha, usuario e id do filme vencedor encapsulado em requestbody

    //get para ranking
}
