package br.com.letscode.java.moviebattleapi.imdbclient;

import br.com.letscode.java.moviebattleapi.movie.Movie;
import br.com.letscode.java.moviebattleapi.movie.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@RestController
public class ImdbScraper {

    private MovieService movieService;

    public ImdbScraper() throws IOException {
        // empty
    }

    @Autowired
    public ImdbScraper(MovieService movieService) throws IOException {
        this.movieService = movieService;
    }


    final Document document = Jsoup.connect("https://www.imdb.com/search/title/?groups=top_250&sort=user_rating,desc&view=advanced")
            .timeout(6000)
            .get();

    private ArrayList<Movie> scraping() {
        ArrayList<Movie> movieDataList = new ArrayList<Movie>();
        Elements body = document.select("div.lister-list");
        for (Element row : body.select("div.lister-item-content")) {
            final String imdbId = row.select("h3.lister-item-header span").get(0).text().replaceAll("[\\.]", "");
            final String title = row.select("h3.lister-item-header a").text();
            final Integer year = Integer.parseInt(row.select("h3.lister-item-header span").get(1).text().replaceAll("[^\\d]", ""));
            //final String genre = row.select("p.text-muted span").get(4).text();
            final Double rating = Double.parseDouble(row.select("div.ratings-bar strong").text());
            final Long votes = Long.parseLong(row.select("p.sort-num_votes-visible span").get(1).text().replaceAll(",", ""));

            movieDataList.add(new Movie(imdbId, title, year, rating, votes));
        }
        return movieDataList;
    }

    public ArrayList<Movie> criar() throws IOException {
        ArrayList<Movie> movieDataList = scraping();
        movieService.criar(movieDataList);
        return movieDataList;
    }
}
