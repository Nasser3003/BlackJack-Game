package play.blackjack.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid Email and/or Password!");
    }
}


