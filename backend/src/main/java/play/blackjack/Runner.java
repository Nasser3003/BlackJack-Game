package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import play.blackjack.controllers.AuthController;
import play.blackjack.model.Player;
import play.blackjack.repository.CasinoRepository;
import play.blackjack.repository.LogRepository;
import play.blackjack.service.AuthenticationService;
import play.blackjack.service.PlayerService;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class Runner implements CommandLineRunner {
    private CasinoRepository casinoRepository;
    private PlayerService playerService;
    private LogRepository logRepository;
    private PasswordEncoder encoder;
    private AuthenticationService authenticationService;
    private AuthController authController;

    @Override
    public void run(String... args) {
        Player player = new Player("nasser@gmail.com", 1000, encoder.encode("user"));
        Player house = new Player("house@gmail.com", 1000000, encoder.encode("house"));
        authenticationService.registerUser(player);
        authenticationService.registerUser(house);

        Engine engine = new Engine();
        engine.addPlayer(player);
        engine.addPlayer(house);

        // play will return if they won or lose
        // if they want to play again we relaunch.

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clearScreen();

        Scanner scanner = new Scanner(System.in);

        
        System.out.println("\n\n\n\n\nHello, Welcome to The Bill Club!\n" +
            "This is a Blackjack Game by Nasser, Carlos, and Sam.\n" +
            "a) New User: register\n" +
            "b) Returning User: login\n" + 
            "> ");
            clearScreen();
        String response = scanner.nextLine();
        if (response.equals("a")) {
            System.out.print("Registration Form\nEmail: ");
            String email = scanner.nextLine();
            System.out.print("\nPassword: ");
            String password = scanner.nextLine();
            System.out.print("Starting Cash: ");
            long money = scanner.nextLong();
            scanner.nextLine();
            authController.register(new Player(email, money, password));
        } else if (response.equals("b")) {
            System.out.print("Email: ");
            String lEmail = scanner.nextLine();
            System.out.print("Password: ");
            String lPassword = scanner.nextLine();
            authenticationService.loginUser(lEmail, lPassword);
        }
        scanner.close();

        engine.play();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
