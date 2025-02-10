import main.java.com.souza.library.model.Library;

import java.util.Scanner;

import static main.java.com.souza.library.menu.Book.BookMenu;
import static main.java.com.souza.library.menu.Loan.LoanMenu;
import static main.java.com.souza.library.menu.User.MemberMenu;


public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Books");
            System.out.println("2. Users");
            System.out.println("3. Loans/Returns");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch(choice) {
                case 1: BookMenu(library, scanner); break;
                case 2: MemberMenu(library, scanner); break;
                case 3: LoanMenu(library, scanner); break;
                case 4: System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
    }

}