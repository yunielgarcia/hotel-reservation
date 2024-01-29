package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AdminResources {
    public Customer getCustomer(String email) {
        return null;
    }

    public void addRoom(List<IRoom> rooms){
    }

    public Collection<IRoom> getAllRooms(){return null;}
    public Collection<Customer> getAllCustomers(){return null;}
    public void displayAllReservations(){}
}
