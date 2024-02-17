package api;

import model.Customer;
import model.IRoom;
import model.Room;
import service.ReservationService;

import java.util.Collection;

public class AdminResources {
    private static final AdminResources adminResources = new AdminResources();
    private final ReservationService reservationService = ReservationService.getInstance();

    private AdminResources() {
    }

    public static AdminResources getInstance() {
        return adminResources;
    }

    public Customer getCustomer(String email) {
        return null;
    }

    public void addRoom(Room room){
        reservationService.addRoom(room);
    }

    public Collection<Room> getAllRooms(){
        return reservationService.getAllRooms();
    }
    public Collection<Customer> getAllCustomers(){return null;}
    public void displayAllReservations(){}
}
