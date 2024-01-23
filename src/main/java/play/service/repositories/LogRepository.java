package play.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import play.service.models.Logs;

public interface LogRepository extends JpaRepository<Logs, Long> {

}
