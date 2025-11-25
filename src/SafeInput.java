
// SafeInput.java
import java.util.Scanner;

public class SafeInput {
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int value = 0;
        while (true) {
            System.out.print(prompt + " ");
            String line = console.nextLine().trim();
            try {
                value = Integer.parseInt(line);
                if (value >= low && value <= high) {
                    return value;
                } else {
                    System.out.println("Error: value must be between " + low + " and " + high + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: please enter a valid integer.");
            }
        }
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N) ");
            String resp = console.nextLine().trim().toLowerCase();
            if (resp.equals("y") || resp.equals("yes")) {
                return true;
            } else if (resp.equals("n") || resp.equals("no")) {
                return false;
            } else {
                System.out.println("Please enter Y or N.");
            }
        }
    }
}
