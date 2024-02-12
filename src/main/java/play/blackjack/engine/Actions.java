package play.blackjack.engine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        List<Card> hand = playerService.getHand(player);
        List<String> handInUi = new ArrayList<>();
        hand.forEach(c -> handInUi.add(getCardUI(c)));
        printCardsSideBySide(handInUi, hand);
    }

    void seeSplitHand(Player player) {
        List<Card> splitHand = playerService.getSplitHand(player);
        List<String> splitHandInUi = new ArrayList<>();
        splitHand.forEach(c -> splitHandInUi.add(getCardUI(c)));
        printCardsSideBySide(splitHandInUi, splitHand);

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
    private String getCardUI (Card c) {
        ECardRank ecr = c.getRank();
        ECardType ect = c.getCardType();
        String cardUi = null;
        switch (ect) {
            case CLUBS:
                cardUi = CardUI.CARD_CLUBS.get(ecr);
                break;
            case DIAMONDS:
                cardUi = CardUI.CARD_DIAMONDS.get(ecr);
                break;
            case HEARTS:
                cardUi = CardUI.CARD_HEARTS.get(ecr);
                break;
            case SPADES:
                cardUi = CardUI.CARD_SPADES.get(ecr);
        }

        return cardUi;
    }
    private void printCardsSideBySide(List<String> handInUi, List<Card> cards) {
        String[][] allCardsLinesSplit = new String[handInUi.size()][];

        StringBuilder hiddenStatus = new StringBuilder();
        cards.forEach( c -> hiddenStatus.append(" ").append(c.isHidden()).append("\t"));

        // split each card into lines
        for (int i = 0; i < handInUi.size(); i++) {
            allCardsLinesSplit[i] = handInUi.get(i).split("\r\n");
        }

        // print cards side by side
        for (int i = 0; i < allCardsLinesSplit[0].length; i++) {
            StringBuilder sb = new StringBuilder();
            for (String[] cardLines : allCardsLinesSplit) {
                sb.append(cardLines[i]).append("\t");
            }
            System.out.println(sb);
        }
        // print each card is hidden or not
        System.out.println(hiddenStatus);
    }
}
