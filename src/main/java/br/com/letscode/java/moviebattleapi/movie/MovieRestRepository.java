package br.com.letscode.java.moviebattleapi.movie;

import br.com.letscode.java.moviebattleapi.imdbclient.ImdbScraper;
import br.com.letscode.java.moviebattleapi.imdbclient.MovieDTO;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Repository
public class MovieRestRepository {

    private Path movie;

    public void init() {
        final String pathMovie = ".\\movie-battle-api\\src\\main\\java\\br\\com\\letscode\\java\\moviebattleapi\\dados\\filmes\\Filmes.csv";
        this.movie = Paths.get(pathMovie);
    }

    public void inserirNoArquivo(ArrayList<Movie> imdbScraper) {
        ArrayList<Movie> movieDataList = new ArrayList<Movie>();
        try {
            File outFile = new File(".\\movie-battle-api\\src\\main\\java\\br\\com\\letscode\\java\\moviebattleapi\\dados\\filmes\\Filmes.csv");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile, true), "utf-8"), 10240);
            for (int i = 0; i < movieDataList.size(); i++) {
                out.write(movieDataList.get(i) + "\r\n");
            }
            out.flush();
            out.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * public Movie inserirNoArquivo(Movie filme) {
     * try (BufferedWriter bf = Files.newBufferedWriter(movie, StandardOpenOption.APPEND)) {
     * bf.write(formatar(filme));
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * return filme;
     * }
     */

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