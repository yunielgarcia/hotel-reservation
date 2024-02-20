package service;

import model.Customer;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {

    private final static ReservationService reference = new ReservationService();
    private final Map<String, Collection<Reservation>> reservations = new HashMap<>();
    private final Map<String, Room> rooms = new HashMap<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        return ReservationService.reference;
    }

    public void addRoom(Room room) {
        this.rooms.put(room.getRoomNumber(), room);
    }

    public Room getRoom(String roomId) {
        return rooms.getOrDefault(roomId, null);
    }

    public Collection<Room> getAllRooms() {
        return rooms.values();
    }

    public Reservation reserveARoom(Customer customer, Room room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);

        Collection<Reservation> customerReservations = reservations.getOrDefault(customer.getEmail(), new ArrayList<>());

        customerReservations.add(reservation);

        if (!reservations.containsKey(customer.getEmail())) {
            reservations.put(customer.getEmail(), customerReservations);
        }

        return reservation;
    }

    public Collection<Room> findRooms(Date checkInDate, Date checkOutDate) {
        final Collection<Reservation> reservations = getAllReservations();
        final Set<Room> notAvailableRooms = new HashSet<>();
        final Set<Room> availableRooms = new HashSet<>();

        // analyze existing reservations
        // 4 combinations for overlapping
        // plus 2 more making sure dateIn not equal to reservation's In and Out and same for dayOut
        for (Reservation reservation : reservations) {
            if (
                    checkInDate.before(reservation.getOutDate()) && checkOutDate.after(reservation.getOutDate()) ||
                            checkInDate.before(reservation.getInDate()) && checkOutDate.before((reservation.getOutDate())) ||
                            checkInDate.before(reservation.getInDate()) && checkOutDate.after(reservation.getOutDate()) ||
                            checkInDate.after(reservation.getInDate()) && checkOutDate.before(reservation.getOutDate()) ||
                            (checkInDate.equals(reservation.getInDate()) || checkInDate.equals(reservation.getOutDate())) ||
                            (checkOutDate.equals(reservation.getInDate()) || checkOutDate.equals(reservation.getOutDate()))
            ) {
                notAvailableRooms.add(reservation.getRoom());
            }
        }

        // return all room except the conflicting ones
        for (Room r : rooms.values()) {
            if (!notAvailableRooms.contains(r)) {
                availableRooms.add(r);
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getCustomersReservation(String email) {
        return reservations.getOrDefault(email, null);
    }

    public Collection<Reservation> getAllReservations() {
        List<Reservation> allReservations = new ArrayList<>();

        for (Collection<Reservation> customerReservations : reservations.values()) {
            allReservations.addAll(customerReservations);
        }

        return allReservations;
    }

}
