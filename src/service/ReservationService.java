package service;

import model.Customer;
import model.Reservation;
import model.Room;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

    private final static ReservationService reference = new ReservationService();

    private final Map<String, Collection<Reservation>> reservations = new HashMap<>();
    private final Map<String, Room> rooms = new HashMap<>();

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

        // analyze existing reservations
        for (Reservation reservation : reservations) {
            if (checkConflict(reservation, checkInDate, checkOutDate)) {
                notAvailableRooms.add(reservation.getRoom());
            }
        }

        // return all room except the conflicting ones
        return rooms.values().stream().filter(room ->
                        notAvailableRooms.stream().noneMatch(conflictinRoom ->
                                conflictinRoom.equals(room)))
                .collect(Collectors.toList());
    }

    public Collection<Reservation> getCustomersReservation(String email) {
        return reservations.get(email);
    }

    public Collection<Reservation> getAllReservations() {
        List<Reservation> allReservations = new ArrayList<>();

        for (Collection<Reservation> customerReservations : reservations.values()) {
            allReservations.addAll(customerReservations);
        }

        return allReservations;
    }

    private boolean checkConflict(Reservation reservation, Date inDate, Date outDate) {
        return
                inDate.before(reservation.getOutDate()) && outDate.after(reservation.getOutDate()) ||
                        inDate.before(reservation.getInDate()) && outDate.before((reservation.getOutDate())) ||
                        inDate.before(reservation.getInDate()) && outDate.after(reservation.getOutDate()) ||
                        inDate.after(reservation.getInDate()) && outDate.before(reservation.getOutDate());
    }
}
