package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import play.blackjack.models.Player;
import play.blackjack.repositories.CasinoRepository;
import play.blackjack.repositories.LogRepository;
import play.blackjack.repositories.PlayerRepository;
import play.blackjack.services.PlayerService;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class Runner implements CommandLineRunner {
    private CasinoRepository casinoRepository;
    private PlayerService playerService;
    private LogRepository logRepository;
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        Player player = new Player("nasser@gmail.com", 1000, encoder.encode("user"));
        playerService.setPlayer(player);
        playerService.save();

    }

}