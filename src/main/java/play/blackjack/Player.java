package play.blackjack;

import lombok.Data;
import play.blackjack.cards.Card;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {
    private String name;
    private int money;
    private int earnings;
    List<Card> hand = new ArrayList<>();
    List<Card> splitHand = new ArrayList<>();

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    private void hit(List<Card> hand) {
        // asks for a new card
    }

    private void split() {
        // if you get pair you can split two 2 hands
    }
    private void stay(){

    }
    private int calculateHandValue(List<Card> hand) {
        // make sure the Ace 1 or 11
    }
}
