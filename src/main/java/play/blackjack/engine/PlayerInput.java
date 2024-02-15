package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.model.Player;
import play.blackjack.service.PlayerService;
import play.blackjack.utils.UserInterface;

import java.util.Scanner;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class PlayerInput {
    private static final int FIRST_CHOICE = 1;
    private static final int LAST_CHOICE = 11;
    private PlayerService playerService;

    private static void printChoices() {
        System.out.print(
                "\n" +
                "1: Hit\n" +
                "2: Hit Split Hand\n" +
                "3: Split (you will be charged bet amount for split Hand)\n" + "4: Stay\n" +
                "5: Stay Split Hand\n" +
                "6: See Hand\n" +
                "7: See Split Hand\n" +
                "8: See Dealer's Hand\n" +
                "9: Calculate Hand Value\n" +
                "10: Calculate Hand Value Split\n" +
                "11: Check Your Money\n"+
                "Enter your choice (1-11): "
        );
    }

    boolean isWantToPlay(Scanner scanner) {
        System.out.print("Do you want to play? (Press Enter to continue, any other key to exit): ");
        return scanner.nextLine().isEmpty();
    }

    int getUserInput(Scanner scanner) {
        int userChoice = 0;
        do {
            printChoices();
            if (scanner.hasNextInt()) userChoice = scanner.nextInt();
            scanner.nextLine();
        } while (userChoice < FIRST_CHOICE || userChoice > LAST_CHOICE);
        return userChoice;
    }

    void enterBet(Scanner scanner, Player player) {
        long bet = 0;
        do {
            System.out.print("Enter amount to bet: ");
            if (scanner.hasNextLong()) bet = scanner.nextLong();
            scanner.nextLine();
        } while (!playerService.setBet(player, bet));
    }

    String validatePassword(Scanner scanner) {
        String password;
        String confirmPassword;
        do {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();

            System.out.print("Enter your password again: ");
            confirmPassword = scanner.nextLine();

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Please try again.");
            } else if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please try again.");
            }
        } while (!password.equals(confirmPassword) || password.isEmpty());

        return password;
    }

    String validateEmail(Scanner scanner) {
        String email;
        boolean isValid = false;
        do {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (email.matches(emailRegex)) {
                isValid = true;
            } else
                System.out.println("Invalid email format. Please try again.");
        } while (!isValid);
        return email;
    }



    boolean isUserWantsToLoginOrRegister(Scanner scanner) {
        UserInterface.clearScreen();
        System.out.print("Do you want to login? (Press Enter to login, any other key to Register): ");
        return scanner.nextLine().isEmpty();
    }
    long amountToBuyCredit(Scanner scanner) {
        long amount;
        while (true) {
            System.out.print("Enter Amount to buy: ");
            if (scanner.hasNextLong()) {
                amount = scanner.nextLong();
                scanner.nextLine();
                return amount;
            }
            else {
                System.out.println("invalid input");
                scanner.nextLine();
            }
        }
    }


}