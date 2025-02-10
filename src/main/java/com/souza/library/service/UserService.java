package main.java.com.souza.library.service;

import main.java.com.souza.library.Exceptions.BookNotFoundException;
import main.java.com.souza.library.Exceptions.UserAlreadyExistsException;
import main.java.com.souza.library.Exceptions.UserNotFoundException;
import main.java.com.souza.library.model.Loan;
import main.java.com.souza.library.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private  List<User> users;

    public UserService() {
        this.users =  new ArrayList<>();
    }

    public void addUser (String id, String name, String email) throws UserAlreadyExistsException {
        User user = new User(id, name, email);

        if (users.contains(user)) {
            throw new UserAlreadyExistsException("Error: The user with ID " + id + " is already registered.");
        }

        users.add(user);
        System.out.println("User successfully registered: " + user);
    }

    public void updateUser(String id, String newName,
                           String newEmail) throws UserNotFoundException {
        for (User user : users) {
            if(user.getId().equals(id)) {
                user.setName(newName);
                user.setEmail(newEmail);
                System.out.println("User successfully updated: " + user);
                return;
            }
        }
        throw new UserNotFoundException("Error: No user found with ID " + id);
    }

    public void removeUser (String id) throws UserNotFoundException {
        User userToRemove = null;

        for (User user : users) {
            if (user.getId().equals(id)) {
                userToRemove = user;
                break;
            }
        }

        if (userToRemove != null) {
            users.remove(userToRemove);
            System.out.println("User successfully removed: " + userToRemove);
        } else {
            throw new UserNotFoundException("Error: No user found with ID " + id);
        }
    }

    public void userList () {
        if(users.isEmpty()) {
            System.out.println("Empty list, no registered users");
        } else {
            System.out.println("List of Users: ");
            for(User user: users) {
                System.out.println(user);
            }
        }

    }

    public User findUserById(String userId) throws UserNotFoundException {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        throw new UserNotFoundException("Error: No book found with ISBN " + userId);
    }
}
