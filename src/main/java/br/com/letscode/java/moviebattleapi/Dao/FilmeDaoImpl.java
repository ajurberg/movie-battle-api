package br.com.letscode.java.moviebattleapi.Dao;

import br.com.letscode.java.moviebattleapi.movie.Movie;
import br.com.letscode.java.moviebattleapi.quiz.user.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class FilmeDaoImpl {

    private Path movie;

    public void init() {
        final String pathMovie = ".\\movie-battle-api\\src\\main\\java\\br\\com\\letscode\\java\\moviebattleapi\\dados\\filmes\\Filmes.csv";
        this.movie = Paths.get(pathMovie);
    }

    public Movie inserirNoArquivo(Movie filme) {
        try (BufferedWriter bf = Files.newBufferedWriter(movie, StandardOpenOption.APPEND)) {
            bf.write(formatar(filme));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filme;
    }

    private String formatar(Movie movie) {
        return String.format("%s;%f;%x\n", movie.getTitle(), movie.getRating(), movie.getVotes());
    }

    public List<Movie> getAll() throws IOException {
        List<Movie> movie;
        try (BufferedReader br = Files.newBufferedReader(this.movie)) {
            movie = br.lines().filter(String::isEmpty)
                    .map(this::converterLinhaEmFilme)
                    .collect(Collectors.toList());
        }
        return movie;
    }

    public Movie converterLinhaEmFilme(String linha) {
        StringTokenizer token = new StringTokenizer(linha, ";");
        Movie movie = new Movie();
        movie.setTitle(token.nextToken());
        movie.setRating(Double.valueOf(token.nextToken()));
        movie.setVotes(Long.valueOf(token.nextToken()));
        return movie;
    }
}
