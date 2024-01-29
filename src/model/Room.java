package model;

public class Room  implements IRoom{
    private final String roomNumber;
    private final Double roomPrice;
    private final RoomType roomType;
    private final boolean isFree;

    public Room(String roomNumber, Double roomPrice, RoomType roomType, boolean isFree) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.isFree = isFree;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return this.roomType;
    }

    @Override
    public boolean isFree() {
        return this.isFree;
    }

    @Override
    public String toString() {
        return this.roomNumber;
    }
}
