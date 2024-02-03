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

import java.util.*;

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
    private List<Player> nonPassPlayers;
    private Casino casino;

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(Player player) {
        players.remove(player);
    }
    public void start(Player theUserPlayer) {
        kickBrookePlayers();
        nonPassPlayers = new ArrayList<>(players);
        startRound();
        Scanner scanner = new Scanner(System.in);

        Iterator<Player> iterator = nonPassPlayers.iterator();
        while (iterator.hasNext()) {
            Player nonPassPlayer = iterator.next();
            if (nonPassPlayer.isPassHand() && nonPassPlayer.isPassSplitHand()) iterator.remove();
            if (nonPassPlayer == theUserPlayer) {
                playerMove(nonPassPlayer, scanner);
                if (!theUserPlayer.getSplitHand().isEmpty()) {
                    System.out.println("Now for the second hand?");
                    playerMove(nonPassPlayer, scanner);
                }
            } else {
                botPlayerAlgorithm(nonPassPlayer);
            }
        }
        scanner.close();
    }
    private void playerMove(Player player, Scanner scanner) {
        gameLoop: while (true) {
            int userChoice = getUserInput(scanner);
            switch (userChoice) {
                case 1: hit(player); break;
                case 2: hitSplit(player); break;
                case 3: split(player); break;
                case 4: stay(player); break;
                case 5: staySplit(player); break;
                case 6: seeHand(player); break;
                case 7: seeSplitHand(player); break;
                case 8: System.out.println(calculateHandValue(player)); break;
                case 9: System.out.println(calculateHandValueSplit(player)); break;
                case 10: break gameLoop;
            }
        }
    }

    private int getUserInput(Scanner scanner) {
        int userChoice = 0;
        do {
            printChoices();
            if (scanner.hasNextInt())
                userChoice = scanner.nextInt();
            scanner.nextLine();
        } while (userChoice < 1 || userChoice > 10);
        return userChoice;
    }
    private static void printChoices() {
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
                        "10: End My Turn" +
                        "Enter your choice (1-9): "
        );
    }

    // initialize the round by giving each player 2 cards, the first card is hidden
    private void startRound() {
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
    private void hit(Player player) {
        playerService.hit(player, deck);
    }
    private void split(Player player) {
        playerService.split(player);
    } // after splitting the first hand they get should be hidden.
    private void hitSplit(Player player) {
        playerService.hitSplit(player, deck);
    }
    private void stay(Player player) {
        playerService.stay(player);
    }
    private void staySplit(Player player) {
        playerService.staySplit(player);
    }
    private void seeHand(Player player) {
        System.out.println(playerService.seeHand(player));
    }
    private void seeSplitHand(Player player) {
        System.out.println(playerService.seeSplitHand(player));
    }
    private int calculateHandValue(Player player) {
        return playerService.calculateHand(player);
    }
    private int calculateHandValueSplit(Player player) {
        return playerService.calculateHandSplit(player);
    }
    private void enterBet(Scanner scanner, Player player, long amount) {
        long bet = 0;
        do {
            System.out.print("Amount to bet: ");
            if (scanner.hasNextLong())
                bet = scanner.nextLong();
            scanner.nextLine();
        } while (!playerService.setBet(player, bet));
    }
    private void kickBrookePlayers() {
        players.removeIf(player -> player.getMoney() < 1);
    }
    private void botPlayerAlgorithm(Player player) {
        int hand = calculateHandValue(player);
        Random random = new Random();
        if (hand < 17 && random.nextBoolean())
            hit(player);
        else
            stay(player);
    }
    private void updatePlayer(Player player, long bet) {
        boolean won = player.isWon();
        long value = won ? bet : -bet;
        playerService.adjustMoneyAndEarnings(player, value);
    }
    private void updateCasino(Player player, long bet) {
        boolean won = player.isWon();
        long value = won ? -bet : bet;
        casinoService.adjustRevenueAndCapital(casino, value);
    }
    private void saveLog(Player player, long bet, boolean playerWon) {
        logService.generateAndSaveLog(player, casino, bet, playerWon);
    }
//    private long getMoney() {
//        return playerService.getMoney(player);
//    }
//    private void saveLog(long initialMoney) {
//        logService.generateLog(player);
//    }
}
// take care of logging get initial money, after losing or winning calculate.
// fix relationship between Logs Casino and Player mainly the casino and log. and how you gonna pass the casino?
// should I remove and make it implicit as there is only 1 casino?

