package play.blackjack.engine;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import play.blackjack.model.Player;
import play.blackjack.service.AuthenticationService;
import play.blackjack.utils.UserInterface;

import java.util.Scanner;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class AuthPlayer {
    @NonNull
    private Engine engine;
    @NonNull
    private PlayerInput playerInput;
    @NonNull
    private AuthenticationService authenticationService;

    @Transactional
    public Player loginUser(Scanner scanner) {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│                 Login                   │");
        System.out.println("└─────────────────────────────────────────┘");
        while (true) {
            UserInterface.printDashes();
            String email = playerInput.validateEmail(scanner);
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            try {
                Player p = authenticationService.login(email, password);
                System.out.printf("Wallet: $%s \n", p.getMoney());
                System.out.print("Do you want to buy credit? (Press Enter to buy, any other key to exit): ");
                if (scanner.nextLine().isEmpty()) {
                    p.setMoney(p.getMoney() + playerInput.amountToBuyCredit(scanner));
                    System.out.println("Your wallet now has: " + p.getMoney());
                }
                engine.addPlayer(p);
                return p;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @Transactional
    public void registerUser(Scanner scanner) {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│              Registration               │");
        System.out.println("└─────────────────────────────────────────┘");
        while (true) {
            UserInterface.printDashes();
            String email = playerInput.validateEmail(scanner);
            String password = playerInput.validatePassword(scanner);
            try {
                authenticationService.register(email, password);
                break;
            } catch (DuplicateKeyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
