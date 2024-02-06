package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.model.Player;
import play.blackjack.service.CasinoService;
import play.blackjack.service.LogService;
import play.blackjack.service.PlayerService;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class PostGame {
    private PlayerService playerService;
    private CasinoService casinoService;
    private LogService logService;
    private Engine engine;


    // playerWinLoseValue * getBet if they split and win/lose with main and split hand
    void updatePlayersGainsAndLoses(List<Player> players) {
        long value = 0;
        for (Player p : players) {
            if (p == playerService.getPlayerByEmail(GameLogic.DEALER_EMAIL))
                continue;
            int playerWinLoseValue = p.getIsWonTieLose();
            if (playerWinLoseValue > 0)
                value = playerWinLoseValue *  playerService.getMoney(p);
            else if (playerWinLoseValue < 0)
                value = playerWinLoseValue * -playerService.getMoney(p);
            playerService.adjustMoneyAndEarnings(p, value);
        }
    }

    void updateCasinoGainsAndLoses(List<Player> players) {
        players.forEach(p -> {
            long bet = 0;
            switch (p.getIsWonTieLose()) {
                case 1: bet = -playerService.getMoney(p); break;
                case -1: bet = playerService.getMoney(p);
            }
            casinoService.adjustRevenueAndCapital(engine.getCasino(), bet);
        });
    }
    void generateAndSaveLogs(List<Player> players) {
        for (Player p : players) {
            logService.generateAndSaveLog(p, engine.getCasino(), playerService.getBet(p), p.getIsWonTieLose() > 0);
        }
    }
    void flushPlayersGameStats(List<Player> players) {
        for (Player p : players) {
            playerService.flushPlayerGameStats(p);
        }
    }
}
