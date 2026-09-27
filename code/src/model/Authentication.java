package model;

import custom_exception.NotFoundException;

public abstract class Authentication {
    public  abstract String login(User user) throws NotFoundException;
    public abstract String register(User user);

    public abstract boolean validateEmail(String email);
    public abstract boolean validatePassword(String pass);
}

