package play.blackjack.engine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.cards.Card;
import play.blackjack.model.Player;

import java.util.Scanner;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class PreGame {
    @NonNull
    private Engine engine;
    @NonNull
    private PlayerInput playerInput;
    @NonNull
    private AuthPlayer authPlayer;


    @Getter
    private final Player dealer = new Player();

    public Player authenticate(Scanner scanner) {
        if (playerInput.isUserWantsToLoginOrRegister(scanner))
            return authPlayer.loginUser(scanner);
        authPlayer.registerUser(scanner);
        return authPlayer.loginUser(scanner);
    }

    void kickBrookePlayers() {
        engine.getPlayers().removeIf(player -> player.getMoney() < 1);
    }

    // initialize the round by giving each player 2 cards, the first card is hidden
    void startRound() {
        boolean isHidden = true;

        for (int i = 0; i < 2; i++) {
            Card dealerCard = engine.getDeck().deal();
            dealerCard.setHidden(true);
            dealer.hit(dealerCard); // dealing to dealer
            for (Player p : engine.getPlayers()) {
                Card c = engine.getDeck().deal();
                c.setHidden(isHidden);
                p.hit(c);
            }
            isHidden = false;
        }
    }

}
