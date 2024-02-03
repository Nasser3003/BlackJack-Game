package play.blackjack.model;

import lombok.*;
import play.blackjack.cards.Card;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Player {

    @Id
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;

    private long money;
    private long earnings;
    private String password;

    @OneToMany(mappedBy = "player")
    private List<Logs> logs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "casino_id")
    private Casino casino;

    @Transient
    private List<Card> hand = new ArrayList<>(2);
    @Transient
    private List<Card> splitHand = new ArrayList<>(2);
    @Transient
    private long bet;
    @Transient
    private boolean passHand;
    @Transient
    private boolean passSplitHand = true;
    @Transient
    private boolean isWon;

    @Builder
    public Player(String email, long money, String password) {
        this.email = email;
        this.username = email;
        this.money = money;
        this.password = password;
    }
    public void hit(Card card) {
        hit(hand, card);
    }
    public void hitSplit(Card card) {
        hit(splitHand, card);
    }
    public void split() {
        Card c = hand.get(0);
        hand.remove(0);

        c.setHidden(false);
        splitHand.add(c);
        passSplitHand = false;
    }
    public void stay() {
        passHand = true;
    }
    public void staySplit() {
        passSplitHand = true;
    }
    public void adjustMoneyAndEarnings(long value) {
        money += value;
        earnings += value;
    }
    private void hit(List<Card> hand, Card card) {
        hand.add(card);
    }
}
