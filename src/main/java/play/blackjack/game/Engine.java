package play.blackjack.game;

import lombok.Data;
import play.blackjack.Player;

import java.util.ArrayList;
import java.util.List;

@Data
public class Engine {
    private List<Player> players = new ArrayList<>();
    public void addPlayer(Player player) {
        players.add(player);
    }
}
