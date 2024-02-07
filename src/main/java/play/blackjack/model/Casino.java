package play.blackjack.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Casino {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private long id;

    private String name;
    private long capital;
    private long revenue;

    public Casino(String name, long capital) {
        this.name = name;
        this.capital = capital;
    }

    @OneToMany(mappedBy = "casino", cascade = CascadeType.ALL)
    private List<Logs> logs;

    @OneToMany(mappedBy = "casino", cascade = CascadeType.ALL)
    private List<Player> players;

    public void adjustRevenueAndCapital(long value) {
        revenue += value;
        capital += value;
    }

}
