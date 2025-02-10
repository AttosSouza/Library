package main.java.com.souza.library.model;

import java.time.LocalDate;
import java.util.List;

public class Loan {
    private Book book;
    private User user;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan(Book book, User user) {
        this.book = book;
        this.user = user;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void registerReturn() {
        this.returnDate = LocalDate.now();
        book.setAvailabilityStatus(true); // Torna o livro disponível novamente
        System.out.println("Livro devolvido com sucesso: " + book.getTitle());
    }

    @Override
    public String toString() {
        return "Book:" + book.getTitle() +
                ", User:" + user.getName() +
                ", Loan Date = " + loanDate +
                ", Return Date=" + (returnDate != null ? returnDate : "Não devolvido");
    }
}
