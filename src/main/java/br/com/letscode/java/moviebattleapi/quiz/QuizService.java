package br.com.letscode.java.moviebattleapi.quiz;

import br.com.letscode.java.moviebattleapi.imdbclient.ImdbScraper;
import br.com.letscode.java.moviebattleapi.movie.Movie;
import br.com.letscode.java.moviebattleapi.quiz.ranking.RankingRestRepository;
import br.com.letscode.java.moviebattleapi.quiz.user.User;
import br.com.letscode.java.moviebattleapi.quiz.user.UserRestRepository;
import br.com.letscode.java.moviebattleapi.quiz.user.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

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
    private final RankingRestRepository rankingRestRepository;


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
            return quizRepository.carregarMovieDTO();
        }
        return null;
    }

    @SneakyThrows
    public QuizClient verifyAnswer(QuizClientAnswer quizClientAnswer) {
        User user = new User();
        user.setUserId(quizClientAnswer.getUserIdQuiz());
        user.setPassword(quizClientAnswer.getPassword());
        //verificando usuario e senha
        if (login(user)){
           //verificando se tem jogo ativo, se tiver carregar quizCliente, senao fazer um novo iniciando com 3 vidas
            QuizClient quizClient = quizRepository.verifyJogosCsv(user);
            List<Movie> moviePair = quizRepository.carregarFilmesTemp();
            Movie winnerMovie = compareTwoMoviesByScore(moviePair);
            quizClient = checkUserAnswer(quizClientAnswer, winnerMovie, quizClient);
            quizRepository.writeJogo(quizClient);
            return quizClient;



         //   quizRepository.verifyAnswer(quizClient, quizClientAnswer);
           //alterar situação atual em jogoscsv (vida, pontuacao e tentativa)
           //se vida = 0 finalizar jogo, excluir de jogos.csv e adidionar ao ranking (ordenar)
            //TODO Fazer else para tratar as respostas negativas
       }
        return null;
    }

    public QuizClient checkUserAnswer(QuizClientAnswer quizClientAnswer, Movie winnerMovie, QuizClient quizClient) {
        if (quizClientAnswer.getAnswer().equals(winnerMovie.getImdbId())) {
            quizClient.setScore(quizClient.getScore() + 10);
            quizClient.setTotalOfMoves(quizClient.getTotalOfMoves() + 1);
        } else {
            quizClient.setLife(quizClient.getLife() - 1);
            quizClient.setTotalOfMoves(quizClient.getTotalOfMoves() + 1);
            if (quizClient.getLife() == 0 ){
                this.rankingRestRepository.writeRanking(quizClient);
                return new QuizClient();
            }
        }
        return quizClient;
    }

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

    public static Movie compareTwoMoviesByScore(List<Movie> moviePair) {

        if (null == moviePair) {
            // TODO log
        } else {
            Double score1 = moviePair.get(0).getScore();
            Double score2 = moviePair.get(1).getScore();
            if (score1 < score2) {
                return moviePair.get(1);
            } else {
                return moviePair.get(0);
            }
        }
        return null;
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public Boolean login(User user) throws IOException {

        if (this.userService.verificaUsuario(user)) {
            return true;
        } else {
            return false;
        }
    }



}
