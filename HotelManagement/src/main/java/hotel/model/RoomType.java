package hotel.model;

public enum RoomType {
    STANDARD(500),
    DELUXE(800),
    VIP(1200);

    private final double price;

    RoomType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
