package play.blackjack.cards;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import play.blackjack.ENUMS.ECardRank;
import play.blackjack.ENUMS.ECardType;

import java.util.ArrayList;
import java.util.List;

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
    private List<Card> deck = new ArrayList<>(52 * setsInDeck);

    // Initialize the Deck
    public Deck(int setsInDeck) {
        this.setsInDeck = setsInDeck;

        for (int i = 0; i < setsInDeck; i++) {
            for (ECardType type : types) {
                for (ECardRank rank : ranks) {
                    deck.add(new Card(rank, type));
                }
            }
        }
    }
}
