package play.blackjack;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import play.blackjack.engine.Engine;

@Component
public class AppStarter {

    private final Engine engine;

    public AppStarter(Engine engine) {
        this.engine = engine;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startGame() {
        engine.start();
    }
}
