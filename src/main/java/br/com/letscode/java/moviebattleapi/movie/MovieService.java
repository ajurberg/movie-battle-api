package br.com.letscode.java.moviebattleapi.movie;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Component
@AllArgsConstructor
public class MovieService {

    private final MovieRestRepository movieRestRepository;

    public Movie criar(List<Movie> movieDataList) throws IOException {
        if (null == movieDataList) {
            // TODO log
            return null;
        } else {
            movieRestRepository.inserirNoArquivo(movieDataList);
            return (Movie) movieDataList;
        }
    }
}
