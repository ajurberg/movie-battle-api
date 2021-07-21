package br.com.letscode.java.moviebattleapi.quiz.user;

import br.com.letscode.java.moviebattleapi.security.Criptografia;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Component
@AllArgsConstructor
public class UserService {

    private final Criptografia cripto;
    private final UserRestRepository userRestRepository;

    public User criar(User user) {
        if (verificarRegrasDeUsuarioeSenha(user)) {
            user.setPassword(cripto.encode(user.getPassword()));
            userRestRepository.inserirNoArquivo(user);
            return user;
        } else {
            return null;
        }
    }

    private Boolean verificarRegrasDeUsuarioeSenha(User user) {
        if (user.getUserId().length() < 5 || user.getUserId().length() > 10 ||
                user.getPassword().length() < 4 || user.getPassword().length() > 8) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
            Matcher userMatcher = pattern.matcher(user.getUserId());
            boolean isUserMatcher = userMatcher.find();
            Matcher passwordMatcher = pattern.matcher(user.getPassword());
            boolean isPasswordMatcher = passwordMatcher.find();
            if (isUserMatcher || isPasswordMatcher) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean verificaUsuario(User usuario) throws IOException {
        usuario.setPassword(cripto.encode(usuario.getPassword()));
        for (User user : userRestRepository.carregarJogadores()) {
            if (usuario.getUserId().equals(user.getUserId())
                    && usuario.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}






