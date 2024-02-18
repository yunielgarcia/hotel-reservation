package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResources {

    private static final HotelResources reference = new HotelResources();

    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    private HotelResources() {}

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public Room getRoom(String roomNumber) {
        return reservationService.getRoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, Room room, Date chekInDate, Date checkOutDate) {
        return reservationService.reserveARoom(
                customerService.getCustomer(customerEmail),
                room,
                chekInDate,
                checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        return reservationService.getCustomersReservation(customerEmail);
    }

    public Collection<Room> findARoom(Date checkIn, Date checkout) {
        return reservationService.findRooms(checkIn, checkout);
    }

    public static HotelResources getInstance() {
        return reference;
    }
}
