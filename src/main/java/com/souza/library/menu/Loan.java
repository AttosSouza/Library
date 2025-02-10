package main.java.com.souza.library.menu;

import main.java.com.souza.library.Exceptions.BookNotFoundException;
import main.java.com.souza.library.Exceptions.UserNotFoundException;
import main.java.com.souza.library.model.Library;

import java.util.Scanner;

public class Loan {
    public static void LoanMenu(Library library, Scanner scanner) {
        while (true) {
            System.out.println("\nLoan Management");
            System.out.println("1. Register a loan");
            System.out.println("2. List Loans");
            System.out.println("3. Register a return");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addLoan(library, scanner);
                    break;
                case 2:
                    library.loanList();
                    break;
                case 3:
                    addReturnBook(library, scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addLoan(Library library, Scanner scanner) {
        try {
            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();

            System.out.print("Enter ID of the user: ");
            String userId = scanner.nextLine();

            // scanner.nextLine(); // consume newline

            library.registerLoan(isbn, userId);
        } catch (BookNotFoundException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }


    private static void addReturnBook(Library library, Scanner scanner) {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter ID of the user: ");
        String userId = scanner.nextLine();

        scanner.nextLine(); // consume newline

        library.registerReturn(isbn, userId);
    }
}
