package play.blackjack.service;

import lombok.AllArgsConstructor;

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

    public void registerUser(String email, String password, long money) throws DuplicateKeyException {
        if (!playerRepository.findByEmail(email).isPresent())
            playerRepository.save(new Player(email, money, encoder.encode(password)));
        else
            throw new DuplicateKeyException("Email already taken ERROR in PlayerService Save");
    }
    public void registerUser(Player player) {
        if (!playerRepository.findByEmail(player.getEmail()).isPresent())
            playerRepository.save(player);
        else
            System.out.println(new DuplicateKeyException("Email already taken ERROR in PlayerService Save").getMessage());
    }

    public Player loginUser(Player player) throws InvalidCredentialsException {
        Player existingPlayer = null;
        if (playerRepository.findByEmail(player.getEmail()).isPresent()) {
            existingPlayer = playerRepository.findByEmail(player.getEmail()).get();
            if (existingPlayer.getPassword().equals(encoder.encode(player.getPassword()))) {
                return existingPlayer;
            } 
        }
        throw new InvalidCredentialsException(); 
    }
}
