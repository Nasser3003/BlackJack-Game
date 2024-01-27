package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import play.blackjack.model.Casino;
import play.blackjack.model.Logs;
import play.blackjack.model.Player;
import play.blackjack.repository.CasinoRepository;
import play.blackjack.repository.LogRepository;
import play.blackjack.service.AuthenticationService;
import play.blackjack.service.PlayerService;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class Runner implements CommandLineRunner {
    private CasinoRepository casinoRepository;
    private PlayerService playerService;
    private LogRepository logRepository;
    private PasswordEncoder encoder;
    private AuthenticationService authenticationService;
    private Engine engine;

    @Override
    public void run(String... args) {
        Player player = new Player("nasser@gmail.com", 1000, encoder.encode("user"));
        Player house = new Player("house@gmail.com", 1000000, encoder.encode("house"));
        authenticationService.registerUser(player);
        authenticationService.registerUser(house);

        engine.addPlayer(player);
        engine.addPlayer(house);
        Casino casino = new Casino("Casino 1", 20000000);
        casinoRepository.save(casino);
        Logs log = new Logs(player, casino);
        logRepository.save(log);

        // play will return if they won or lose
        // if they want to play again we relaunch.
        engine.setPlayer(player);
        engine.play();
    }
}
