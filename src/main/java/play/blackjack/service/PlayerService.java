package play.blackjack.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import play.blackjack.cards.Card;
import play.blackjack.cards.Deck;
import play.blackjack.model.Player;
import play.blackjack.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
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
        return calculateHandValue(player.getHand());
    }
    public int calculateHandSplit(Player player) {
        return calculateHandValue(player.getSplitHand());
    }
    public void hit(Player player, Deck deck) {
        if (!player.isPassHand()) {
            player.hit(deck.deal());

        }
        else
            System.out.println("You can't request card for your Hand");
    }
    public void hitSplit(Player player, Deck deck) {
        if (!player.isPassSplitHand())
            player.hitSplit(deck.deal());
        else
            System.out.println("You can't request card for your Split Hand");
    }
    public void split(Player player) {
        if (player.getHand().get(0).getRankAsInt() != player.getHand().get(1).getRankAsInt())
            System.out.println("Cant split, you dont have a pair");
        else if (getBet(player) > getMoney(player)) {
        // TODO fix?
        } else {
            player.split();
        }
    }
    public long getBet(Player player) {
        return player.getBet();
    }
    public void stay(Player player) {
        player.stay();
    }
    public void staySplit(Player player) {
        player.staySplit();
    }
    public List<Card> seeHand(Player player) {
        return player.getHand();
    }
    public List<Card> seeSplitHand(Player player) {
        return player.getSplitHand();
    }
    public long getMoney(Player player) {
        return player.getMoney();
    }
    public void adjustMoneyAndEarnings(Player player, long value) {
        player.adjustMoneyAndEarnings(value);
    }
    public boolean setBet(Player player, long amount) {
        if (hasEnoughMoney(player, amount)) {
            player.setBet(amount);
            return true;
        } else {
            System.out.println("You don't have enough money");
            return false;
        }
    }
    private int calculateHandValue(List<Card> hand) {
        int sum = 0;

        List<Card> aces = new ArrayList<>();

        // we added all the none-ace cards
        for (Card c : hand) {
            if (c.getRankAsInt() != 1)
                sum += c.getRankAsInt();
            else
                aces.add(c);
        }

        if (aces.isEmpty()) return sum;

        // if there is ace do the logic
        if ((sum + 11) > 21)
            for (Card ace : aces)
                sum += ace.getRankAsInt();

        return sum;
    }
    private boolean hasEnoughMoney(Player player, long amount) {
        return amount < player.getMoney();
    }
    private void addMoney(Player player, long amount) {
        player.setMoney(amount + getMoney(player));
    }
    public void flushPlayerGameStats(Player player) {
        player.flushPlayerGameStats();
    }
}
// TODO fix error handling in Save
