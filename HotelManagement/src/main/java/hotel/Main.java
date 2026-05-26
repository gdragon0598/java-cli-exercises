package hotel;

import hotel.service.HotelService;
import hotel.util.SeedData;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HotelService service = new HotelService();

        SeedData.init();

        while (true) {

            System.out.println("\n===== HOTEL SYSTEM =====");

            System.out.println("1. View available rooms");
            System.out.println("2. Book room");
            System.out.println("3. Cancel reservation");
            System.out.println("4. Check-in");
            System.out.println("5. Check-out");
            System.out.println("6. Show all bookings");
            System.out.println("7. Search by customer name");
            System.out.println("8. Save All");
            System.out.println("9. Load All");
            System.out.println("0. Exit");

            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1 -> service.viewRooms();

                case 2 -> service.bookRoom();

                case 3 -> service.cancel();

                case 4 -> service.checkIn();

                case 5 -> service.checkOut();

                case 6 -> service.showAll();

                case 7 -> service.searchByCustomerName();

                case 8 -> service.saveAll();

                case 9 -> service.loadAll();

                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}