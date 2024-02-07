package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import play.blackjack.engine.Engine;
import play.blackjack.model.Casino;
import play.blackjack.service.CasinoService;
import play.blackjack.service.PlayerService;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class AppStarter {
    private Engine engine;
    private PlayerService playerService;
    private CasinoService casinoService;

    @EventListener(ApplicationReadyEvent.class)
    public void startGame() {

        Casino casino = casinoService.getCasinoByName("Casino 1");


        engine.addPlayer(playerService.getPlayerByEmail("bot1@gmail.com"));
        engine.addPlayer(playerService.getPlayerByEmail("bot2@gmail.com"));

        engine.setCasino(casino);

        engine.start();
    }
}
// TODO login method should add the player to the players list