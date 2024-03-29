package model;

import java.util.Objects;

public class Room  implements IRoom{
    private final String roomNumber;
    private final Double roomPrice;
    private final RoomType roomType;

    public Room(String roomNumber, Double roomPrice, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
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
        return this.roomPrice == 0;
    }

    @Override
    public String toString() {
        return "Room #" + this.roomNumber + ", type " + this.roomType + " at $" + this.roomPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            return Objects.equals(this.roomNumber, ((Room) obj).roomNumber);
        }
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.getRoomNumber());
    }
}
