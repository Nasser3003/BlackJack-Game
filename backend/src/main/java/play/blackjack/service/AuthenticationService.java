package play.blackjack.service;


import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import play.blackjack.exception.InvalidCredentialsException;
import play.blackjack.model.Player;
import play.blackjack.repository.PlayerRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class AuthenticationService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder encoder;

    public void registerUser(String email, String password, long money) {
        if (!playerRepository.findByEmail(email).isPresent())
            playerRepository.save(new Player(email, money, encoder.encode(password)));
        else
            System.out.println(new DuplicateKeyException("Email already taken ERROR in PlayerService Save").getMessage());
    }
    public void registerUser(Player player) {
        if (!playerRepository.findByEmail(player.getEmail()).isPresent())
            playerRepository.save(player);
        else
            System.out.println(new DuplicateKeyException("Email already taken ERROR in PlayerService Save").getMessage());
    }

    public Player loginUser(String email, String password) {
        Player player = null;
        if (playerRepository.findByEmail(email).isPresent()) {
            player = playerRepository.findByEmail(email).get();
            if (player.getPassword().equals(encoder.encode(password))) {
                return player;
            } else {
                System.out.println(new InvalidCredentialsException().getMessage());
            }
        }
        return null; 
    }
}
