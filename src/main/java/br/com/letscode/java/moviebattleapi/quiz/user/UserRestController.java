package br.com.letscode.java.moviebattleapi.quiz.user;

import br.com.letscode.java.moviebattleapi.security.Criptografia;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/users")
@RestController
public class UserRestController {

    private final UserService userService;
    private final Criptografia cripto;
    
  
    @PostMapping
    public User criar(@RequestBody User user){

       user.setPassword(cripto.encode(user.getPassword().toString()));
       
	   //TODO Criar log INFO
	   userService.criar(user)
       
	   return user;
       
   
    }

   
}
