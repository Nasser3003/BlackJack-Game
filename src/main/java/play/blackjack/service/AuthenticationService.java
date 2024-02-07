package play.blackjack.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import play.blackjack.model.Player;
import play.blackjack.repository.PlayerRepository;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder encoder;

    public void registerUser(String email, String password, long money) {
        if (!playerRepository.findByEmail(email).isPresent())
            playerRepository.save(new Player(email, money, encoder.encode(password)));
        else
            System.out.println(new DuplicateKeyException("Email already taken ERROR in PlayerService Save").getMessage());
    }
    @Transactional
    public void registerUser(Player player) {
        if (!playerRepository.findByEmail(player.getEmail()).isPresent())
            playerRepository.save(player);
        else
            System.out.println(new DuplicateKeyException("Email already taken ERROR in PlayerService Save").getMessage());
    }
    @Transactional
    public Player login(String email, String password) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("Email cannot be null or empty.");

        Optional<Player> optionalPlayer = playerRepository.findByEmail(email);

        if (!optionalPlayer.isPresent())
            throw new IllegalArgumentException("Incorrect Email.");
        Player user = optionalPlayer.get();

        if (!encoder.matches(password, user.getPassword()))
            throw new IllegalArgumentException("Wrong Password.");

        System.out.println("You have successfully logged in.");
        return user;
    }

}
