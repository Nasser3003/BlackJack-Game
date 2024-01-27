package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import play.blackjack.cards.Card;
import play.blackjack.cards.Deck;
import play.blackjack.model.Casino;
import play.blackjack.model.Logs;
import play.blackjack.model.Player;
import play.blackjack.service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class Engine {
    private PlayerService playerService;
    private Logs logs;
    private Casino casino;
    private final Deck deck = new Deck(4);


    private List<Player> players = new ArrayList<>();
    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(Player player) {
        players.remove(player);
    }
    public String play() {
        boolean isHidden = true;
        for (int i = 0; i < 2; i++) {
            for (Player p : players) {
                Card c = deck.deal();
                c.setHidden(isHidden);
                p.hit(c);
            }
        isHidden = false;
        }

        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            System.out.print(
                    "1: Deal\n" +
                    "2: Deal Split Hand\n" +
                    "3: Split\n" +
                    "4: Stay\n" +
                    "5: Stay Split Hand\n" +
                    "6: See Hand\n" +
                    "7: See Split Hand\n" +
                    "Enter your choice (1-7): "
            );

            while (!scanner.hasNextInt()) {
                System.out.print("Enter valid input\n" +
                        "Enter your choice (1-7): ");
                scanner.next();
            }
            userChoice = scanner.nextInt();
        } while (userChoice < 1 || userChoice > 7);



        scanner.close();
        return null;
    }
}

