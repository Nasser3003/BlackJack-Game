package play.service.cards;

import lombok.Data;
import play.service.cards.ENUMS.ECardRank;
import play.service.cards.ENUMS.ECardType;

@Data
public class Card {
    private ECardRank rank;
    private ECardType cardType;

    public Card(ECardRank rank, ECardType cardType) {
        this.rank = rank;
        this.cardType = cardType;
    }

    private boolean isHidden;

    public int getRankAsInt() {
        return rank.getValue();
    }
}
