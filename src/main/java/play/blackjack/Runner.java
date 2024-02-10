package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import play.blackjack.engine.Engine;
import play.blackjack.model.Casino;
import play.blackjack.repository.CasinoRepository;
import play.blackjack.service.AuthenticationService;
import play.blackjack.service.CasinoService;
import play.blackjack.service.PlayerService;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class Runner implements CommandLineRunner {
    private CasinoRepository casinoRepository;
    private AuthenticationService authenticationService;
    private Engine engine;
    private PlayerService playerService;
    private CasinoService casinoService;


    @Override
    public void run(String... args) {
        if (!casinoRepository.findCasinoByName("Casino 1").isPresent()) {

            Casino casino = new Casino("Casino 1", 2000000000);
            casinoRepository.save(casino);

            authenticationService.register("bot1@gmail.com", "bot1", 10000L);
            authenticationService.register("bot2@gmail.com", "bot2", 10000L);

        }

        engine.addPlayer(playerService.getPlayerByEmail("bot1@gmail.com"));
        engine.addPlayer(playerService.getPlayerByEmail("bot2@gmail.com"));

        Casino casino = casinoService.getCasinoByName("Casino 1");
        engine.setCasino(casino);

        engine.start();
    }
}
