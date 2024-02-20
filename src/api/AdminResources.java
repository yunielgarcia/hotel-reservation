package api;

import model.Customer;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;

public class AdminResources {
    private static final AdminResources adminResources = new AdminResources();
    private final ReservationService reservationService = ReservationService.getInstance();
    private final CustomerService customerService = CustomerService.getInstance();

    private AdminResources() {
    }

    public static AdminResources getInstance() {
        return adminResources;
    }

    public boolean addRoom(Room room){
        return reservationService.addRoom(room);
    }

    public Collection<Room> getAllRooms(){
        return reservationService.getAllRooms();
    }
    public Collection<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }
    public Collection<Reservation> displayAllReservations(){
        return reservationService.getAllReservations();
    }
}
