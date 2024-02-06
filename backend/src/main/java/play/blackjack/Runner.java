package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import play.blackjack.model.Casino;
import org.springframework.dao.DuplicateKeyException;
import play.blackjack.controllers.AuthController;
import play.blackjack.exception.InvalidCredentialsException;
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
    private Engine engine;
    private AuthController authController;

    @Override
    public void run(String... args) {
        // play will return if they won or lose
        // if they want to play again we relaunch.

        try (Scanner scanner = new Scanner(System.in);) {
            Thread.sleep(2000);

            clearScreen();

            intro();
            makeDecision(scanner);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void intro() {
        System.out.println("\n\n\n\n\nHello, Welcome to The Bill Club!\n" +
            "This is a Blackjack Game by Nasser, Carlos, and Sam.\n");
    }

    public void makeDecision(Scanner scanner) {
        String response = registerOrLogin(scanner);
        registerOrLoginForms(scanner, response); 
    }

    public String registerOrLogin(Scanner scanner) {        
        System.out.println("a) register\n" + "b) login\n");
        System.out.print("> ");
        String response = scanner.nextLine();
        return response;
    }

    public void registerOrLoginForms(Scanner scanner, String response) {
        if (response.equals("a")) {
            clearScreen();
            System.out.print("Registration Form\n--------------------\nEmail: ");
            String email = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("Starting Cash: ");
            long money = scanner.nextLong();
            scanner.nextLine();
            try {
                authController.register(new Player(email, money, password));
                authController.register(new Player("house@gmail.com", 1000000, "house"));
                Casino casino = new Casino("Casino 1", 20000000);
                casinoRepository.save(casino);
                clearScreen();
                
                System.out.println("You have registered successfully!");
                makeDecision(scanner);
            } catch (DuplicateKeyException e) {
                System.out.println(e.getMessage());
            }
            
        } else if (response.equals("b")) {
            clearScreen();
            System.out.print("Login Form\n--------------------\nEmail: ");
            String loginEmail = scanner.nextLine();
            System.out.print("Password: ");
            String loginPassword = scanner.nextLine();
            
            try {
                Player player = authController.login(new Player(loginEmail, loginPassword));
                Player house = authController.login(new Player("house@gmail.com", "house"));
                
                if (player != null) {
                    engine.addPlayer(house);
                    engine.addPlayer(player);
                    playOrPlayAgain(scanner, player);
                } 
            } catch (InvalidCredentialsException e) {
                System.out.println(e.getMessage());
            } finally {
                clearScreen();
                System.out.println("Please try again.");
                makeDecision(scanner);
            }
        }
    }

    public void playOrPlayAgain(Scanner scanner, Player player) {
        while (true) {
            clearScreen();
            System.out.print("Do you want to play? (Press Enter to continue, any other key to exit): ");
            if (!scanner.nextLine().isEmpty()) {
                System.out.println("Game Over.");
                break;
            }
            engine.start(player);
        }
        System.exit(0);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
