package br.com.letscode.java.moviebattleapi.quiz.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RequestMapping("/users")
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    public UserRestController (UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public String criar(@RequestBody User user) {
        if (userService.criar(user)){
            return "Usúario cadastrado com sucesso";
        }
        return "Erro ao criar usúario";
    }

    //post para criação de um novo usuario chama o services

}
