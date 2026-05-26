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
            System.out.println("4. Check customer reservations");
            System.out.println("5. Check-in");
            System.out.println("6. Check-out");
            System.out.println("7. Show all bookings");
            System.out.println("8. Search by customer name");


            System.out.println("9. Save All");
            System.out.println("10. Load All");


            System.out.println("0. Exit");

            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1 -> service.viewRooms();

                case 2 -> service.bookRoom();

                case 3 -> service.cancel();

                case 4 -> service.searchByCustomer();

                case 5 -> service.checkIn();

                case 6 -> service.checkOut();

                case 7 -> service.showAll();

                case 8 -> service.searchByCustomerName();

                case 9 -> service.saveAll();

                case 10 -> service.loadAll();

                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}