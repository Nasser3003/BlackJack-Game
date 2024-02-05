package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.model.Player;
import play.blackjack.service.PlayerService;

import java.util.Scanner;
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class PlayerInput {
    private PlayerService playerService;
    private static void printChoices() {
        System.out.print(
                "\n1: Hit\n" +
                        "2: Hit Split Hand\n" +
                        "3: Split\n" +
                        "4: Stay\n" +
                        "5: Stay Split Hand\n" +
                        "6: See Hand\n" +
                        "7: See Split Hand\n" +
                        "8: Calculate Hand Value\n" +
                        "9: Calculate Hand Value Split\n" +
                        "10: End My Turn\n" +
                        "Enter your choice (1-10): "
        );
    }
    protected boolean isWantToPlay(Scanner scanner) {
        System.out.print("Do you want to play? (Press Enter to continue, any other key to exit): ");
        return scanner.nextLine().isEmpty();
    }
    protected int getUserInput(Scanner scanner) {
        int userChoice = 0;
        do {
            printChoices();
            if (scanner.hasNextInt())
                userChoice = scanner.nextInt();
            scanner.nextLine();
        } while (userChoice < 1 || userChoice > 10);
        return userChoice;
    }

    private long enterBet(Scanner scanner, Player player) {
        long bet = 0;
        do {
            System.out.print("Amount to bet: ");
            if (scanner.hasNextLong())
                bet = scanner.nextLong();
            scanner.nextLine();
        } while (!playerService.setBet(player, bet));
        return bet;
    }

}