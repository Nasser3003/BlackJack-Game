package play.blackjack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import play.blackjack.models.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
