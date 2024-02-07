package play.blackjack.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.blackjack.model.Casino;
import play.blackjack.repository.CasinoRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CasinoService {
    private CasinoRepository casinoRepository;

    public void adjustRevenueAndCapital(Casino casino, long value) {
        casino.adjustRevenueAndCapital(value);
    }

    public Casino getCasinoByName(String name) {
        Optional<Casino> casinoOptional = casinoRepository.findCasinoByName(name);
        if (casinoOptional.isPresent())
            return casinoOptional.get();
        else throw new NoSuchElementException("No Casino with Name: " + name);
    }
}
