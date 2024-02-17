package model;

public class FreeRoom extends Room{
    //todo: price 0 relation with isFree
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    @Override
    public String toString() {
        return this.getRoomNumber() + " free room";
    }
}
