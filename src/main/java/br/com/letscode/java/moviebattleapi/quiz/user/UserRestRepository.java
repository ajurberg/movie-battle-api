package br.com.letscode.java.moviebattleapi.quiz.user;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


@Repository
public class UserRestRepository {

    private Path user;
    private Path rank;

    @PostConstruct
    public void init() {
        final String pathRank = "src\\main\\java\\br\\com\\letscode\\java\\moviebattleapi\\dados\\ranking\\Ranking.csv";
        this.rank = Paths.get(pathRank);
        final String pathUser = "src\\main\\java\\br\\com\\letscode\\java\\moviebattleapi\\dados\\usuarios\\Jogadores.csv";
        this.user = Paths.get(pathUser);
    }

    public User inserirNoArquivo(User usuario) {
        try (BufferedWriter bf = Files.newBufferedWriter(user, StandardOpenOption.APPEND)) {
            bf.write(formatar(usuario));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    private String formatar(User usuario) {
        return String.format("%s;%s;\n", usuario.getUserId(), usuario.getPassword());
        //todo colocar as vidas do jogador
//        return String.format("%s;%s;%d;\n", usuario.getUserId(), usuario.getPassword(), usuario.getLife);
    }

    public List<User> getAll() throws IOException {
        List<User> users;
        try (BufferedReader br = Files.newBufferedReader(this.user)) {
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

    public boolean autenticarUsuario(User id, User senha) throws IOException {
        List<User> users = getAll();
        Optional<User> idUser = users.stream()
                .filter(clienteSearch -> clienteSearch.getUserId().equals(id)).findFirst();
        Optional<User> senhaUser = users.stream()
                .filter(clienteSearch -> clienteSearch.getPassword().equals(senha)).findFirst();
        // FIXME
        // Condition 'idUser.isPresent()' is always 'false'
        // Condition 'idUser.isPresent() && senhaUser.isPresent()' is always 'false'
        if (idUser.isPresent() && senhaUser.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
