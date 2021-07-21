package br.com.letscode.java.moviebattleapi.quiz.ranking;

import br.com.letscode.java.moviebattleapi.quiz.QuizClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class RankingService {

    private final RankingRestRepository rankingRestRepository;

    public Stream<QuizClient> getRanking() throws IOException {
        List<QuizClient> lista = rankingRestRepository.carregarJogadores();
        return rankingRestRepository.sortedRanking(lista);

    }

    //podemos criar uma regra de verificar quantos
    //resultados possui o ranking e limitar a lista
    // a 10 resultados, exlcuindo os demais

    //pegar os dados, ordenar por ranking, gravar novamente
}
