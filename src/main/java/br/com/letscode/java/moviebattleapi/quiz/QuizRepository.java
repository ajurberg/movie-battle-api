package br.com.letscode.java.moviebattleapi.quiz;

import br.com.letscode.java.moviebattleapi.movie.Movie;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


@Repository
public class QuizRepository {

    private Path quizPathTemp;


    public List gravarArquivoTemporario(List<Movie> moviePair) {
        final String quizPath = ".\\src\\main\\resources\\quiztemp.csv";
        this.quizPathTemp = Paths.get(quizPath);
        try (BufferedWriter bf = Files.newBufferedWriter(quizPathTemp, StandardOpenOption.APPEND)) {

            List<String> linhas = formatar(moviePair);
            for (String linha : linhas) {
                bf.write(linha);
            }







        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List formatar(List<Movie> moviePair) {


        List<String> linhas = new ArrayList<>();
        for (Movie m : moviePair) {
             String linha = String.format("%s;%s;%s;%s;%s;%s;\n", m.getImdbId(), m.getTitle(), m.getYear(), m.getRating(), m.getVotes(), m.getScore());
             linhas.add(linha);

        }

        return linhas;

    }

}
    //deve criar o arquivo jogos.csv
    //o arquivo deve possuir em cada linha o nome do usuario, x/y
    //sendo x = acertos e y, total, adicionaremos z
    //z sendo a qtd de vidas



