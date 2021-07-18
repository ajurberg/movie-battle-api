package br.com.letscode.java.moviebattleapi.imdbclient;

import br.com.letscode.java.moviebattleapi.movie.MovieDTO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class ImdbScraper {

    private ArrayList<MovieDTO> movieDataList;

    final Document document = Jsoup.connect("https://www.imdb.com/search/title/?groups=top_250&sort=user_rating,desc&view=advanced")
            .timeout(6000)
            .get();

    public ImdbScraper() throws IOException {
    }

    /**
     * Parse data form the top_250 movies at IMDB
     * @return ArrayList<MovieDTO>movieDataList</MovieDTO> containing imdbId, title, year, rating, votes
     * @throws IOException
     */
    public ArrayList<MovieDTO> ImdbScraper() throws IOException {
        Elements body = document.select("div.lister-list");
        for (Element row : body.select("div.lister-item-content")) {
            final String imdbId = row.select("h3.lister-item-header span").get(0).text().replaceAll("\\.", "");
            final String title = row.select("h3.lister-item-header a").text();
            final Integer year = Integer.parseInt(row.select("h3.lister-item-header span").get(1).text().replaceAll("[^\\d]", ""));
            //final String genre = row.select("p.text-muted span").get(4).text();
            final Double rating = Double.parseDouble(row.select("div.ratings-bar strong").text());
            final Long votes = Long.parseLong(row.select("p.sort-num_votes-visible span").get(1).text().replaceAll(",", ""));

            movieDataList.add((MovieDTO) MovieDTO(imdbId, title, year, rating, votes));
        }
        return movieDataList;
    }

    // TODO confirmar se está correto
    private Object MovieDTO(String imdbId, String title, Integer year, Double rating, Long votes) {
        return this.movieDataList;
    }

}