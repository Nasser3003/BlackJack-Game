package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import play.blackjack.cards.Card;
import play.blackjack.model.Player;
import play.blackjack.service.AuthenticationService;
import play.blackjack.utils.PrintDashes;

import javax.transaction.Transactional;
import java.util.Scanner;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class PreGame {
    private Engine engine;
    private PlayerInput playerInput;
    private AuthenticationService authService;

    public Player login(Scanner scanner) {
        while (true) {
            PrintDashes.printDashes();
            String email = playerInput.validateEmail(scanner);
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            try {
                return authService.login(email, password);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void Register(Scanner scanner) {
        while (true) {
            PrintDashes.printDashes();
            String email = playerInput.validateEmail(scanner);
            String password = playerInput.validatePassword(scanner);
            long money = playerInput.setInitialMoney(scanner);
            try {
                authService.registerUser(email, password, money);
                break;
            } catch (DuplicateKeyException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void kickBrookePlayers() {
        engine.getPlayers().removeIf(player -> player.getMoney() < 1);
    }

    // initialize the round by giving each player 2 cards, the first card is hidden
    public void startRound() {
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
