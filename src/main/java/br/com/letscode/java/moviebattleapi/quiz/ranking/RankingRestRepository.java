package br.com.letscode.java.moviebattleapi.quiz.ranking;

import br.com.letscode.java.moviebattleapi.quiz.QuizClient;
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
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class RankingRestRepository {

    private List<QuizClient> rankingSorted;
    private Path quizPathTemp;
    final String rankingPath = ".\\src\\main\\resources\\Ranking.csv";

    public void writeRanking(QuizClient quizClient) {
        this.quizPathTemp = Paths.get(this.rankingPath);
        try (BufferedWriter bf = Files.newBufferedWriter(quizPathTemp, StandardOpenOption.APPEND)) {
            String quizClientString = String.format("%s;%s;%s;\n", quizClient.getUserIdQuiz(), quizClient.getScore(), quizClient.getTotalOfMoves());
            bf.write(quizClientString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<QuizClient> carregarJogadores() throws IOException {
        this.quizPathTemp = Paths.get(this.rankingPath);
        this.rankingSorted = new ArrayList<>();
        Reader leitor = Files.newBufferedReader(this.quizPathTemp);
        CSVReader csvReader = new CSVReader(leitor);
        String[] linhaArq;
        while ((linhaArq = csvReader.readNext()) != null) {
            for (String s : linhaArq) {
                StringTokenizer token = new StringTokenizer(s, ";");
                QuizClient quizClient = new QuizClient();
                quizClient.setUserIdQuiz(token.nextToken());
                quizClient.setScore(Integer.valueOf(token.nextToken()));
                quizClient.setTotalOfMoves(Integer.valueOf(token.nextToken()));
                this.rankingSorted.add(quizClient);
            }
        }
        return this.rankingSorted;
    }

    public Stream<QuizClient> sortedRanking(List<QuizClient> listaRanking ) {
//        return listaRanking.stream().sorted((o1, o2)->o1.getScore().
//                compareTo(o2.getScore()).
//                collect(Collectors.toList());


        return listaRanking.stream()
                .sorted(Comparator.comparingInt(QuizClient::getScore).reversed());
    }
}
