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
    final String jogosPath = ".\\src\\main\\resources\\jogos.csv";

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
                String ignore = token.nextToken();
                String ignore2 = token.nextToken();
                movieDTOList.add(movieDTO);
            }
        }
        return movieDTOList;
    }

    public QuizClient carregarJogos(User user) throws IOException {
        this.quizPathTemp = Paths.get(jogosPath);
        Reader leitor = Files.newBufferedReader(this.quizPathTemp);
        CSVReader csvReader = new CSVReader(leitor);
        String[] linhaArq;
        while ((linhaArq = csvReader.readNext()) != null) {
            for (String s : linhaArq) {
                if (s.contains(user.getUserId())) {
                    QuizClient quizClient = new QuizClient();
                    StringTokenizer token = new StringTokenizer(s, ";");
                    quizClient.setUserIdQuiz(token.nextToken());
                    quizClient.setScore(Integer.valueOf(token.nextToken()));
                    quizClient.setTotalOfMoves(Integer.valueOf(token.nextToken()));
                    quizClient.setLife(Integer.valueOf(token.nextToken()));
                    return quizClient;
                }
            }
        }
        return null;
    }


    public QuizClient verifyJogosCsv(User user) throws IOException {
        QuizClient quizClient = carregarJogos(user);
        if (quizClient != null) {
            return quizClient;
        } else {
            quizClient = new QuizClient();
            quizClient.setUserIdQuiz(user.getUserId());
            quizClient.setTotalOfMoves(0);
            quizClient.setScore(0);
            quizClient.setLife(3);
            writeJogo(quizClient);
            return quizClient;
        }
    }

    public void writeJogo(QuizClient quizClient) {
        this.quizPathTemp = Paths.get(jogosPath);
        try (BufferedWriter bf = Files.newBufferedWriter(quizPathTemp, StandardOpenOption.TRUNCATE_EXISTING)) {
            String quizClientString = String.format("%s;%s;%s;%s;\n", quizClient.getUserIdQuiz(), quizClient.getScore(), quizClient.getTotalOfMoves(), quizClient.getLife());
            bf.write(quizClientString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> carregarFilmesTemp() throws IOException {
        this.quizPathTemp = Paths.get(quizPath);
        Reader leitor = Files.newBufferedReader(this.quizPathTemp);
        CSVReader csvReader = new CSVReader(leitor);
        String[] linhaArq;
        List<Movie> moviePair = new ArrayList<>();
        while ((linhaArq = csvReader.readNext()) != null) {
            for (String s : linhaArq) {
                Movie movie = new Movie();
                StringTokenizer token = new StringTokenizer(s, ";");
                movie.setImdbId(token.nextToken());
                movie.setTitle(token.nextToken());
                movie.setYear(Integer.valueOf(token.nextToken()));
                movie.setRating(Double.valueOf(token.nextToken()));
                movie.setVotes(Double.valueOf(token.nextToken()));
                movie.setScore(movie.getRating()* movie.getVotes());
                moviePair.add(movie);
            }
        }
        return moviePair;
    }
}



