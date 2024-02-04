package play.blackjack.controllers;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import play.blackjack.exception.InvalidCredentialsException;
import play.blackjack.model.Player;
import play.blackjack.service.AuthenticationService;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody Player player) throws DuplicateKeyException {
        authenticationService.registerUser(player.getEmail(), player.getPassword(), player.getMoney());
    }
    
    @PostMapping("/login")
    public Player login(@RequestBody Player player) throws InvalidCredentialsException {
        return authenticationService.loginUser(player);
    }
}
