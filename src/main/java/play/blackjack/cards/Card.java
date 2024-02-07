package play.blackjack.cards;

import lombok.Data;
import play.blackjack.cards.ENUMS.ECardRank;
import play.blackjack.cards.ENUMS.ECardType;

@Data
public class Card {
    private ECardRank rank;
    private ECardType cardType;
    private boolean isHidden = true;

    public Card(ECardRank rank, ECardType cardType) {
        this.rank = rank;
        this.cardType = cardType;
    }

    public int getRankAsInt() {
        return rank.getValue();
    }
}
