package play.blackjack.utils;

public class PrintDashes {
    public static void printDashes(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void printDashes() {
        printDashes(50);
    }
}
