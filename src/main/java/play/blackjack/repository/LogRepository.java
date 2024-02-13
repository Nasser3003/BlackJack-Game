package play.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import play.blackjack.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
