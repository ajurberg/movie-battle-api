package br.com.letscode.java.moviebattleapi.quiz;

import br.com.letscode.java.moviebattleapi.imdbclient.ImdbScraper;
import br.com.letscode.java.moviebattleapi.movie.Movie;
import br.com.letscode.java.moviebattleapi.quiz.user.User;
import br.com.letscode.java.moviebattleapi.quiz.user.UserRestRepository;
import br.com.letscode.java.moviebattleapi.quiz.user.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class QuizService {

    private UserRestRepository userRestRepository;
    private UserService userService;

    public List createQuiz() throws IOException {
        // TODO
        return null;
    }

    public static List pickTwoMovies() throws IOException {
        ImdbScraper imdbScraper = new ImdbScraper();
        List<Movie> movieList = imdbScraper.scraping();
        // Verificar se a lista de filmes existe, senão retornar erro
        if ( null == movieList) {
            // TODO log
        } else {
            // Se existir, pegar dois filmes aleatórios da lista, sem repetição
            // (remove o primeiro escolhido para então escolher o segundo)
            List<Movie> moviePair = new ArrayList<>();
            Random random = new Random();
            int index1 = getRandomNumberUsingNextInt(1, movieList.size());
            Movie movie1 = movieList.get(index1);
            moviePair.add(movie1);
            movieList.remove(index1);
            int index2 = getRandomNumberUsingNextInt(1, movieList.size());
            Movie movie2 = movieList.get(index2);
            moviePair.add(movie2);
            return moviePair;
        }
        return null;
    }


    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
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
