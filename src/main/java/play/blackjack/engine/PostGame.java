package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import play.blackjack.model.Player;
import play.blackjack.service.CasinoService;
import play.blackjack.service.LogService;
import play.blackjack.service.PlayerService;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class PostGame {
    private PlayerService playerService;
    private CasinoService casinoService;
    private LogService logService;
    private Engine engine;
    private EntityManager entityManager;


    // playerWinLoseValue * getBet if they split and win/lose with main and split hand
    @Transactional
    public void updatePlayersGainsAndLoses(List<Player> players) {
        for (Player p : players) {
                playerService.adjustMoneyAndEarnings(p, p.getIsWonTieLose() * p.getBet());
                entityManager.merge(p);
        }
    }

    @Transactional
    public void updateCasinoGainsAndLoses(List<Player> players) {
        for (Player p : players) {
                casinoService.adjustRevenueAndCapital(engine.getCasino(), - p.getIsWonTieLose() * p.getBet());
                entityManager.merge(engine.getCasino());
        }
    }
    @Transactional
    public void generateAndSaveLogs(List<Player> players) {
        for (Player p : players)
            logService.generateAndSaveLog(p, engine.getCasino());

    }

    public void flushPlayersGameStats(List<Player> players) {
        for (Player p : players) {
            playerService.flushPlayerGameStats(p);
        }
    }
}
