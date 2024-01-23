package play.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import play.service.models.Casino;

public interface CasinoRepository extends JpaRepository<Casino, Long> {
}
