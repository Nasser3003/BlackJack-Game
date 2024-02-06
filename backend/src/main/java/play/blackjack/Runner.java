package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import play.blackjack.engine.Engine;
import play.blackjack.model.Casino;
import play.blackjack.model.Player;
import play.blackjack.repository.CasinoRepository;
import play.blackjack.service.AuthenticationService;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class Runner implements CommandLineRunner {
    private CasinoRepository casinoRepository;
    private PasswordEncoder encoder;
    private AuthenticationService authenticationService;
    private Engine engine;

    @Override
    public void run(String... args) {
        Player playerRod = Player.builder()
                .email("rod@gmail.com").password(encoder.encode("rod")).money(1000).build();
        Player playerJosh = Player.builder()
                .email("josh@gmail.com").password(encoder.encode("josh")).money(1000).build();
        Player dealer = Player.builder()
                .email("dealer@house.com").password(encoder.encode("dealer")).money(1000).build();

        Casino casino = new Casino("Casino 1", 2000000000);
        casinoRepository.save(casino);

        authenticationService.registerUser(dealer);
        authenticationService.registerUser(playerRod);
        authenticationService.registerUser(playerJosh);

        engine.addPlayer(playerRod);
        engine.addPlayer(playerJosh);
        engine.addPlayer(dealer);

        engine.setCasino(casino);

        engine.start();
    }
}
// TODO if now i just need to print user experience
// TODO implement user registration