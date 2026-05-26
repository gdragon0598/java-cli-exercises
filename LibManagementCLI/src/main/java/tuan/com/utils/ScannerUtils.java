package tuan.com.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ScannerUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String enterString (String title) {
        System.out.print(title);
        String input = scanner.nextLine().trim();
        String simpleInput = input.toLowerCase();
        if (simpleInput.contains("exit") || simpleInput.contains("quit"))
            throw new RuntimeException("Quit process!");
        return input;
    }

    public static Integer enterInteger (String title) {
        while (true) {
            try {
                String input = enterString(title);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Enter Integer: " + e.getMessage());
            }
        }
    }

    public static LocalDate enterLocalDate (String message) {
        while (true) {
            try {
                return LocalDate.parse(enterString(message));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format date (YYYY-MM-DD)");
            }
        }
    }
}
