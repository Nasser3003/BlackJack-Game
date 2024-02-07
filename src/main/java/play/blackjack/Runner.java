package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import play.blackjack.engine.Engine;
import play.blackjack.model.Casino;
import play.blackjack.model.Player;
import play.blackjack.repository.CasinoRepository;
import play.blackjack.service.AuthenticationService;
import play.blackjack.service.PlayerService;

import javax.persistence.EntityManager;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
@Transactional
public class Runner implements CommandLineRunner {
    private CasinoRepository casinoRepository;
    private PasswordEncoder encoder;
    private AuthenticationService authenticationService;
    private Engine engine;
    private EntityManager entityManager;
    private PlayerService playerService;

    @Override
    public void run(String... args) {
        if (!casinoRepository.findById(1L).isPresent()) {

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

            System.out.println("attach status >> " + "Casino: " +
                    entityManager.contains(casino) +
                    " rod " + entityManager.contains(playerRod) +
                    " Josh " + entityManager.contains(playerJosh) +
                    " dealer " + entityManager.contains(dealer)
            );
            engine.start();
        } else {

            Player playerRod = playerService.getPlayerByEmail("rod@gmail.com");
            Player playerJosh = playerService.getPlayerByEmail("josh@gmail.com");
            Player dealer = playerService.getPlayerByEmail("dealer@house.com");

            Casino casino = casinoRepository.findById(1L).get();

            engine.addPlayer(playerRod);
            engine.addPlayer(playerJosh);
            engine.addPlayer(dealer);

            engine.setCasino(casino);

            System.out.println("attach status >> " + "Casino: " +
                    entityManager.contains(casino) +
                    " rod " + entityManager.contains(playerRod) +
                    " Josh " + entityManager.contains(playerJosh) +
                    " dealer " + entityManager.contains(dealer)
            );
            engine.start();
        }

    }
}
// TODO if now i just need to print user experience
// TODO implement user registration
// TODO master the Cascade, and relationships.