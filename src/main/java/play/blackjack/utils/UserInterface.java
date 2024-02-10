package play.blackjack.utils;

public class UserInterface {
    public static void printDashes(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void printDashes() {
        printDashes(43);
    }

    // using cmd and terminal logic doesn't work on ALL OS.
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  


}
