package br.com.letscode.java.moviebattleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class MovieBattleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieBattleApiApplication.class, args);
    }
}
