package model;

import custom_exception.NotFoundException;
import file.UserFile;

import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class User extends Authentication {

    private String user_name;
    private String user_email;
    private String user_pass;
    private String user_role;
    private String user_id;

    public enum UserRole {
        USER,
        ADMIN,
        RIDER
    }

    // Constructor for registration
    public User(String user_name, String user_email, String user_pass) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_pass = user_pass;
        this.user_role = null;
        this.user_id = null;
    }

    // Constructor for login
    public User(String user_email, String user_pass) {
        this.user_email = user_email;
        this.user_pass = user_pass;
        this.user_role = null;
        this.user_id = null;
    }

    // Getters
    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_role() {
        return user_role;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUserPassword() {
        return user_pass;
    }

    // Setters
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setPassword(String user_pass) {
        this.user_pass = user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    // Validate email
    @Override
    public boolean validateEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            return false;
        }

       if(!email.contains("@") || !email.contains(".") || !email.contains(".com")) {
           return false;
       }
        return true;
    }

    // Validate password
    @Override
    public boolean validatePassword(String pass) {
        return pass != null && pass.length() >= 6;
    }

    // Login
    @Override
    public String login(User user) throws NotFoundException {

        if (user == null) {
            return "Invalid login information.";
        }

        String email = user.getUser_email();
        String password = user.getUserPassword();

        if (!validateEmail(email)) {
            return "This email is invalid. Please enter a valid email!";
        }

        if (!validatePassword(password)) {
            return "Password must be at least 6 characters long.";
        }

        UserFile userFile = new UserFile();

        try {

            User loggedUser = userFile.loginUser(email, password);
            this.user_name = loggedUser.getUser_name();
            this.user_email = loggedUser.getUser_email();
            this.user_pass = loggedUser.getUserPassword();
            this.user_role = loggedUser.getUser_role();
            this.user_id = loggedUser.getUser_id();
            return "User login successful.";

        } catch (NotFoundException e) {
            return "Failed to Login.\n" + e.getMessage();
        }
    }

    @Override
    public String register(User user) {

        if (user == null) {
            return "Invalid user information.";
        }

        if (user.getUser_name() == null || user.getUser_name().trim().isEmpty()) {
            return "Username cannot be empty.";
        }

        if (!validateEmail(user.getUser_email())) {
            return "Invalid email!";
        }

        if (!validatePassword(user.getUserPassword())) {
            return "Password must be at least 6 characters long.";
        }

        UserFile userFile = new UserFile();

        boolean result = userFile.createNewUser(user);

        if (result) {
            return "User registered successfully.\n"
                    + "User ID: " + user.getUser_id()
                    + "\nRole: " + user.getUser_role();
        }

        return "Failed to Register User.\n" + "Email may already be registered.";
    }

    public String generateUserID() {
        LocalTime lt = LocalTime.now();
        Random rnd = new Random();
        int randomInt = rnd.nextInt(999);
        return "U" + (lt.getSecond()*randomInt);
    }
}