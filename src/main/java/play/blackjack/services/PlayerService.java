package play.blackjack.services;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.blackjack.cards.Card;
import play.blackjack.models.Player;
import play.blackjack.repositories.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Setter
    private Player player;

    public int calculateHand() {
        return player.calculateHandValue();
    }
    public int calculateHandSplit() {
        return player.calculateHandValueSplit();
    }
    public void hit(Card card) {
        player.hit(card);
    }
    public void hitSplit(Card card) {
        player.hitSplit(card);
    }
    public void split() {
        if (player.getHand().get(0).getRankAsInt() != player.getHand().get(1).getRankAsInt())
            System.out.println("Cant split, you dont have a pair");
        else {
            player.split();
        }
    }
    public void stay() {
        player.stay();
    }
    public void save() {
        playerRepository.save(player);
    }

}
