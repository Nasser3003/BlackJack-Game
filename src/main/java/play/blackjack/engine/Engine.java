package play.blackjack.engine;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import play.blackjack.cards.Deck;
import play.blackjack.model.Casino;
import play.blackjack.model.Player;
import play.blackjack.service.CasinoService;
import play.blackjack.service.LogService;
import play.blackjack.service.PlayerService;
import play.blackjack.utils.UserInterface;

import java.util.*;

@Data
@RequiredArgsConstructor(onConstructor_={@Autowired, @Lazy})
@NoArgsConstructor
@Component
public class Engine {
    @NonNull
    private PlayerInput playerInput;
    @NonNull
    private PreGame preGame;
    @NonNull
    private PostGame postGame;
    @NonNull
    private GameLogic gameLogic;
    @NonNull
    private PlayerService playerService;
    @NonNull
    private Actions actions;
    @NonNull
    private LogService logService;
    @NonNull
    private CasinoService casinoService;

    @Setter(AccessLevel.NONE)
    private Deck deck = new Deck(4);
    @Setter(AccessLevel.NONE)
    private List<Player> players = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    private List<Player> nonPassPlayers;
    private Casino casino;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Player theUserPlayer = preGame.authenticate(scanner);

        while (playerInput.isWantToPlay(scanner)) {
            preGame.kickBrookePlayers();
            nonPassPlayers = new ArrayList<>(players);
            playerInput.enterBet(scanner, theUserPlayer);
            preGame.startRound();

            Iterator<Player> iterator = nonPassPlayers.iterator();
            while (iterator.hasNext()) {
                Player nonPassPlayer = iterator.next();
                if (nonPassPlayer.isPassHand() && nonPassPlayer.isPassSplitHand())
                    iterator.remove();
                if (nonPassPlayer.equals(theUserPlayer)) {
                    gameLogic.playerMove(nonPassPlayer, theUserPlayer, scanner);
                    if (!theUserPlayer.getSplitHand().isEmpty()) {
                        System.out.println("Now for the second hand?");
                        gameLogic.playerMove(nonPassPlayer, theUserPlayer, scanner);
                    }
                } else {
                    botPlayerAlgorithm(nonPassPlayer, theUserPlayer);
                }
            }
            dealerAlgorithm(preGame.getDealer(), theUserPlayer);

            gameLogic.updatePlayersAsWinLoseOrTie(players);
            postGame.updatePlayersGainsAndLoses(players);
            postGame.updateCasinoGainsAndLoses(players);
            postGame.generateAndSaveLogs(players);

            if (theUserPlayer.getIsWonTieLose() > 0) System.out.println("You Win!");
            else if (theUserPlayer.getIsWonTieLose() < 0) System.out.println("You lose!");
            else System.out.println("Tie");

            System.out.println("Your Cards are: ");
            actions.seeHand(theUserPlayer);
            if (!theUserPlayer.getSplitHand().isEmpty()){
                System.out.println("Your Split hand is :");
                actions.seeSplitHand(theUserPlayer);
            }
            UserInterface.printDashes(15);
            System.out.println("Dealer Cards are: ");
            actions.seeHand(preGame.getDealer());

            postGame.flushPlayersGameStats(players);
            playerService.flushPlayerGameStats(preGame.getDealer());
        }

        scanner.close();
        System.exit(0);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    private void botPlayerAlgorithm(Player player, Player theUserPlayer) {
        int bet;
        Random random = new Random();
        do {
            bet = 25 * (random.nextInt(5) + 1);
        } while (playerService.getMoney(player) <= bet);

        playerService.setBet(player, bet);

        int hand = actions.calculateHandValue(player);
        if (hand < 17 || hand > 17 && random.nextBoolean())
            actions.hit(player, theUserPlayer);
        else
            actions.stay(player);
    }
    private void dealerAlgorithm(Player dealer, Player theUserPlayer) {
        UserInterface.clearScreen();

        while (actions.calculateHandValue(dealer) < 17) {
            actions.hit(dealer, theUserPlayer);
            System.out.println("Dealer Draw a card, His new cards are...");
            actions.seeDealerHand(dealer);
            UserInterface.printDashes();
        }
        System.out.println("Round is over");
        UserInterface.printDashes();
    }
}

