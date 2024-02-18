package ui;

import api.AdminResources;
import model.Customer;
import model.Reservation;
import model.Room;
import model.RoomType;

import java.util.Collection;
import java.util.Scanner;

import static ui.MainMenu.start;

public class AdminMenu {

    static AdminResources adminResources = AdminResources.getInstance();

    public static void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        try {
            printAdminOptions();
            int adminSelection = Integer.parseInt(scanner.nextLine());

            switch (adminSelection) {
                case 1:
                    seeAllCustomers();
                    break;
                case 2:
                    seeAllRooms();
                    break;
                case 3:
                    seeAllReservations();
                    break;
                case 4:
                    addRoom();
                    break;
                case 5:
                    start();
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

    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResources.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
        adminMenu();
    }

    private static void addRoom() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter room number:");
        String roomNumber = scanner.nextLine();

        System.out.println("Enter price per night:");
        double roomPrice = validateRoomPrice(scanner);

        System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
        RoomType roomType = validateRoomType(scanner);

        Room room = new Room(roomNumber, roomPrice, roomType);

        adminResources.addRoom(room);
        System.out.println("Room added successfully!");

        adminMenu();
    }

    private static void seeAllRooms() {
        Collection<Room> rooms = adminResources.getAllRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            rooms.forEach(System.out::println);
        }
        adminMenu();
    }

    private static void seeAllReservations() {
        Collection<Reservation> reservations = adminResources.displayAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservation found");
        } else {
            reservations.forEach(System.out::println);
        }
        adminMenu();
    }

    private static void printAdminOptions() {
        System.out.println("Admin Menu");
        System.out.println("---------------------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to main menu");
        System.out.println("---------------------------------------------------");
        System.out.println("Please select a number for the menu option");

    }

    private static double validateRoomPrice(Scanner scanner) {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException exp) {
            System.out.println("Invalid room price! Please, enter a valid price.");
            return validateRoomPrice(scanner);
        }
    }

    private static RoomType validateRoomType(Scanner scanner) {
        try {
            return RoomType.valueFromNumberOption(scanner.nextLine());
        } catch (NumberFormatException exp) {
            System.out.println("Invalid room type! Enter a valid option");
            return validateRoomType(scanner);
        }
    }


}
