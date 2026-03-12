package service;

import java.util.ArrayList;
import java.util.List;
import model.User;

public class AuthenticationService {

    private List<User> users = new ArrayList<>();
    private boolean loggedIn = false;

    public AuthenticationService() {
        users.add(new User(1, "admin", "1234"));
        users.add(new User(2, "aseel", "1111"));
        users.add(new User(3, "lara", "2222"));
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                user.getPassword().equals(password)) {
                loggedIn = true;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        loggedIn = false;
        System.out.println("Logged out successfully.");
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}