package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import play.blackjack.cards.Card;
import play.blackjack.cards.ENUMS.ECardRank;
import play.blackjack.cards.ENUMS.ECardType;
import play.blackjack.model.Player;
import play.blackjack.service.PlayerService;
import play.blackjack.utils.CardUI;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Component
class Actions {
    private PlayerService playerService;
    private Engine engine;

    void hit(Player player, Player theUserPlayer) {
        playerService.hit(player, engine.getDeck());
        int handValue = calculateHandValue(player);

        if (handValue > 21) {
            stay(player);
            if (player.equals(theUserPlayer))
                System.out.println("You have exceeded 21 with cards valued at " + handValue);
        }
    }
    void hitSplit(Player player, Player theUserPlayer) {
        playerService.hitSplit(player, engine.getDeck());
        int handValue = calculateSplitHandValue(player);

        if (handValue > 21) {
            stay(player);
            if (player.equals(theUserPlayer))
                System.out.println("You have exceeded 21 with cards valued at " + handValue);
        }
    }
    void split(Player player) {
        playerService.split(player);
    }
    void stay(Player player) {
        playerService.stay(player);
    }
    void staySplit(Player player) {
        playerService.staySplit(player);
    }

    void seeHand(Player player) {
        List<Card> hand = playerService.seeHand(player);
        for (Card c : hand) {
            ECardRank ecr = c.getRank();
            ECardType ect = c.getCardType();
            switch (ect) {
                case CLUBS: System.out.println(CardUI.CARD_CLUBS.get(ecr)); 
                    break;
                case DIAMONDS: System.out.println(CardUI.CARD_DIAMONDS.get(ecr)); 
                    break;
                case HEARTS: System.out.println(CardUI.CARD_HEARTS.get(ecr)); 
                    break;
                case SPADES: System.out.println(CardUI.CARD_SPADES.get(ecr)); 
                    break;
                default: System.out.println("Default in Actions."); 
                    break;
            }
        }
    }

    void seeSplitHand(Player player) {
        List<Card> hand = playerService.seeHand(player);
        for (Card c : hand) {
            ECardRank ecr = c.getRank();
            ECardType ect = c.getCardType();
            switch (ect) {
                case CLUBS: System.out.println(CardUI.CARD_CLUBS.get(ecr)); 
                    break;
                case DIAMONDS: System.out.println(CardUI.CARD_DIAMONDS.get(ecr)); 
                    break;
                case HEARTS: System.out.println(CardUI.CARD_HEARTS.get(ecr)); 
                    break;
                case SPADES: System.out.println(CardUI.CARD_SPADES.get(ecr)); 
                    break;
                default: System.out.println("Default in Actions."); 
                    break;
            }
        }
    }
    int calculateHandValue(Player player) {
        return playerService.calculateHand(player);
    }
    int calculateSplitHandValue(Player player) {
        return playerService.calculateHandSplit(player);
    }
    long getMoney(Player player) {
        return playerService.getMoney(player);
    }
}
