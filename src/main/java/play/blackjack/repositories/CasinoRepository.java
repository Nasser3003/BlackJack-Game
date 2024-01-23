package play.blackjack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import play.blackjack.models.Casino;

@Repository
public interface CasinoRepository extends JpaRepository<Casino, Long> {
}
