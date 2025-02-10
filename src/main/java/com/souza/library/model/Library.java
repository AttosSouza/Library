package main.java.com.souza.library.model;

import main.java.com.souza.library.Exceptions.*;
import main.java.com.souza.library.service.BookService;
import main.java.com.souza.library.service.LoanService;
import main.java.com.souza.library.service.UserService;

public class Library {
    private String name;
    private final BookService bookService;
    private final UserService userService;
    private final LoanService loanService;


    public Library() {
        this.name = null;
        this.bookService = new BookService();
        this.userService = new UserService();
        this.loanService = new LoanService(bookService, userService);
    }

    public String getName() {
        return name;
    }

    // Book Management
    public void addBook(String isbn, String title,
                        String author, String publicationYear,
                        boolean availabilityStatus) throws InvalidIsbnException, BookAlreadyExistsException {
        bookService.addBooks(isbn, title, author, publicationYear, availabilityStatus);

    }

    public void updateBook(String isbn, String newTitle, String newAuthor, String newPublicationYear, boolean newAvailabilityStatus) throws BookNotFoundException {
        bookService.updateBook(isbn, newTitle, newAuthor, newPublicationYear, newAvailabilityStatus);
    }

    public void removeBook(String isbn) throws BookNotFoundException {
        bookService.removeBook(isbn);
    }

    public void listBooks() {
        bookService.listBooks();
    }

    // User Management
    public void addUser (String id, String name, String email) throws UserAlreadyExistsException {
        userService.addUser(id, name, email);
    }

    public void updateUser(String id, String newName,
                           String newEmail) throws UserNotFoundException {
        userService.updateUser(id, newName, newEmail);
    }

    public void removeUser (String id) throws UserNotFoundException {
        userService.removeUser(id);
    }

    public void userList () {
        userService.userList();
    }

    // Loan Management
    public void registerLoan(String isbn, String userId) throws BookNotFoundException, UserNotFoundException {
        loanService.registerLoan(isbn, userId);
    }

    public void registerReturn(String isbn, String userId) {
        loanService.registerReturn(isbn, userId);
    }

    public void loanList () {
       loanService.listLoans();
    }
}
