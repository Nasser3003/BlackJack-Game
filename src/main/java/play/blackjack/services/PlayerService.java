package play.blackjack.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.blackjack.cards.Deck;
import play.blackjack.models.Player;
import play.blackjack.repositories.PlayerRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerService {
    private PlayerRepository playerRepository;

    public int calculateHand(Player player) {
        return player.calculateHandValue();
    }
    public int calculateHandSplit(Player player) {
        return player.calculateHandValueSplit();
    }
    public void hit(Player player, Deck deck) {
        player.hit(deck.deal());
    }
    public void hitSplit(Player player, Deck deck) {
        player.hitSplit(deck.deal());
    }
    public void split(Player player) {
        if (player.getHand().get(0).getRankAsInt() != player.getHand().get(1).getRankAsInt())
            System.out.println("Cant split, you dont have a pair");
        else {
            player.split();
        }
    }
    public void stay(Player player) {
        player.stay();
    }
    public void save(Player player) {
        playerRepository.save(player);
    }

}
