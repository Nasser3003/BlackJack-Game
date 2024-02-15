package play.blackjack.utils;

import play.blackjack.cards.ENUMS.ECardRank;

import java.util.HashMap;

public class CardUI {

    public static final String hiddenCard =
                    " _____\r\n" +
                    "|%=%=%|\r\n" +
                    "|=%=%=|\r\n" +
                    "|%=%=%|\r\n" +
                    "|=%=%=|\r\n" +
                    "|_____|\r\n"
    ;
    public static final HashMap<ECardRank, String> CARD_CLUBS = new HashMap<>();
    public static final HashMap<ECardRank, String> CARD_DIAMONDS = new HashMap<>();
    public static final HashMap<ECardRank, String> CARD_HEARTS = new HashMap<>();
    public static final HashMap<ECardRank, String> CARD_SPADES = new HashMap<>();
    static {

        // CLUBS


        CARD_CLUBS.put(ECardRank.ACE,
                        " _____\r\n" +
                        "|A _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____V|\r\n"
        );
        CARD_CLUBS.put(ECardRank.TWO,
                " _____\r\n" +
                        "|2 _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____2|\r\n"
        );
        CARD_CLUBS.put(ECardRank.THREE,
                " _____\r\n" +
                        "|3 _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____3|\r\n"
        );
        CARD_CLUBS.put(ECardRank.FOUR,
                        " _____\r\n" +
                        "|4 _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____4|\r\n"
        );
        CARD_CLUBS.put(ECardRank.FIVE,
                        " _____\r\n" +
                        "|5 _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____5|\r\n"
        );
        CARD_CLUBS.put(ECardRank.SIX,
                " _____\r\n" +
                        "|6 _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____6|\r\n"
        );
        CARD_CLUBS.put(ECardRank.SEVEN,
                " _____\r\n" +
                        "|7 _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____7|\r\n"
        );
        CARD_CLUBS.put(ECardRank.EIGHT,
                " _____\r\n" +
                        "|8 _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____8|\r\n"
        );
        CARD_CLUBS.put(ECardRank.NINE,
                " _____\r\n" +
                        "|9 _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____9|\r\n"
        );
        CARD_CLUBS.put(ECardRank.TEN,
                " _____\r\n" +
                        "|10 _ |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|___10|\r\n"
        );
        CARD_CLUBS.put(ECardRank.JACK,
                        " _____\r\n" +
                        "|J _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____J|\r\n");
        CARD_CLUBS.put(ECardRank.QUEEN,
                        " _____\r\n" +
                        "|Q _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____Q|\r\n"
        );
        CARD_CLUBS.put(ECardRank.KING,
                        " _____\r\n" +
                        "|K _  |\r\n" +
                        "| ( ) |\r\n" +
                        "|(_'_)|\r\n" +
                        "|  |  |\r\n" +
                        "|____K|\r\n"
        );

        // DIAMONDS

        CARD_DIAMONDS.put(ECardRank.ACE,
                        " _____\r\n" +
                        "|A ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____V|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.TWO,
                        " _____\r\n" +
                        "|2 ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____2|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.THREE,
                        " _____\r\n" +
                        "|3 ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____3|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.FOUR,
                        " _____\r\n" +
                        "|4 ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____4|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.FIVE,
                        " _____\r\n" +
                        "|5 ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____5|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.SIX,
                        " _____\r\n" +
                        "|6 ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____6|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.SEVEN,
                        " _____\r\n" +
                        "|7 ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____7|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.EIGHT,
                        " _____\r\n" +
                        "|8 ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____8|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.NINE,
                        " _____\r\n" +
                        "|9 ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____9|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.TEN,
                        " _____\r\n" +
                        "|10 ^ |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|___10|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.JACK,
                        " _____\r\n" +
                        "|J ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____J|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.QUEEN,
                        " _____\r\n" +
                        "|Q ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____Q|\r\n"
        );
        CARD_DIAMONDS.put(ECardRank.KING,
                        " _____\r\n" +
                        "|K ^  |\r\n" +
                        "| / \\ |\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____K|\r\n"
        );

        // HEARTS

        CARD_HEARTS.put(ECardRank.ACE,
                        " _____\r\n" +
                        "|A_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____V|\r\n"
        );
        CARD_HEARTS.put(ECardRank.TWO,
                        " _____\r\n" +
                        "|2_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____2|\r\n"
        );
        CARD_HEARTS.put(ECardRank.THREE,
                        " _____\r\n" +
                        "|3_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____3|\r\n"
        );
        CARD_HEARTS.put(ECardRank.FOUR,
                         " _____\r\n" +
                        "|4_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____4|\r\n"
        );
        CARD_HEARTS.put(ECardRank.FIVE,
                        " _____\r\n" +
                        "|5_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____5|\r\n"
        );
        CARD_HEARTS.put(ECardRank.SIX,
                        " _____\r\n" +
                        "|6_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____6|\r\n"
        );
        CARD_HEARTS.put(ECardRank.SEVEN,
                        " _____\r\n" +
                        "|7_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____7|\r\n"
        );
        CARD_HEARTS.put(ECardRank.EIGHT,
                        " _____\r\n" +
                        "|8_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____8|\r\n"
        );
        CARD_HEARTS.put(ECardRank.NINE,
                        " _____\r\n" +
                        "|9_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____9|\r\n"
        );
        CARD_HEARTS.put(ECardRank.TEN,
                        " _____\r\n" +
                        "|10_ _|\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|___10|\r\n"
        );
        CARD_HEARTS.put(ECardRank.JACK,
                        " _____\r\n" +
                        "|J_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____J|\r\n"
        );
        CARD_HEARTS.put(ECardRank.QUEEN,
                        " _____\r\n" +
                        "|Q_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____Q|\r\n"
        );
        CARD_HEARTS.put(ECardRank.KING,
                        " _____\r\n" +
                        "|K_ _ |\r\n" +
                        "|( v )|\r\n" +
                        "| \\ / |\r\n" +
                        "|  .  |\r\n" +
                        "|____K|\r\n"
        );

        // SPADES

        CARD_SPADES.put(ECardRank.ACE,
                        " _____\r\n" +
                        "|A .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____V|\r\n"
        );
        CARD_SPADES.put(ECardRank.TWO,
                        " _____\r\n" +
                        "|2 .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____2|\r\n"
        );
        CARD_SPADES.put(ECardRank.THREE,
                        " _____\r\n" +
                        "|3 .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____3|\r\n"
        );
        CARD_SPADES.put(ECardRank.FOUR,
                        " _____\r\n" +
                        "|4 .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____4|\r\n"
        );
        CARD_SPADES.put(ECardRank.FIVE,
                        " _____\r\n" +
                        "|5 .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____5|\r\n"
        );
        CARD_SPADES.put(ECardRank.SIX,
                        " _____\r\n" +
                        "|6 .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____6|\r\n"
        );
        CARD_SPADES.put(ECardRank.SEVEN,
                        " _____\r\n" +
                        "|7 .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____7|\r\n");
        CARD_SPADES.put(ECardRank.EIGHT,
                        " _____\r\n" +
                        "|8 .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____8|\r\n"
        );
        CARD_SPADES.put(ECardRank.NINE,
                        " _____\r\n" +
                        "|9 .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____9|\r\n"
        );
        CARD_SPADES.put(ECardRank.TEN,
                        " _____\r\n" +
                        "|10.  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|___10|\r\n"
        );
        CARD_SPADES.put(ECardRank.JACK,
                        " _____\r\n" +
                        "|J .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____J|\r\n"
        );
        CARD_SPADES.put(ECardRank.QUEEN,
                        " _____\r\n" +
                        "|Q .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____Q|\r\n"
        );
        CARD_SPADES.put(ECardRank.KING,
                        " _____\r\n" +
                        "|K .  |\r\n" +
                        "| /.\\ |\r\n" +
                        "|(_._)|\r\n" +
                        "|  |  |\r\n" +
                        "|____K|\r\n"
        );
    }
}
