package hotel.util;

import hotel.data.DataStore;
import hotel.model.Room;
import hotel.model.RoomType;

public class SeedData {

    public static void init() {

        DataStore.rooms.add(new Room(1, "A101", RoomType.STANDARD));
        DataStore.rooms.add(new Room(2, "A102", RoomType.DELUXE));
        DataStore.rooms.add(new Room(3, "B201", RoomType.VIP));

    }
}