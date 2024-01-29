package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReservationService {

    private final static ReservationService reference = new ReservationService();

    private Map<String, Reservation> mapOfReservations = new HashMap<String, Reservation>();

    public static ReservationService getInstance() {
        return ReservationService.reference;
    }

    public void addRoom(IRoom room) {

    }

    public IRoom getRoom(String roomId) {
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return null;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
       return null;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        return null;
    }

    public void printAllReservations() {

    }
}
