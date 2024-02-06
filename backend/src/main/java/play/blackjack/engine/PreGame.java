package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.cards.Card;
import play.blackjack.model.Player;
import play.blackjack.service.AuthenticationService;
import play.blackjack.utils.PrintDashes;

import java.util.Scanner;
import java.util.function.Function;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class PreGame {
    private Engine engine;
    private AuthenticationService authService;
    Player login(Scanner scanner) {
        while (true) {
            PrintDashes.printDashes();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            try {
                return authService.login(email, password);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
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
