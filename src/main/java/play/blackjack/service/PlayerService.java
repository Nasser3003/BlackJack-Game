package play.blackjack.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.blackjack.cards.Card;
import play.blackjack.cards.Deck;
import play.blackjack.cards.ENUMS.ECardRank;
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
        Optional<Player> playerOptional = playerRepository.findByEmailIgnoreCase(email);
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
        if (player.getHand().get(0).getRank() != player.getHand().get(1).getRank())
            System.out.println("Cant split, you dont have a pair");
        else if (getBet(player) > getMoney(player)) {
            System.out.println("Cant split, you dont have the bet amount");
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
    public List<Card> getHand(Player player) {
        return player.getHand();
    }
    public List<Card> getSplitHand(Player player) {
        return player.getSplitHand();
    }
    public long getMoney(Player player) {
        return player.getMoney();
    }
    public void adjustMoneyAndEarnings(Player player, int playerWisLoses, long bet) {

        if (playerWisLoses > 0)
            player.adjustMoneyAndEarnings(bet * (playerWisLoses * 2L));
        else if (playerWisLoses < 0)
            player.setEarnings(player.getEarnings() + (bet * playerWisLoses));
        else
            if (!player.getSplitHand().isEmpty())
                player.setMoney(player.getMoney() + (bet * 2L));
            else
                player.setMoney(player.getMoney() + bet);
    }

    public boolean setBet(Player player, long amount) {
        if (hasEnoughMoney(player, amount)) {
            player.setBet(amount);
            player.setMoney(player.getMoney() - amount);
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
        for (Card ace : aces) {
            if ((sum + 11) <= 21)
                sum += ace.getRankAsInt() + 10;
            else
                sum += ace.getRankAsInt();
        }
        return sum;
    }
    private boolean hasEnoughMoney(Player player, long amount) {
        return amount <= player.getMoney();
    }
    void addMoney(Player player, long amount) {
        player.setMoney(amount + getMoney(player));
    }
    public void flushPlayerGameStats(Player player) {
        player.flushPlayerGameStats();
    }
}
