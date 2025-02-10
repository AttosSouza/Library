package main.java.com.souza.library.Exceptions;

public class BookAlreadyExistsException extends Exception{
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
