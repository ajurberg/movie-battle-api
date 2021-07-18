package br.com.letscode.java.moviebattleapi.quiz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserService {

    private final UserRestRepository userRestRepository;

    public UserService(UserRestRepository userRestRepository) {
        this.userRestRepository = userRestRepository;
    }

    public Boolean criar(User user) {
        if (verificarRegra(user)) {
            userRestRepository.inserirNoArquivo(user);
            return true;
        } else {
            return false;
        }
    }

    public Boolean verificarRegra(User user) {

        //Verifica tamanho do userId e password
        if (!(user.getUserId().length() < 5 || user.getUserId().length() > 10) &&
                !(user.getPassword().length() < 4 || user.getPassword().length() > 8)) {

            for (int i = 0; i < user.getUserId().length(); i++) {

                //verifica se o userId tem caracteres especiais
                if (Character.isAlphabetic(user.getUserId().charAt(i))
                        || Character.isDigit(user.getUserId().charAt(i))) {

                    for (int j = 0; j < user.getPassword().length(); j++) {

                        //verifica se o password tem caracteres especiais
                        if (Character.isAlphabetic(user.getPassword().charAt(j))
                                || Character.isDigit(user.getPassword().charAt(j))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //o nome de usuario deve possuir de 5-10 caracteres
    //a senha deve possuir de 4-8 caracteres
    //ambos nao podem possuir caracteres especiais ou espaços
    //se nao atender aos requisitos, retornar erro
    //se atender, verificar se o nome de usuario já existe
    //se existir, enviar erro
    //se nao criar usuario.
}
