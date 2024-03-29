package play.blackjack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlackJackGame {
     public static void main(String[] args) {
        SpringApplication.run(BlackJackGame.class, args);
    }
}
// TODO master the Cascade, and relationships.