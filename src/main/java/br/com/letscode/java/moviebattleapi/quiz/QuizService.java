package br.com.letscode.java.moviebattleapi.quiz;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class QuizService {

    QuizRestControler quizRestControler;

    public List createQuiz(){
        //verificar se a lista de filmes existe, senao retornar erro
        //se existir voltar dois filmes em lista
        //como verificar a questao já passada para um user sem fazer login?
        //usar uma lista grande (50 criada por Arnon) e ir removendo? mas e hr que acabar?

        return null; //acertar o null
    }

    //usuario e senha errado retornar erro
    //se o usuario e senha ok, verifica se tem jogo ativo em jogos.csv
    //se nao tiver jogo ativo, criar um jogo com 3 vidas
    // verifica se a resposta está correta
    //se o id for invalido ( nao corresponder a nenhum filme) retornar um erro
    //se se correta adicionar pontuação , se errada diminuir vida
    //se vidas < 0 finalizar o jogo, excluir jogo atual de jogos.csv e adicionar
    //os dados ao ranking de forma ordenada
}
