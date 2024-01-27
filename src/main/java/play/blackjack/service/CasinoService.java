package play.blackjack.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.blackjack.model.Casino;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CasinoService {
    public void adjustRevenueAndCapital(Casino casino, long value) {
        casino.adjustRevenueAndCapital(value);
    }
}
