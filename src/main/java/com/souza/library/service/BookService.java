package main.java.com.souza.library.service;

import main.java.com.souza.library.Exceptions.BookAlreadyExistsException;
import main.java.com.souza.library.Exceptions.BookNotFoundException;
import main.java.com.souza.library.Exceptions.InvalidIsbnException;
import main.java.com.souza.library.model.Book;
import main.java.com.souza.library.model.Loan;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<Book> books;

    public BookService() {
        this.books = new ArrayList<>();
    }

    public void addBooks (String isbn, String title,
                          String author, String publicationYear,
                          boolean availabilityStatus) throws InvalidIsbnException, BookAlreadyExistsException {
        if (!isValidIsbn(isbn)) {
            throw new InvalidIsbnException("Error: Invalid ISBN! The ISBN must contain only numbers and have 3 or 5 digits.");
        }
        Book book = new Book(isbn, title, author, publicationYear, availabilityStatus);

        if (books.contains(book)) {
            throw new BookAlreadyExistsException("Error: The book with ISBN " + isbn + " is already registered.");
        }

        books.add(book);
        System.out.println("Book successfully registered: " + book);

    }

    public void updateBook(String isbn, String newTitle,
                           String newAuthor, String newPublicationYear,
                           boolean newAvailabilityStatus) throws BookNotFoundException {
        for (Book book : books) {
            if(book.getIsbn().equals(isbn)) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                book.setPublicationYear(newPublicationYear);
                book.setAvailabilityStatus(newAvailabilityStatus);
                System.out.println("Book successfully updated: " + book);
                return;
            }
        }
        throw new BookNotFoundException("Error: No book found with ISBN " + isbn);
    }

    public void removeBook (String isbn) throws BookNotFoundException {
        Book bookToRemove = null;

        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Book successfully removed: " + bookToRemove);
        } else {
            throw new BookNotFoundException("Error: No book found with ISBN " + isbn);
        }
    }

    public void listBooks () {
        if(books.isEmpty()) {
            System.out.println("No book found");
        } else {
            System.out.println("List of Books");
            for(Book book: books) {
                System.out.println(book);
            }
        }
    }

    private boolean isValidIsbn(String isbn) {
        return isbn != null && isbn.matches("\\d{3}|\\d{5}"); // ISBN de 10 ou 13 dígitos numéricos
    }

    public Book findBookByIsbn(String isbn) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        throw new BookNotFoundException("Error: No book found with ISBN " + isbn);
    }

}
