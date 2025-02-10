package main.java.com.souza.library.menu;

import main.java.com.souza.library.Exceptions.BookAlreadyExistsException;
import main.java.com.souza.library.Exceptions.BookNotFoundException;
import main.java.com.souza.library.Exceptions.InvalidIsbnException;
import main.java.com.souza.library.model.Library;

import java.util.Scanner;

public class Book {
    public static void BookMenu(Library library, Scanner scanner) {
        while (true) {
            System.out.println("\nBook Management");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. List Books");
            System.out.println("4. Remove Book by ISBN");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook(library, scanner);
                    break;
                case 2:
                    updateBook(library, scanner);
                    break;
                case 3:
                    library.listBooks();
                    break;
                case 4:
                    removeBook(library, scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addBook(Library library, Scanner scanner) {
        try {
            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();

            System.out.print("Enter Title: ");
            String title = scanner.nextLine();

            System.out.print("Enter Author: ");
            String author = scanner.nextLine();

            System.out.print("Enter Publication Year: ");
            String publicationYear = scanner.nextLine();

            System.out.print("Is the book available? (true/false): ");
            boolean availability = scanner.nextBoolean();
            scanner.nextLine();

            library.addBook(isbn, title, author, publicationYear, availability);
        } catch (InvalidIsbnException | BookAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void updateBook(Library library, Scanner scanner) {
        try {
            System.out.print("Enter ISBN of the book to update: ");
            String isbn = scanner.nextLine();

            System.out.print("Enter new Title: ");
            String newTitle = scanner.nextLine();

            System.out.print("Enter new Author: ");
            String newAuthor = scanner.nextLine();

            System.out.print("Enter new Publication Year: ");
            String newPublicationYear = scanner.nextLine();

            System.out.print("Is the book available? (true/false): ");
            boolean newAvailability = scanner.nextBoolean();
            scanner.nextLine(); // consume newline

            library.updateBook(isbn, newTitle, newAuthor, newPublicationYear, newAvailability);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeBook(Library library, Scanner scanner) {
        try {
            System.out.print("Enter ISBN of the book to remove: ");
            String isbn = scanner.nextLine();

            library.removeBook(isbn);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
