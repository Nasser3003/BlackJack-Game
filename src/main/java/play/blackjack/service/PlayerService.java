package play.blackjack.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.blackjack.cards.Deck;
import play.blackjack.model.Player;
import play.blackjack.repository.PlayerRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerService {
    private PlayerRepository playerRepository;

    public Player getPlayerByEmail(String email) {
        Optional<Player> playerOptional = playerRepository.findByEmail(email);
        if (playerOptional.isPresent())
            return playerOptional.get();
        throw new NoSuchElementException("Player not found with email: " + email);
    }
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
        StringBuilder a = new StringBuilder();
    }
}
// fix error handling in Save
