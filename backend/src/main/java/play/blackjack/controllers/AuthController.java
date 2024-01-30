package play.blackjack.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import play.blackjack.model.Player;
import play.blackjack.service.AuthenticationService;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody Player player) {
        authenticationService.registerUser(player.getEmail(), player.getPassword(), player.getMoney());
    }
    
}
