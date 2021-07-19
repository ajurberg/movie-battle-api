package br.com.letscode.java.moviebattleapi.movie;

import br.com.letscode.java.moviebattleapi.imdbclient.ImdbScraper;
import br.com.letscode.java.moviebattleapi.imdbclient.MovieDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
@Component
@AllArgsConstructor
public class MovieService {

    private final MovieRestRepository movieRestRepository;

    public ArrayList<MovieDTO> criar(ArrayList<MovieDTO> movieDataList) throws IOException {
        // The return type of this method should be an interface such as "List" rather than the implementation "ArrayList".
        ImdbScraper imdbScraper = new ImdbScraper();
        if (null == movieDataList) {
            // TODO log
            return null;
        } else {
            movieRestRepository.inserirNoArquivo(movieDataList);
            return movieDataList;
        }
    }
}
