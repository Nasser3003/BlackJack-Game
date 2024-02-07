package play.blackjack.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private Long id;

    private long playerMoneyAdjustment;
    private long casinoRevenueAdjustment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "casino_id")
    private Casino casino;

    public Logs(Player player, Casino casino) {
        this.player = player;
        this.casino = casino;
    }

    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime date;

}
