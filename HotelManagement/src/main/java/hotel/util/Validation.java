package hotel.util;

import java.time.LocalDate;

public class Validation {

    public static boolean isValidName(String name) {
        return name != null
                && !name.isBlank()
                && name.matches("[a-zA-ZÀ-Ỹà-ỹ\\s]+");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null
                && phone.matches("\\d{10}");
    }

    public static boolean isPositiveNumber(int number) {
        return number > 0;
    }

    public static boolean isValidCheckIn(LocalDate checkIn) {
        return !checkIn.isBefore(LocalDate.now());
    }

    public static boolean isValidCheckOut(
            LocalDate checkIn,
            LocalDate checkOut) {

        return checkOut.isAfter(checkIn);
    }
}