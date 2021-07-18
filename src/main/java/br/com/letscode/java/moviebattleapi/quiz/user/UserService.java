package br.com.letscode.java.moviebattleapi.quiz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRestRepository userRestRepository;

    public User criar(User user){
        userRestRepository.inserirNoArquivo(user);
        return user;
    }


    //verificar Regras De Usuario e Senha
    public void verificarRegra(User user){
        if((user.getUserId().length() <5 || user.getUserId().length() >10) ||
                (user.getPassword().length() < 4 || user.getPassword().length()>8)){
            criar(user);
        }else {
            //TODO lançar erro "Usuario ou senha não atendem as regras"
        }
    }
    //o nome de usuario deve possuir de 5-10 caracteres
    //a senha deve possuir de 4-8 caracteres
    //ambos nao podem possuir caracteres especiais ou espaços
    //se nao atender aos requisitos, retornar erro
    //se atender, verificar se o nome de usuario já existe
    //se existir, enviar erro
    //se nao criar usuario.
}
