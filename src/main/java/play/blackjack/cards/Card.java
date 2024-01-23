package play.blackjack.cards;

import lombok.AllArgsConstructor;
import lombok.Data;
import play.blackjack.cards.ENUMS.ECardRank;
import play.blackjack.cards.ENUMS.ECardType;

@Data
@AllArgsConstructor
public class Card {
    private ECardRank rank;
    private ECardType cardType;
    private boolean isHidden;

    public int getRankAsInt() {
        return rank.getValue();
    }
}
