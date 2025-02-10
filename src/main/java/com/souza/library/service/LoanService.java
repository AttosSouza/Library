package main.java.com.souza.library.service;

import main.java.com.souza.library.Exceptions.BookNotFoundException;
import main.java.com.souza.library.Exceptions.UserNotFoundException;
import main.java.com.souza.library.model.Book;
import main.java.com.souza.library.model.Loan;
import main.java.com.souza.library.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoanService {
    private final List<Loan> loans;
    private final BookService bookService;
    private final UserService userService;

    public LoanService(BookService bookService, UserService userService) {
        this.loans = new ArrayList<>();
        this.bookService = bookService;
        this.userService = userService;
    }

    private Book findBookByIsbn(String isbn) throws BookNotFoundException {
        return bookService.findBookByIsbn(isbn);
    }

    private User findUserById(String userId) throws UserNotFoundException {
        return userService.findUserById(userId);
    }

    public void registerLoan(String isbn, String userId) throws BookNotFoundException, UserNotFoundException {
        Book book = findBookByIsbn(isbn);
        User user = findUserById(userId);

        if (book == null) {
            throw new BookNotFoundException("Error: Book with ISBN " + isbn + " not found.");
        }
        if (user == null) {
            throw new UserNotFoundException("Error: User with ID " + userId + " not found.");
        }
        if (!book.isAvailabilityStatus()) {
            System.out.println("Error: The Book " + book.getTitle() + " is not available for loan.");
            return;
        }

        book.setAvailabilityStatus(false);
        Loan loan = new Loan(book, user);
        loans.add(loan);
        System.out.println("Registered loan: " + loan);
    }

    public void registerReturn(String isbn, String userId) {
        for (Loan loan : loans) {
            if (loan.getBook().getIsbn().equals(isbn) &&
                    loan.getUser().getId().equals(userId) &&
                    loan.getReturnDate() == null) {

                loan.registerReturn();
                return;
            }
        }
        System.out.println("Error: No active loan found for ISBN " + isbn + " and user " + userId);
    }

    public void listLoans () {
        if(loans.isEmpty()) {
            System.out.println("Empty list, no loans registered");
        } else {
            System.out.println("Borrowed books: ");
            for(Loan loan: loans) {
                System.out.println(loan);
            }
        }
    }

}
