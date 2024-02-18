package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReservationService {

    private final static ReservationService reference = new ReservationService();

    private final Map<String, Reservation> reservations = new HashMap<String, Reservation>();
    private final Map<String, Room> rooms = new HashMap<>();

    public static ReservationService getInstance() {
        return ReservationService.reference;
    }

    public void addRoom(Room room) {
        this.rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getRoom(String roomId) {
        return null;
    }

    public Collection<Room> getAllRooms() {
        return rooms.values();
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

    public Collection<Reservation> getAllReservations() {
        return reservations.values();
    }
}
