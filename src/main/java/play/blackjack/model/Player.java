package play.blackjack.model;

import lombok.*;
import play.blackjack.cards.Card;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    private long money;
    private long earnings;
    private String password;

    // If not eager, you get this error
    /*
    Unable to evaluate the children renderer expression Method threw 'org.hibernate.LazyInitializationException' exception.
    The error you're encountering, org.hibernate.LazyInitializationException, typically occurs when Hibernate attempts
    to lazily initialize a collection or entity outside an active session.
     This commonly happens when you're trying to access a lazily loaded property of an entity after the session has been closed.
     */
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "player_log_junction",
//            joinColumns = {@JoinColumn(name = "player_id")},
//            inverseJoinColumns = {@JoinColumn(name = "log_id")}
//    )
    private List<Log> logs = new ArrayList<>();

    @Transient
    private List<Card> hand = new ArrayList<>(6);
    @Transient
    private List<Card> splitHand = new ArrayList<>(6);
    @Transient
    private long bet;
    @Transient
    private boolean passHand;
    @Transient
    private boolean passSplitHand = true;
    @Transient
    private int isWonTieLose; // 1 for win -1 for losing 0 for tie

    @Builder
    public Player(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Player(String email, String password, long money) {
        this.email = email;
        this.password = password;
        this.money = money;
    }
    public void hit(Card card) {
        hit(hand, card);
    }
    public void hitSplit(Card card) {
        hit(splitHand, card);
    }
    public void split() {
        splitHand.add(hand.remove(0));
        money -= bet;
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
        earnings += value / 2;
    }
    private void hit(List<Card> hand, Card card) {
        hand.add(card);
    }
    public void flushPlayerGameStats() {
        hand = new ArrayList<>(6);
        splitHand = new ArrayList<>(6);
        bet = 0;
        passHand = false;
        isWonTieLose = 0;
    }
}
