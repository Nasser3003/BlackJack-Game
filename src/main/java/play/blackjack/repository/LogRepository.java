package play.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import play.blackjack.model.Logs;

@Repository
public interface LogRepository extends JpaRepository<Logs, Long> {

}
