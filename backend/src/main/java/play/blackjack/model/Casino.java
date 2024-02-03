package play.blackjack.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Casino {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private long capital;
    private long revenue;

    public Casino(String name, long capital) {
        this.name = name;
        this.capital = capital;
    }

    @OneToMany(mappedBy = "casino")
    private List<Logs> logs;

    @OneToMany(mappedBy = "casino")
    private List<Player> players;

    public void adjustRevenueAndCapital(long value) {
        revenue += value;
        capital += value;
    }

}
