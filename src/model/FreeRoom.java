package model;

public class FreeRoom extends Room{
    //todo: price 0 relation with isFree
    public FreeRoom(String roomNumber, RoomType roomType, boolean isFree) {
        super(roomNumber, 0.0, roomType, isFree);
    }

    @Override
    public String toString() {
        return this.getRoomNumber() + " free room";
    }
}
