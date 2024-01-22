package play.blackjack.cards;

import lombok.AllArgsConstructor;
import lombok.Data;
import play.blackjack.ENUMS.ECardRank;
import play.blackjack.ENUMS.ECardType;

@Data
@AllArgsConstructor
public class Card {
    private ECardRank rank;
    private ECardType cardType;
}
