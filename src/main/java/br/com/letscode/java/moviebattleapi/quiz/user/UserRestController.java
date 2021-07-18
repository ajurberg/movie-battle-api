package br.com.letscode.java.moviebattleapi.quiz.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/users")
@RestController
public class UserRestController {


    @Autowired
    private UserService userService;

    @PostMapping
    public User criar(@RequestBody User user){
        userService.verificarRegra(user);
        return user;
    }

    //post para criação de um novo usuario chama o services

}
