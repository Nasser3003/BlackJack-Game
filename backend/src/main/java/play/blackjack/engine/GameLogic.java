package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.model.Player;

import java.util.Scanner;
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class GameLogic {
    private PlayerInput playerInput;
    private  Engine engine;
    private Actions actions;


    void playerMove(Player player, Scanner scanner) {
        gameLoop: while (true) {
            int userChoice = playerInput.getUserInput(scanner);
            switch (userChoice) {
                case 1: actions.hit(player); break;
                case 2: actions.hitSplit(player); break;
                case 3: actions.split(player); break;
                case 4: actions.stay(player); break;
                case 5: actions.staySplit(player); break;
                case 6: actions.seeHand(player); break;
                case 7: actions.seeSplitHand(player); break;
                case 8: System.out.println("\nYour Hand Value: " + actions.calculateHandValue(player)); break;
                case 9:
                    int handValue = actions.calculateSplitHandValue(player);
                    System.out.println(handValue > 0 ? "\nYour Split Hand Value: " + handValue : "\nYou don't have a Split Hand");
                    break;
                case 10: break gameLoop;
            }
        }
    }
}
