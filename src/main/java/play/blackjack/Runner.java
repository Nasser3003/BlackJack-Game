package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
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


    @Override
    public void run(String... args) {
        if (!casinoRepository.findById(1L).isPresent()) {

            Player playerRod = Player.builder()
                    .email("bot1@gmail.com").password(encoder.encode("bot")).money(1000).build();
            Player playerJosh = Player.builder()
                    .email("bot2@gmail.com").password(encoder.encode("bot")).money(1000).build();
            Player dealer = Player.builder()
                    .email("rod@gmail.com").password(encoder.encode("rod")).money(1000).build();

            Casino casino = new Casino("Casino 1", 2000000000);
            casinoRepository.save(casino);

            authenticationService.registerUser(dealer);
            authenticationService.registerUser(playerRod);
            authenticationService.registerUser(playerJosh);

        }
    }
}
// TODO if now i just need to print user experience
// TODO implement user registration
// TODO master the Cascade, and relationships.