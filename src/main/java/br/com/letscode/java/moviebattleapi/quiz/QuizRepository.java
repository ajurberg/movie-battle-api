package br.com.letscode.java.moviebattleapi.quiz;

import br.com.letscode.java.moviebattleapi.movie.Movie;
import br.com.letscode.java.moviebattleapi.movie.MovieDTO;
import br.com.letscode.java.moviebattleapi.quiz.user.User;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Repository
public class QuizRepository {

    private Path quizPathTemp;
    final String quizPath = ".\\src\\main\\resources\\quiztemp.csv";

    public List gravarArquivoTemporario(List<Movie> moviePair) {
        this.quizPathTemp = Paths.get(quizPath);
        try (BufferedWriter bf = Files.newBufferedWriter(quizPathTemp, StandardOpenOption.TRUNCATE_EXISTING)) {

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

    public List carregarMovieDTO() throws IOException {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        this.quizPathTemp = Paths.get(quizPath);
        Reader leitor = Files.newBufferedReader(this.quizPathTemp);
        CSVReader csvReader = new CSVReader(leitor);
        String[] linhaArq;
        while ((linhaArq = csvReader.readNext()) != null) {
            MovieDTO movieDTO = new MovieDTO();
            for (String s : linhaArq) {
                StringTokenizer token = new StringTokenizer(s, ";");
                movieDTO.setImdbId(token.nextToken());
                movieDTO.setTitle(token.nextToken());
                movieDTO.setYear(Integer.valueOf(token.nextToken()));
                movieDTOList.add(movieDTO);
            }
        }
        return movieDTOList;
    }
}
    //deve criar o arquivo jogos.csv
    //o arquivo deve possuir em cada linha o nome do usuario, x/y
    //sendo x = acertos e y, total, adicionaremos z
    //z sendo a qtd de vidas



