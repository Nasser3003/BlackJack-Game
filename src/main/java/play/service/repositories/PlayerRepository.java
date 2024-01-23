package play.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import play.service.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
