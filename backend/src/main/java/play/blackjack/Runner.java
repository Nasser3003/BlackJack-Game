package play.blackjack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
    private AuthController authController;

    @Override
    public void run(String... args) {
        // play will return if they won or lose
        // if they want to play again we relaunch.

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clearScreen();

        intro();
        makeDecision();

    }

    public void intro() {
        System.out.println("\n\n\n\n\nHello, Welcome to The Bill Club!\n" +
            "This is a Blackjack Game by Nasser, Carlos, and Sam.\n");
    }

    public void makeDecision() {
        String response = registerOrLogin();
        registerOrLoginForms(response); 
    }

    public String registerOrLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("a) register\n" + "b) login\n");
        System.out.print("> ");
        String response = scanner.nextLine();
        scanner.close();
        return response;
    }

    public void registerOrLoginForms(String response) {
        Scanner scanner = new Scanner(System.in);
        if (response.equals("a")) {
            System.out.print("Registration Form\nEmail: ");
            String email = scanner.nextLine();
            System.out.print("\nPassword: ");
            String password = scanner.nextLine();
            System.out.print("Starting Cash: ");
            long money = scanner.nextLong();
            scanner.nextLine();
            try {
                authController.register(new Player(email, money, password));
                System.out.println("You have registered successfully!");
                makeDecision();
            } catch (DuplicateKeyException e) {
                System.out.println(e.getMessage());
            }
            
        } else if (response.equals("b")) {
            System.out.print("Email: ");
            String loginEmail = scanner.nextLine();
            System.out.print("Password: ");
            String loginPassword = scanner.nextLine();
            
            try {
                Player player = authController.login(new Player(loginEmail, loginPassword));
                Player house = authController.login(new Player("house@gmail.com", "house"));                if (player != null) {

                Engine engine = new Engine();
                engine.addPlayer(house);
                engine.addPlayer(player);
                engine.play();
                } 
            } catch (InvalidCredentialsException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
