package play.blackjack.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Casino {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private long id;

    @NonNull
    private String name;
    @NonNull
    private long capital;

    private long revenue;

    @OneToMany(mappedBy = "casino")
    private List<Log> logs;

    public void adjustRevenueAndCapital(long value) {
        revenue += value;
        capital += value;
    }

}
