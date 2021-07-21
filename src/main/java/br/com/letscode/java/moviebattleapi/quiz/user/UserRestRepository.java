package br.com.letscode.java.moviebattleapi.quiz.user;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


@Repository
public class UserRestRepository {

    public List<User> userList;
    private Path userPath;
    private Path rankPath;

    @PostConstruct
    public void init() {
        final String pathRank = ".\\src\\main\\resources\\Ranking.csv";
        this.rankPath = Paths.get(pathRank);
        final String pathUser = ".\\src\\main\\resources\\Jogadores.csv";
        this.userPath = Paths.get(pathUser);
    }

    public User inserirNoArquivo(User usuario) {
        try (BufferedWriter bf = Files.newBufferedWriter(userPath, StandardOpenOption.APPEND)) {
            bf.write(formatar(usuario));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    private String formatar(User usuario) {
        return String.format("%s;%s;\n", usuario.getUserId(), usuario.getPassword());
    }

    public List<User> getAll() throws IOException {
        List<User> users;
        try (BufferedReader br = Files.newBufferedReader(this.userPath)) {
            users = br.lines().filter(String::isEmpty)
                    .map(this::converterLinhaEmUsuario)
                    .collect(Collectors.toList());
        }
        return users;
    }

    public User converterLinhaEmUsuario(String linha) {
        StringTokenizer token = new StringTokenizer(linha, ";");
        User usuario = new User();
        usuario.setUserId(token.nextToken());
        usuario.setPassword(token.nextToken());
        return usuario;
    }

    public List<User> carregarJogadores() throws IOException {
        this.userList = new ArrayList<>();
        Reader leitor = Files.newBufferedReader(this.userPath);
        CSVReader csvReader = new CSVReader(leitor);
        String[] linhaArq;
        while ((linhaArq = csvReader.readNext()) != null) {
            for (String s : linhaArq) {
                StringTokenizer token = new StringTokenizer(s, ";");
                User user = new User();
                user.setUserId(token.nextToken());
                user.setPassword(token.nextToken());
                this.userList.add(user);
            }
        }
        return this.userList;
    }
}
