package play.blackjack;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.blackjack.cards.Card;
import play.blackjack.cards.Deck;
import play.blackjack.model.Casino;
import play.blackjack.model.Player;
import play.blackjack.service.CasinoService;
import play.blackjack.service.LogService;
import play.blackjack.service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
public class Engine {
    @NonNull
    private PlayerService playerService;
    @NonNull
    private LogService logService;
    @NonNull
    private CasinoService casinoService;

    private Deck deck = new Deck(4);
    private List<Player> players = new ArrayList<>();
    private Player player;
    private Casino casino;

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(Player player) {
        players.remove(player);
    }
    public boolean play() {
        firstHandInGame();
        Scanner scanner = new Scanner(System.in);

        scanner.close();
        return false;
    }
    private boolean playerMove(Scanner scanner) {
        int userChoice;
        do {
            printChoices();
            while (!scanner.hasNextInt()) {
                System.out.print("Enter valid input\n" +
                        "Enter your choice (1-9): ");
                scanner.next();
            }
            userChoice = scanner.nextInt();
        } while (userChoice < 1 || userChoice > 9);

        // run functions
        switch (userChoice) {
            case 1: hit(); break;
            case 2: hitSplit(); break;
            case 3: split(); break;
            case 4: stay(); break;
            case 5: staySplit(); break;
            case 6: seeHand(); break;
            case 7: seeSplitHand(); break;
            case 8: calculateHandValue(); break;
            case 9: calculateHandValueSplit();
        }
        return false;
    }
    private void printChoices() {
        System.out.print(
                "1: Hit\n" +
                        "2: Hit Split Hand\n" +
                        "3: Split\n" +
                        "4: Stay\n" +
                        "5: Stay Split Hand\n" +
                        "6: See Hand\n" +
                        "7: See Split Hand\n" +
                        "8: Calculate Hand Value\n" +
                        "9: Calculate Hand Value Split\n" +
                        "Enter your choice (1-9): "
        );
    }
    private void firstHandInGame() {
        boolean isHidden = true;
        for (int i = 0; i < 2; i++) {
            for (Player p : players) {
                Card c = deck.deal();
                c.setHidden(isHidden);
                p.hit(c);
            }
            isHidden = false;
        }
    }
    private void hit() {
        playerService.hit(player, deck);
    }
    private void split() {
        playerService.split(player);
    }
    private void hitSplit() {
        playerService.hitSplit(player, deck);
    }
    private void stay() {
        playerService.stay(player);
    }
    private void staySplit() {
        playerService.staySplit(player);
    }
    private void seeHand() {
        System.out.println(playerService.seeHand(player));
    }
    private void seeSplitHand() {
        System.out.println(playerService.seeSplitHand(player));
    }
    private void calculateHandValue() {
        System.out.println(playerService.calculateHand(player));

    }
    private void calculateHandValueSplit() {
        System.out.println(playerService.calculateHandSplit(player));
    }
    private void updatePlayer(long bet, boolean playerWon) {
        long value = won ? bet : -bet;
        playerService.adjustMoneyAndEarnings(player, value);
    }
    private void updateCasino(long bet, boolean playerWon) {
        long value = won ? bet : -bet;
        casinoService.adjustRevenueAndCapital(casino, value);
    }
    private void saveLog(long bet, boolean playerWon) {
        logService.generateAndSaveLog(player, casino, bet, playerWon);
    }
//    private long getMoney() {
//        return playerService.getMoney(player);
//    }
//    private void saveLog(long intialMoney) {
//        logService.generateLog(player);
//    }
}
// take care of logging get initial money, after losing or winning calculate.
// fix relationship between Logs Casino and Player mainly the casino and log.. and how you gonna pass the casino?
// should i remove and make it implicit as there is only 1 casino?

