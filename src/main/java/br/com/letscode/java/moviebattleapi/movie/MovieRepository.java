package br.com.letscode.java.moviebattleapi.movie;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository {

    Optional<Movie> findMovieByImdbId(String imdbId);
    List<Movie> findMoviesByImdbIdNotIn(List<String> imdbIdList);

}
