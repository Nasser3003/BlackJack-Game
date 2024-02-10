package play.blackjack.utils;

import play.blackjack.cards.ENUMS.ECardRank;
import play.blackjack.cards.ENUMS.ECardType;

import java.util.HashMap;

public class CardUI {
    static final HashMap<ECardType, String> CARD_TYPE = new HashMap<>();
    static final HashMap<ECardRank, HashMap<ECardType, String>> CARD_ASCII = new HashMap<>();
    static {
        CARD_TYPE.put(ECardType.CLUBS, "hi");
        CARD_TYPE.put(ECardType.CLUBS, "hi");
        CARD_TYPE.put(ECardType.CLUBS, "hi");
        CARD_TYPE.put(ECardType.CLUBS, "hi");
        CARD_ASCII.put(ECardRank.ACE, "hi");
        CARD_ASCII.put(ECardRank.ACE, "hi");
    }
}
