package hotel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
public class Reservation implements Serializable {

    private int reservationId;
    private Customer customer;
    private Room room;

    private LocalDate checkIn;
    private LocalDate checkOut;

    private boolean checkedIn;
    private boolean checkedOut;

    public Reservation(int reservationId,
                       Customer customer,
                       Room room,
                       LocalDate checkIn,
                       LocalDate checkOut) {

        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;

        this.checkIn = checkIn;
        this.checkOut = checkOut;

        this.checkedIn = false;
        this.checkedOut = false;
    }

    public void checkIn() {

        if (!checkedIn) {
            checkedIn = true;
        }
    }

    public void checkOut() {

        if (!checkedOut) {
            checkedOut = true;
            room.setAvailable(true);
        }
    }

    public double calculatePrice() {

        long days =
                ChronoUnit.DAYS.between(checkIn, checkOut);

        return days * room.getPrice();
    }
}