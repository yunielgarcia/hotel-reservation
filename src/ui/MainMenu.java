package ui;

import api.HotelResources;
import model.Customer;
import model.Reservation;
import model.Room;
import utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

import static ui.AdminMenu.adminMenu;

public class MainMenu {

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        printMainOptions();
        String selection = scanner.nextLine();

        switch (selection) {
            case "1" -> findAndReserveARoom();
            case "2" -> seeMyReservations();
            case "3" -> createAccount();
            case "4" -> adminMenu();
            case "5" -> System.out.println("Thanks for using our app.");
            default -> {
                System.out.println("Please enter a valid option number");
                start();
            }
        }
    }

    private static final HotelResources hotelResources = HotelResources.getInstance();

    private static void seeMyReservations() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Email...");
        final String clientEmail = scanner.nextLine();

        Collection<Reservation> clientReservations = hotelResources.getCustomerReservations(clientEmail);

        Utils.printCollection(clientReservations);

        start();
    }

    private static void findAndReserveARoom() {
        System.out.println("Enter Check-In Date MM-dd-yy example 02/25/24");
        Date checkIn = getAndValidateDate();

        System.out.println("Enter Check-Out Date MM-dd-yy example 02/25/24");
        Date checkOut = getAndValidateDate();

        Collection<Room> availableRooms = hotelResources.findARoom(checkIn, checkOut);

        Utils.printCollection(availableRooms);

        if (availableRooms == null || availableRooms.isEmpty()) {
            start();
        } else {
            validateDecisionToReserve(availableRooms, checkIn, checkOut);
        }

    }

    private static Date getAndValidateDate() {
        final Scanner scanner = new Scanner(System.in);

        try {
            return new SimpleDateFormat("MM-dd-yy").parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Error: Invalid date.");
            return getAndValidateDate();
        }
    }

    public static void printMainOptions() {
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println("---------------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("---------------------------------------------------");
        System.out.println("Please select a number for the menu option");

    }

    private static void createAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your first name");
        String first = scanner.nextLine();

        System.out.println("Enter your Last name");
        String last = scanner.nextLine();

        System.out.println("Enter your email");
        String email = scanner.nextLine();

        if (email.isBlank() || first.isBlank() || last.isBlank()) {
            System.out.println("Inputs should not be empty.Please try again");
            createAccount();
        } else {
            try {
                hotelResources.createCustomer(email, first, last);
                System.out.println("Customer added!");
                start();
            } catch (Exception exception) {
                System.out.println("Invalid Input data for adding a customer");
                createAccount();
            }
        }

    }

    private static void validateDecisionToReserve(Collection<Room> availableRooms, Date checkInDate, Date checkOutDate) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to book? y/n");
        final String decision = scanner.nextLine();

        if (decision.equals("y")) {
            validateIfHasAccount(availableRooms, checkInDate, checkOutDate);
        } else if (decision.equals("n")) {
            start();
        } else {
            System.out.println("Wrong answer please start again");
            validateDecisionToReserve(availableRooms, checkInDate, checkOutDate);
        }

    }

    private static void validateIfHasAccount(Collection<Room> availableRooms, Date checkInDate, Date checkOutDate) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you have an account with us? y/n");
        final String hasAccount = scanner.nextLine();

        if (hasAccount.equals("y")) {
            makeReservation(availableRooms, checkInDate, checkOutDate);
            start();
        } else if (hasAccount.equals("n")) {
            System.out.println("Please, create an account.");
            start();
        } else {
            System.out.println("Wrong answer please say 'y' or 'n' ...");
            validateIfHasAccount(availableRooms, checkInDate, checkOutDate);
        }
    }

    private static void makeReservation(Collection<Room> availableRooms, Date checkInDate, Date checkOutDate) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your email ...");
        final String clientEmail = scanner.nextLine();

        Customer customer = hotelResources.getCustomer(clientEmail);

        if (customer == null) {
            System.out.println("Wrong email/account. You can try to create a new account.");
            start();
        } else {
            System.out.println("Select a room number to book?");
            final String roomNumber = scanner.nextLine();

            if (availableRooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {
                final Room room = hotelResources.getRoom(roomNumber);
                final Reservation reservation = hotelResources.bookARoom(clientEmail, room, checkInDate, checkOutDate);

                System.out.println("Reservation created successfully!");
                System.out.println(reservation);
                start();
            } else {
                System.out.println("Error: room number not found.");
                start();
            }
        }
    }


}
