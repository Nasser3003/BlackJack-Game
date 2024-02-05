package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.cards.Card;
import play.blackjack.model.Player;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class PreGame {
    private Engine engine;
    void kickBrookePlayers() {
        engine.getPlayers().removeIf(player -> player.getMoney() < 1);
    }

    // initialize the round by giving each player 2 cards, the first card is hidden
    void startRound() {
        boolean isHidden = true;

        for (int i = 0; i < 2; i++) {
            for (Player p : engine.getPlayers()) {
                Card c = engine.getDeck().deal();
                c.setHidden(isHidden);
                p.hit(c);
            }
            isHidden = false;
        }
    }

}
