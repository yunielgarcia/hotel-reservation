package ui;

import api.HotelResources;

import java.util.Scanner;

public class MainMenu {
    private static final HotelResources hotelResources = HotelResources.getInstance();

    public static void start() {
        Scanner scanner = new Scanner(System.in);
            try {
                printMainOptions();
                int selection = Integer.parseInt(scanner.nextLine());

                switch (selection) {
                    case 3:
                        createAccount();
                        break;
                    case 4:
//                        adminMenu();
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
//    public static void printMainOptions() {
//        System.out.println("Welcome to the Hotel Reservation Application");
//        System.out.println("---------------------------------------------------");
//        System.out.println("1. Find and reserve a room");
//        System.out.println("2. See my reservations");
//        System.out.println("3. Create an Account");
//        System.out.println("4. Admin");
//        System.out.println("5. Exit");
//        System.out.println("---------------------------------------------------");
//        System.out.println("Please select a number for the menu option");
//
//    }

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

//    public static void adminMenu() {
//        Scanner scanner = new Scanner(System.in);
//        try {
//            printMainOptions();
//            int selection = Integer.parseInt(scanner.nextLine());
//
//            switch (selection) {
//                case 3:
//                    createAccount();
//                    break;
//                case 4:
//                    adminMenu();
//                    break;
//                case 5:
//                    System.out.println("Thanks for using our app.");
//                    break;
//                default:
//                    System.out.println("Please enter a valid option number");
//                    start();
//            }
//        } catch (Exception exception) {
//            System.out.println("Invalid Input");
//            start();
//        }
//    }


}
