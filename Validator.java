package Java_CompFinal.copy;
import java.util.Scanner;

public class Validator {
    private static Scanner sc = new Scanner(System.in);

    public static String getString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.next();
        sc.nextLine();
        return s;
    }

    public static int getRange(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value < min || value > max) {
                    sc.nextLine();
                    System.out.println("Please enter a number between 1 and 5. Try again.\n");
                } else {
                    sc.nextLine();
                    return value;
                }
            } else {
                sc.nextLine();
                System.out.println("Please enter a number between 1 and 5. Try again.\n");
            }

        }
    }
}