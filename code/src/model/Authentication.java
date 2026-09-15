package model;

public abstract class Authentication {
    public  abstract String login(User user);
    public abstract String register(User user);

    public abstract boolean validateEmail(String email);
    public abstract boolean validatePassword(String pass);
}

