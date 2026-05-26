package hotel.service;

import hotel.data.DataStore;
import hotel.model.*;
import hotel.util.Validation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class HotelService {

    Scanner sc = new Scanner(System.in);

    // 1. VIEW ROOMS
    public void viewRooms() {
        for (Room r : DataStore.rooms) {
            if (r.isAvailable()) {
                System.out.println(r.getRoomId() + " - " + r.getRoomNumber() + " - " + r.getType());
            }
        }
    }

    // 2. BOOK ROOM
    public void bookRoom() {

        System.out.print("Customer name: ");
        String name = sc.nextLine();
        while (true) {


            if (Validation.isValidName(name)) {
                break;
            }
            System.out.println("Invalid customer name!");
        }

        System.out.print("Phone: ");
        String phone = sc.nextLine();
        while (true) {

            if (Validation.isValidPhone(phone)) {
                break;
            }
            System.out.println("Phone must contain 10 digits!");
        }

        Customer customer = new Customer(
                DataStore.customers.size() + 1,
                name,
                phone
        );

        Room room;

        while (true) {

            try {

                System.out.print("Room ID: ");
                int roomId = Integer.parseInt(sc.nextLine());

                room = findRoom(roomId);

                if (room == null) {
                    System.out.println("Room not found! Please try again.");
                    continue;
                }

                if (!room.isAvailable()) {
                    System.out.println("Room is not available! Please choose another room.");
                    continue;
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("Room ID must be a number!");
            }

        }

        LocalDate checkIn;

        while (true) {

            try {

                System.out.print("Check-in date (yyyy-MM-dd): ");
                checkIn = LocalDate.parse(sc.nextLine());

                if (checkIn.isBefore(LocalDate.now())) {
                    System.out.println("Check-in date cannot be in the past!");
                    continue;
                }

                break;

            } catch (Exception e) {
                System.out.println("Invalid date format! Use yyyy-MM-dd");
            }

        }
        LocalDate checkOut;

        while (true) {

            try {

                System.out.print("Check-out date (yyyy-MM-dd): ");
                checkOut = LocalDate.parse(sc.nextLine());

                if (!checkOut.isAfter(checkIn)) {
                    System.out.println("Check-out date must be after check-in date!");
                    continue;
                }

                break;

            } catch (Exception e) {
                System.out.println("Invalid date format! Use yyyy-MM-dd");
            }

        }

        int reservationId = 1;

        for (Reservation r : DataStore.reservations) {
            if (r.getReservationId() >= reservationId) {
                reservationId = r.getReservationId() + 1;
            }
        }

        Reservation reservation = new Reservation(
                reservationId,
                customer,
                room,
                checkIn,
                checkOut
        );

        DataStore.customers.add(customer);

        room.setAvailable(false);

        DataStore.reservations.add(reservation);

        System.out.println("\n===== BOOKING SUCCESS =====");
        System.out.println("Reservation ID: " + reservation.getReservationId());
        System.out.println("Customer: " + customer.getCustomerName());
        System.out.println("Phone: " + phone);
        System.out.println("Room: " + room.getRoomNumber());
        System.out.println("Room Type: " + room.getType());
        System.out.println("Check-in: " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("Total Price: " + reservation.calculatePrice());
    }

    // 3. CANCEL
    public void cancel() {
        System.out.print("Reservation ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Reservation target = null;

        for (Reservation r : DataStore.reservations) {
            if (r.getReservationId() == id) {
                target = r;
                break;
            }
        }

        if (target != null) {
            target.getRoom().setAvailable(true);
            DataStore.reservations.remove(target);
            System.out.println("Cancelled!");
        }
    }

    // 4. SEARCH CUSTOMER (OPTIONAL FEATURE)
    public void searchByCustomer() {

        System.out.print("Customer name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Reservation r : DataStore.reservations) {

            if (r.getCustomer()
                    .getCustomerName()
                    .equalsIgnoreCase(name)) {

                System.out.println(
                        "Reservation ID: " + r.getReservationId()
                                + " | Room: " + r.getRoom().getRoomNumber()
                                + " | Type: " + r.getRoom().getType()
                                + " | Price: " + r.calculatePrice()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No reservation found!");
        }
    }

    // 5. CHECK IN
    public void checkIn() {

        try {

            System.out.print("Reservation ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Reservation reservation = findReservation(id);

            if (reservation == null) {
                System.out.println("Reservation not found!");
                return;
            }

            reservation.checkIn();

            System.out.println("Checked in successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Reservation ID must be a number!");
        }
    }

    // 6. CHECK OUT
    public void checkOut() {

        try {

            System.out.print("Reservation ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Reservation reservation = findReservation(id);

            if (reservation == null) {
                System.out.println("Reservation not found!");
                return;
            }

            reservation.checkOut();

            System.out.println("Checked out successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Reservation ID must be a number!");
        }
    }

    // 7. SHOW ALL
    public void showAll() {

        if (DataStore.reservations.isEmpty()) {
            System.out.println("No reservations found!");
            return;
        }

        for (Reservation r : DataStore.reservations) {

            System.out.println(
                    "Reservation ID: " + r.getReservationId()
                            + " | Customer ID: " + r.getCustomer().getCustomerId()
                            + " | Customer: " + r.getCustomer().getCustomerName()
                            + " | Room: " + r.getRoom().getRoomNumber()
                            + " | Check-in: " + r.getCheckIn()
                            + " | Check-out: " + r.getCheckOut()
                            + " | Price: " + r.calculatePrice()
            );
        }
    }


    //search by customer name
    public void searchByCustomerName() {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Reservation r : DataStore.reservations) {
            if (r.getCustomer().getCustomerName().equalsIgnoreCase(name)) {

                System.out.println(
                        "Reservation ID: " + r.getReservationId() +
                                " | Room: " + r.getRoom().getRoomNumber() +
                                " | Type: " + r.getRoom().getType() +
                                " | Price: " + r.calculatePrice()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No reservation found for this customer!");
        }
    }

    //Calculate price
    public void calculatePrice() {
        System.out.print("Reservation ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Reservation r = findReservation(id);

        if (r != null) {
            System.out.println("Total price: " + r.calculatePrice());
        } else {
            System.out.println("Reservation not found!");
        }
    }

    //Save Room
    public void saveRooms() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("rooms.dat"))) {

            out.writeObject(DataStore.rooms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Save customer
    public void saveCustomers() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("customers.dat"))) {

            out.writeObject(DataStore.customers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //save Reservationes
    public void saveReservations() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("reservations.dat"))) {

            out.writeObject(DataStore.reservations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveAll() {
        saveRooms();
        saveCustomers();
        saveReservations();
        System.out.println("All data saved successfully!");
    }

    //Load room
    @SuppressWarnings("unchecked")
    public void loadRooms() {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("rooms.dat"))) {

            DataStore.rooms = (List<Room>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //load customer
    @SuppressWarnings("unchecked")
    public void loadCustomers() {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("customers.dat"))) {

            DataStore.customers = (List<Customer>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //load reservation
    @SuppressWarnings("unchecked")
    public void loadReservations() {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("reservations.dat"))) {

            DataStore.reservations =
                    (List<Reservation>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadAll() {
        loadRooms();
        loadCustomers();
        loadReservations();
        System.out.println("All data loaded successfully!");
    }

    //Find room
    public Room findRoom(int id) {
        for (Room r : DataStore.rooms) {
            if (r.getRoomId()== id) return r;
        }
        return null;
    }

    public Reservation findReservation(int id) {
        for (Reservation r : DataStore.reservations) {
            if (r.getReservationId() == id) return r;
        }
        return null;
    }
}
