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

    List<Card> hand = new ArrayList<>(2);
    List<Card> splitHand = new ArrayList<>(2);

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public void hit(Card card) {
        hit(hand, card);
    }
    public void hitSplit(Card card) {
        hit(splitHand, card);
    }
    public int calculateHandValue() {
        return calculateHandValue(hand);
    }
    public int calculateHandValueSplit() {
        return calculateHandValue(splitHand);
    }
    private void hit(List<Card> hand, Card card) {
        hand.add(card);
    }
    public void split() {
        if (hand.get(0).getRankAsInt() == hand.get(1).getRankAsInt())
            System.out.println("Cant split, you dont have a pair");
        else {
            splitHand.add(hand.get(1));
            hand.remove(1);
        }
    }
    public void stay(){

    }
    private int calculateHandValue(List<Card> hand) {
        int sum = 0;

        List<Card> aces = new ArrayList<>();

        // we added all the none-ace cards
        for (Card c : hand) {
            if (c.getRankAsInt() != 1)
                sum += c.getRankAsInt();
            else
                aces.add(c);
        }

        if (aces.isEmpty()) return sum;

        // if there is ace do the logic
        if ((sum + 11) > 21)
            for (Card ace : aces)
                 sum += ace.getRankAsInt();

        return sum;
    }
}
