package hotel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Room implements Serializable {

    private int roomId;
    private String roomNumber;
    private RoomType type;
    private boolean available;

    public Room(int roomId, String roomNumber, RoomType type) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.available = true;
    }

    public double getPrice() {
        return type.getPrice();
    }
}