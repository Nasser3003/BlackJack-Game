package play.blackjack.models;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import play.blackjack.cards.Card;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String email;

    private int money;
    private int earnings;
    private String password;
    private boolean isHidden = true;

    @OneToMany(mappedBy = "player")
    private List<Logs> logs = new ArrayList<>();

    @Transient
    private List<Card> hand = new ArrayList<>(2);
    @Transient
    private List<Card> splitHand = new ArrayList<>(2);

    public Player(String email, int money, String password) {
        this.email = email;
        this.money = money;
        this.password = password;
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
    public void split() {
        Card c = hand.get(1);
        hand.remove(1);

        c.setHidden(false);
        splitHand.add(c);
    }
    public void stay(){}
    private void hit(List<Card> hand, Card card) {
        hand.add(card);
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
