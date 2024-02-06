package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.model.Player;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class GameLogic {
    private PlayerInput playerInput;
    private Actions actions;

    final static String DEALER_EMAIL = "dealer@house.com";


    void playerMove(Player player, Scanner scanner) {
        gameLoop: while (true) {
            int userChoice = playerInput.getUserInput(scanner);
            switch (userChoice) {
                case 1: actions.hit(player); break;
                case 2: actions.hitSplit(player); break;
                case 3: actions.split(player); break;
                case 4: actions.stay(player);
                    if (actions.calculateSplitHandValue(player) == 0) // if the split hand is empty, end their round after staying
                        break gameLoop;
                    break;
                case 5: actions.staySplit(player); break;
                case 6: actions.seeHand(player); break;
                case 7: actions.seeSplitHand(player); break;
                case 8: System.out.println("\nYour Hand Value: " + actions.calculateHandValue(player)); break;
                case 9:
                    int handValue = actions.calculateSplitHandValue(player);
                    System.out.println(handValue > 0 ? "\nYour Split Hand Value: " + handValue : "\nYou don't have a Split Hand");
                    break;
                case 10: System.out.println(actions.getMoney(player)); break;
                case 11: break gameLoop;
            }
        }
    }

    void updatePlayersAsWinLoseOrTie(List<Player> players) {
        Player dealer = players.stream()
                .filter(p -> p.getEmail().equals(DEALER_EMAIL))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("dealer not found"));
        for (Player p : players) {
            if (p == dealer)
                continue;
            decideHandWinOrLose(p, dealer);
            if (!p.getSplitHand().isEmpty())
                decideSplitHandWinOrLose(p, dealer);
        }
    }
    private void decideHandWinOrLose(Player player, Player dealer) {
        decideAndSetWinners(player, dealer, actions::calculateHandValue);
    }
    private void decideSplitHandWinOrLose(Player player, Player dealer) {
        decideAndSetWinners(player, dealer, actions::calculateSplitHandValue);
    }
    private void decideAndSetWinners(Player player, Player dealer, Function<Player, Integer> handToCalculate) {
        int dealerValue = handToCalculate.apply(dealer);
        int playerValue = handToCalculate.apply(player);
        int playerIsWonTieLoseValue = player.getIsWonTieLose();

        if (playerValue > 21)
            player.setIsWonTieLose(playerIsWonTieLoseValue - 1);
        else if (dealerValue > 21)
            player.setIsWonTieLose(playerIsWonTieLoseValue + 1);
        else if (playerValue > dealerValue)
            player.setIsWonTieLose(playerIsWonTieLoseValue + 1);
        else if (playerValue < dealerValue)
            player.setIsWonTieLose(playerIsWonTieLoseValue - 1);
    }
}
