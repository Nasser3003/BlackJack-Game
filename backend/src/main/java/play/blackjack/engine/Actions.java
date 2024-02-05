package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.model.Player;
import play.blackjack.service.PlayerService;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class Actions {
    private PlayerService playerService;
    private Engine engine;

    void hit(Player player) {
        playerService.hit(player, engine.getDeck());
        int handValue = calculateHandValue(player);

        if (handValue > 21) {
            stay(player);
            System.out.println("You have exceeded 21 with cards valued at " + handValue);
        }
    }
    void hitSplit(Player player) {
        playerService.hitSplit(player, engine.getDeck());
        int handValue = calculateSplitHandValue(player);

        if (handValue > 21) {
            stay(player);
            System.out.println("You have exceeded 21 with cards valued at " + handValue);
        }
    }
    void split(Player player) {
        playerService.split(player);
    } // after splitting the first hand they get should be hidden.
    void stay(Player player) {
        playerService.stay(player);
    }
    void staySplit(Player player) {
        playerService.staySplit(player);
    }
    void seeHand(Player player) {
        System.out.println(playerService.seeHand(player));
    }
    void seeSplitHand(Player player) {
        System.out.println(playerService.seeSplitHand(player));
    }
    int calculateHandValue(Player player) {
        return playerService.calculateHand(player);
    }
    int calculateSplitHandValue(Player player) {
        return playerService.calculateHandSplit(player);
    }
}
