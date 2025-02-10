package main.java.com.souza.library.menu;

import main.java.com.souza.library.Exceptions.UserNotFoundException;
import main.java.com.souza.library.model.Library;

import java.util.Scanner;

public class User {
    public static void MemberMenu(Library library, Scanner scanner) {
        while (true) {
            System.out.println("\nUser Management");
            System.out.println("1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. List Users");
            System.out.println("4. Remove User By ID");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addUser(library, scanner);
                    break;
                case 2:
                    updateUser(library, scanner);
                    break;
                case 3:
                    library.userList();
                    break;
                case 4:
                    removeUser(library, scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addUser(Library library, Scanner scanner) {
        try {
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            // scanner.nextLine(); // consume newline

            library.addUser(id, name, email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void updateUser(Library library, Scanner scanner) {
        try {
            System.out.print("Enter ID of the user to update: ");
            String id = scanner.nextLine();

            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();

            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();

            //scanner.nextLine(); // consume newline

            library.updateUser(id, newName, newEmail);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeUser(Library library, Scanner scanner) {
        try {
            System.out.print("Enter ID of the user to remove: ");
            String id = scanner.nextLine();

            library.removeUser(id);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
