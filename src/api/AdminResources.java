package api;

import model.Customer;
import model.IRoom;

import java.util.Collection;
import java.util.List;

public class AdminResources {
    private static final AdminResources adminResources = new AdminResources();

    private AdminResources() {
    }

    public static AdminResources getInstance() {
        return adminResources;
    }

    public Customer getCustomer(String email) {
        return null;
    }

    public void addRoom(List<IRoom> rooms){
    }

    public Collection<IRoom> getAllRooms(){return null;}
    public Collection<Customer> getAllCustomers(){return null;}
    public void displayAllReservations(){}
}
