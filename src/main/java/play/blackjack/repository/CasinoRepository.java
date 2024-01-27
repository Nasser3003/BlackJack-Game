package play.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import play.blackjack.model.Casino;

@Repository
public interface CasinoRepository extends JpaRepository<Casino, Long> {
}
