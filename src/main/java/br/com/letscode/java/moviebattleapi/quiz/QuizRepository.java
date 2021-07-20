package br.com.letscode.java.moviebattleapi.quiz;

import br.com.letscode.java.moviebattleapi.movie.Movie;
import br.com.letscode.java.moviebattleapi.quiz.user.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
public class QuizRepository {


//    public List gravarArquivoTemporario(List<Movie> moviePair) throws IOException {
//        File csvOutputFile = new File(".\\src\\main\\resources\\quizTemp.csv");
//
//      try ( PrintWriter pw = new PrintWriter(csvOutputFile)) {
//          moviePair.stream()
//                  .map(this::convertToCSV)
//                  .forEach(pw::println);
//      }
//        assertTrue(csvOutputFile.exists());
//
//            return null;
//    }
//
//    public String convertToCSV(String[] data) {
//        return Stream.of(data)
//                .map(this::escapeSpecialCharacters)
//                .collect(Collectors.joining(","));
//    }
//
//    public String escapeSpecialCharacters(String data) {
//        String escapedData = data.replaceAll("\\R", " ");
//        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
//            data = data.replace("\"", "\"\"");
//            escapedData = "\"" + data + "\"";
//        }
//        return escapedData;
//    }




    }


    //deve criar o arquivo jogos.csv
    //o arquivo deve possuir em cada linha o nome do usuario, x/y
    //sendo x = acertos e y, total, adicionaremos z
    //z sendo a qtd de vidas



