package play.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import play.blackjack.model.Casino;

import java.util.Optional;
import java.util.Scanner;

@Repository
public interface CasinoRepository extends JpaRepository<Casino, Long> {
    Optional<Casino> findCasinoByName(String name);
}
