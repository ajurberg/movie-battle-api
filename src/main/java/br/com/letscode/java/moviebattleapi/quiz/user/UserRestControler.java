package br.com.letscode.java.moviebattleapi.quiz.user;

import br.com.letscode.java.moviebattleapi.security.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("/users")
@RestController
public class UserRestControler {


    private UserService userService;

    private final Criptografia cripto;
    @Autowired
    public UserRestControler(Criptografia cripto) {
        this.cripto = cripto;
    }

    @PostMapping
    public User criar(@RequestBody User user){

       user.setPassword(cripto.encode(user.getPassword().toString()));
       // userService.criar(user);

        return user;
    }

    //post para criação de um novo usuario chama o services

}
