package play.blackjack.cards;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import play.blackjack.ENUMS.ECardRank;
import play.blackjack.ENUMS.ECardType;

import java.util.*;

@Data
public class Deck {
    static final ECardType[] types = {ECardType.SPADES, ECardType.HEARTS, ECardType.DIAMONDS, ECardType.CLUBS};

    static final ECardRank[] ranks = {
            ECardRank.ACE,
            ECardRank.TWO,
            ECardRank.THREE,
            ECardRank.FOUR,
            ECardRank.FIVE,
            ECardRank.SIX,
            ECardRank.SEVEN,
            ECardRank.EIGHT,
            ECardRank.NINE,
            ECardRank.TEN,
            ECardRank.JACK,
            ECardRank.QUEEN,
            ECardRank.KING
    };

    private int setsInDeck;

    @Setter(AccessLevel.NONE)
    private ArrayDeque<Card> deck = new ArrayDeque<>(setsInDeck * 52);

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private List<Card> tempList = new ArrayList<>(setsInDeck * 52);

    // Deck Initialized and shuffled
    public Deck(int setsInDeck) {
        this.setsInDeck = setsInDeck;

        for (int i = 0; i < setsInDeck; i++) {
            for (ECardType type : types) {
                for (ECardRank rank : ranks) {
                    tempList.add(new Card(rank, type));
                }
            }
        }
        Collections.shuffle(tempList);
        deck.addAll(tempList);
    }

    public void shuffle() {
        Collections.shuffle(tempList);
        deck.addAll(tempList);
    }

    public Card deal() {
        if (deck.isEmpty())
            shuffle();
        return deck.pollLast();
    }
}
