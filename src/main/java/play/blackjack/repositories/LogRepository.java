package play.blackjack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import play.blackjack.models.Logs;

@Repository
public interface LogRepository extends JpaRepository<Logs, Long> {

}
