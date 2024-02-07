package play.blackjack.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.blackjack.model.Casino;
import play.blackjack.model.Logs;
import play.blackjack.model.Player;
import play.blackjack.repository.LogRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LogService {
    private LogRepository logRepository;
    public void save(Logs log) {
        logRepository.save(log);
    }

    public void generateAndSaveLog(Player player, Casino casino) {
        Logs log = new Logs(player, casino);

        log.setPlayerMoneyAdjustment(player.getBet() * player.getIsWonTieLose());
        log.setCasinoRevenueAdjustment(- player.getBet() * player.getIsWonTieLose());

        logRepository.save(log);
    }

}
