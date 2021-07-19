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

    public User criar(User user) {
        if (verificarRegrasDeUsuarioeSenha(user)) {
            userRestRepository.inserirNoArquivo(user);    
            return user;
		                } else {
						return null; }
    }

      private Boolean verificarRegrasDeUsuarioeSenha(User user) {
        if (user.getUserId().length() < 5 || user.getUserId().length() > 10 ||
                user.getPassword().length() < 4 || user.getPassword().length() > 8) {
            return false;
        } else {
            // Verifica caracteres especiais
            Pattern p = Pattern.compile("[^A-Za-z0-9]");
			
			//TODO trazer a classe encode para cá (retirar do @RestController)
			
            
			Matcher m = p.matcher(user.getPassword());
			
            boolean b = m.find();
            if (b) {
                //System.out.println("A string possui caracteres especiais"); // TODO Log
                return true;
            } else {
                //System.out.println("A string não possui caracteres especiais"); // TODO Log
                return false;

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

}


