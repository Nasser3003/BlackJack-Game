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

    public void start(Player theUserPlayer) {
        Scanner scanner = new Scanner(System.in);
        while (playerInput.isWantToPlay(scanner)) {
            preGame.kickBrookePlayers();
            nonPassPlayers = new ArrayList<>(players);
            preGame.startRound();

            Iterator<Player> iterator = nonPassPlayers.iterator();
            while (iterator.hasNext()) {
                Player nonPassPlayer = iterator.next();
                if (nonPassPlayer.isPassHand() && nonPassPlayer.isPassSplitHand()) iterator.remove();
                if (nonPassPlayer == theUserPlayer) {
                    gameLogic.playerMove(nonPassPlayer, scanner);
                    if (!theUserPlayer.getSplitHand().isEmpty()) {
                        System.out.println("Now for the second hand?");
                        gameLogic.playerMove(nonPassPlayer, scanner);
                    }
                } else {
                    botPlayerAlgorithm(nonPassPlayer);
                }
            }
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

    private void botPlayerAlgorithm(Player player) {
        int hand = actions.calculateHandValue(player);
        Random random = new Random();
        if (hand < 17 && random.nextBoolean())
            actions.hit(player);
        else
            actions.stay(player);
    }
}
//    private long getMoney() {
//        return playerService.getMoney(player);
//    }
//    private void saveLog(long initialMoney) {
//        logService.generateLog(player);
//    }

// take care of logging get initial money, after losing or winning calculate.
// fix relationship between Logs Casino and Player mainly the casino and log. and how you gonna pass the casino?
// should I remove and make it implicit as there is only 1 casino?

