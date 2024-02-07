package play.blackjack.engine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import play.blackjack.cards.Card;
import play.blackjack.model.Player;
import play.blackjack.service.AuthenticationService;
import play.blackjack.utils.PrintDashes;

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
    private AuthenticationService authService;

    @Getter
    private final Player dealer = new Player();

    public Player login(Scanner scanner) {
        while (true) {
            PrintDashes.printDashes();
            String email = playerInput.validateEmail(scanner);
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            try {
                Player p = authService.login(email, password);
                engine.addPlayer(p);
                return p;
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
            dealer.hit(engine.getDeck().deal()); // dealing to dealer
            for (Player p : engine.getPlayers()) {
                Card c = engine.getDeck().deal();
                c.setHidden(isHidden);
                p.hit(c);
            }
            isHidden = false;
        }
    }

}
