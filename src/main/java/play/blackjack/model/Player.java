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

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Logs> logs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "casino_id")
    private Casino casino;

    @Transient
    private List<Card> hand = new ArrayList<>(6);
    @Transient
    private List<Card> splitHand = new ArrayList<>(6);
    @Transient
    private long bet = 50;
    @Transient
    private boolean passHand;
    @Transient
    private boolean passSplitHand = true;
    @Transient
    private int isWonTieLose; // 1 for win -1 for lose 0 for tie

    @Builder
    public Player(String email, long money, String password) {
        this.email = email;
        this.username = email;
        this.money = money;
        this.password = password;
    }
    public void hit(Card card) {
        if (playedSplitSoNextCardInHandIsHidden())
            card.setHidden(true);
        hit(hand, card);
    }
    public void hitSplit(Card card) {
        if (playedSplitSoNextCardInSplitHandIsHidden())
            card.setHidden(true);
        hit(splitHand, card);
    }
    public void split() {
        Card c = hand.remove(0);
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
    private boolean playedSplitSoNextCardInHandIsHidden() {
        return getSplitHand().size() == 1;
    }
    private boolean playedSplitSoNextCardInSplitHandIsHidden() {
        return getHand().size() == 1 && !getSplitHand().isEmpty();
    }
    public void flushPlayerGameStats() {
        hand = new ArrayList<>(6);
        splitHand = new ArrayList<>(6);
        bet = 0;
        passHand = false;
        isWonTieLose = 0;
    }
}
