package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.model.Player;
import play.blackjack.service.CasinoService;
import play.blackjack.service.LogService;
import play.blackjack.service.PlayerService;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class PostGame {
    private PlayerService playerService;
    private CasinoService casinoService;
    private LogService logService;
    private Engine engine;

    private void updatePlayer(Player player, long bet) {
        boolean won = player.isWon();
        long value = won ? bet : -bet;
        playerService.adjustMoneyAndEarnings(player, value);
    }
    private void updateCasino(Player player, long bet) {
        boolean won = player.isWon();
        long value = won ? -bet : bet;
        casinoService.adjustRevenueAndCapital(engine.getCasino(), value);
    }
    private void saveLog(Player player, long bet, boolean playerWon) {
        logService.generateAndSaveLog(player, engine.getCasino(), bet, playerWon);
    }
}
