package br.com.letscode.java.moviebattleapi.quiz;

import br.com.letscode.java.moviebattleapi.imdbclient.ImdbScraper;
import br.com.letscode.java.moviebattleapi.movie.Movie;
import br.com.letscode.java.moviebattleapi.quiz.user.User;
import br.com.letscode.java.moviebattleapi.quiz.user.UserRestRepository;
import br.com.letscode.java.moviebattleapi.quiz.user.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class QuizService {

    private final UserRestRepository userRestRepository;
    private final UserService userService;
    private final QuizRepository quizRepository;

    // TODO
    // createGame()

    // TODO
    public List createQuiz() throws IOException {
        List<Movie> moviePair = new ArrayList<>();
        // 1- Pegar 2 Filmes - OK
        pickTwoMovies();
        // 2- Apresentar as duas opções
        // TODO Get
        // 3- Aguardar a escolha do usuário
        // TODO Post
        // 4- Comparar os dois filmes - OK
        //compareTwoMoviesByScore(moviePair);
        // 5- Avaliar se resposta do usuário está correta. Se sim, count+1
        // Se não, life-1 (while life !=0)
        //checkUserAnswer(); // TODO
        return moviePair;
    }

    public List pickTwoMovies() throws IOException {
        ImdbScraper imdbScraper = new ImdbScraper();
        List<Movie> movieList = imdbScraper.scraping();
        // Verificar se a lista de filmes existe, senão retornar erro
        if (null == movieList) {
            // TODO log
        } else {
            // Se existir, pegar dois filmes aleatórios da lista, sem repetição
            // (remove o primeiro escolhido para então escolher o segundo)
            List<Movie> moviePair = new ArrayList<>();
            //Random random = new Random();
            int index1 = getRandomNumberUsingNextInt(1, movieList.size());
            Movie movie1 = movieList.get(index1);
            moviePair.add(movie1);
            movieList.remove(index1);
            int index2 = getRandomNumberUsingNextInt(1, movieList.size());
            Movie movie2 = movieList.get(index2);
            moviePair.add(movie2);
            quizRepository.gravarArquivoTemporario(moviePair);
            return moviePair;
        }
        return null;
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

//    public static Movie compareTwoMoviesByScore(List<Movie> moviePair) {
//        if (null == moviePair) {
//            // TODO log
//        } else {
//            Double score1 = moviePair.get(0).getScore();
//            Double score2 = moviePair.get(1).getScore();
//            if (score1 < score2) {
//                return moviePair.get(1);
//            } else {
//                return moviePair.get(0);
//            }
//        }
//        return null;
//    }

    // 5- Avaliar se resposta do usuário está correta.
    // Se sim, count+1; Se não, life-1
    public static Movie checkUserAnswer(Movie movie) {

        return movie;
    }

    public String login(@RequestBody User user) throws IOException {
        this.userRestRepository.carregarJogadores();
        if (this.userService.verificaUsuario(user)) {
            return "Login realizado com sucesso";
        } else {
            return "usuário ou senha incorretos";
        }
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
