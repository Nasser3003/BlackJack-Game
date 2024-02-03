package play.blackjack.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private long playerCapitalAdjustment;
    private long casinoCapitalAdjustment;

    @ManyToOne
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
