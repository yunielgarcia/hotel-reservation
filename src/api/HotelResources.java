package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

public class HotelResources {
    public Customer getCustomer(String email) {
        return null;
    }

    public void createCustomer(String email, String firstName, String lastName) {}

    public IRoom getRoom(String roomNumber) {
        return null;
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date chekInDate, Date checkOutDate) {
        return null;
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        return null;
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkout) {
        return null;
    }
}
