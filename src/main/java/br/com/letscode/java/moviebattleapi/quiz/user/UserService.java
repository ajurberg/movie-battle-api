package br.com.letscode.java.moviebattleapi.quiz.user;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    public User criar(User user) {


        return user;
    }


    private Boolean verificarRegrasDeUsuarioeSenha(User user) {
        if (user.getUserId().length() < 5 || user.getUserId().length() > 10 ||
                user.getPassword().length() < 4 || user.getPassword().length() > 8) {
            return false;
        } else {
            // Verifica caracteres especiais
            Pattern p = Pattern.compile("[^A-Za-z0-9]");
            Matcher m = p.matcher(user.getPassword());
            boolean b = m.find();
            if (b) {
                //System.out.println("A string possui caracteres especiais"); // TODO Log
                return true;
            } else {
                //System.out.println("A string não possui caracteres especiais"); // TODO Log
                return false;
            }
        }
    }

}
//o nome de usuario deve possuir de 5-10 caracteres
//a senha deve possuir de 4-8 caracteres
//ambos nao podem possuir caracteres especiais ou espaços
//se nao atender aos requisitos, retornar erro
//se atender, verificar se o nome de usuario já existe
//se existir, enviar erro
//se nao criar usuario.

