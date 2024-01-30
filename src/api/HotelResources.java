package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;

import java.util.Collection;
import java.util.Date;

public class HotelResources {

    private static final HotelResources reference = new HotelResources();

    CustomerService customerService = CustomerService.getInstance();

    private HotelResources() {}

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

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

    public static HotelResources getInstance() {
        return reference;
    }
}
