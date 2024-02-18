package ui;

import api.AdminResources;
import api.HotelResources;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

import static ui.AdminMenu.adminMenu;

public class MainMenu {
    private static final HotelResources hotelResources = HotelResources.getInstance();
    private static final AdminResources adminResources = AdminResources.getInstance();

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        try {
            printMainOptions();
            int selection = Integer.parseInt(scanner.nextLine());

            switch (selection) {
                case 1:
                    findAndReserveARoom();
                    break;
                case 2:
                    seeMyReservations();
                    break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    adminMenu();
                    break;
                case 5:
                    System.out.println("Thanks for using our app.");
                    break;
                default:
                    System.out.println("Please enter a valid option number");
                    start();
            }
        } catch (Exception exception) {
            System.out.println("Invalid Input");
            start();
        }
    }

    private static void seeMyReservations() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Email...");
        final String clientEmail = scanner.nextLine();

        Collection<Reservation> clientReservations =
                hotelResources.getCustomerReservations(clientEmail);

        if (clientReservations == null || clientReservations.isEmpty()) {
            System.out.println("Reservations under that email are empty");
        } else {
            clientReservations.forEach(System.out::println);
        }

        start();
    }

    private static void findAndReserveARoom() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Check-In Date mm/dd/yyyy example 02/25/2024");
        Date checkIn = getInputDate(scanner);

        System.out.println("Enter Check-Out Date mm/dd/yyyy example 02/25/2024");
        Date checkOut = getInputDate(scanner);

        if (checkIn != null && checkOut != null) {
            Collection<Room> availableRooms = hotelResources.findARoom(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                System.out.println("No room available for those dates");
                start();
            } else {
                availableRooms.forEach(System.out::println);
                reserveRoom(scanner, checkIn, checkOut, availableRooms);
            }
        }
    }

    private static Date getInputDate(final Scanner scanner) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Error: Invalid date.");
            findAndReserveARoom();
        }
        return null;
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
        try {
            System.out.println("Enter your first name");
            String first = scanner.nextLine();

            System.out.println("Enter your Last name");
            String last = scanner.nextLine();

            System.out.println("Enter your email");
            String email = scanner.nextLine();

            hotelResources.createCustomer(email, first, last);

            System.out.println("Customer added!");

            start();

        } catch (Exception exception) {
            System.out.println("Invalid Input data for adding a customer");
            createAccount();
        }

    }

    private static void reserveRoom(final Scanner scanner, final Date checkInDate,
                                    final Date checkOutDate, final Collection<Room> rooms) {
        System.out.println("Would you like to book? y/n");
        final String bookRoom = scanner.nextLine();

        if ("y".equals(bookRoom)) {
            System.out.println("Do you have an account with us? y/n");
            final String haveAccount = scanner.nextLine();

            if ("y".equals(haveAccount)) {
                System.out.println("Enter Email format: name@domain.com");
                final String clientEmail = scanner.nextLine();

                Customer customer = hotelResources.getCustomer(clientEmail);

                if (customer == null) {
                    System.out.println("Wrong email/account. You can try to create a new account.");
                } else {
                    System.out.println("Select a room number to book?");
                    final String roomNumber = scanner.nextLine();

                    if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {
                        final Room room = hotelResources.getRoom(roomNumber);

                        final Reservation reservation = hotelResources
                                .bookARoom(clientEmail, room, checkInDate, checkOutDate);
                        System.out.println("Reservation created successfully!");
                        System.out.println(reservation);
                    } else {
                        System.out.println("Error: room number not found.");
                    }
                }

                start();
            } else {
                System.out.println("Please, create an account.");
                start();
            }
        } else if ("n".equals(bookRoom)) {
            start();
        } else {
            reserveRoom(scanner, checkInDate, checkOutDate, rooms);
        }
    }


}
