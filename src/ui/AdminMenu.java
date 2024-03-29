package ui;

import api.AdminResources;
import model.Customer;
import model.Reservation;
import model.Room;
import model.RoomType;
import utils.Utils;

import java.util.Collection;
import java.util.Scanner;

import static ui.MainMenu.start;

public class AdminMenu {

    static AdminResources adminResources = AdminResources.getInstance();

    public static void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        printAdminOptions();
        String adminSelection = scanner.nextLine();

        switch (adminSelection) {
            case "1" -> seeAllCustomers();
            case "2" -> seeAllRooms();
            case "3" -> seeAllReservations();
            case "4" -> addRoom();
            case "5" -> start();
            default -> {
                System.out.println("Please enter a valid option number");
                adminMenu();
            }
        }
    }

    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResources.getAllCustomers();
        Utils.printCollection(customers, "Customer");
        adminMenu();
    }

    private static void seeAllRooms() {
        Collection<Room> rooms = adminResources.getAllRooms();
        Utils.printCollection(rooms, "Room");
        adminMenu();
    }

    private static void seeAllReservations() {
        Collection<Reservation> reservations = adminResources.displayAllReservations();
        Utils.printCollection(reservations, "Reservation");
        adminMenu();
    }

    private static void addRoom() {

        System.out.println("Enter room number:");
        String roomNumber = String.valueOf(validateRoomNumber());

        System.out.println("Enter price per night:");
        double roomPrice = validateRoomPrice();

        System.out.println("Enter room type: 'S' for single bed, 'D' for double bed:");
        RoomType roomType = validateRoomTypeSelection();

        Room room = new Room(roomNumber, roomPrice, roomType);

        boolean added = adminResources.addRoom(room);

        if (added) {
            System.out.println("Room added successfully!");
        } else {
            System.out.println("Room already exist.");
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

    private static int validateRoomNumber() {
        Scanner scanner = new Scanner(System.in);
        String stringPrice = scanner.nextLine();
        try {
            return Integer.parseInt(stringPrice);
        } catch (NumberFormatException exp) {
            System.out.println("Invalid room number! Please, enter a valid number.");
            return validateRoomNumber();
        }
    }

    private static double validateRoomPrice() {
        Scanner scanner = new Scanner(System.in);
        String stringPrice = scanner.nextLine();
        try {
            return Double.parseDouble(stringPrice);
        } catch (NumberFormatException exp) {
            System.out.println("Invalid room price! Please, enter a valid price.");
            return validateRoomPrice();
        }
    }

    private static RoomType validateRoomTypeSelection() {
        Scanner scanner = new Scanner(System.in);
        String rSelection = scanner.nextLine();

        try {
            return RoomType.valueFromNumberOption(rSelection);
        } catch (IllegalArgumentException exp) {
            System.out.println("Invalid room type! Enter a valid option");
            return validateRoomTypeSelection();
        }
    }


}
