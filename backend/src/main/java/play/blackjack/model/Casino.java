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

    private int capital;
    private int revenue;

    @OneToMany(mappedBy = "casino")
    List<Logs> logs;
}
