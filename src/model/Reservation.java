package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    Room room;
    Date checkInDate;
    Date checkOutDate;

    public Reservation(Customer customer, Room room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Client: " + this.customer.getEmail()
                + " Room# " + this.room.getRoomNumber() +
                " from " + this.checkInDate +
                " to " + this.checkOutDate;
    }

    public Room getRoom() {
        return this.room;
    }

    public Date getInDate() {
        return this.checkInDate;
    }

    public Date getOutDate() {
        return this.checkOutDate;
    }
}
