package model;
import file.UserFile;
public class User extends Authentication {
    private String user_name;
    private String user_email;
    private String user_pass;
    private String user_role;
    private String user_id;

    public String USER_EXISTS = null;
    private static int userCount = 100;


    public User(String user_name, String user_email, String user_pass, String user_role) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_pass = user_pass;
        this.user_role = user_role;
        this.user_id = generateID();
    }

    public User(){
        
    }

    public User(String user_email, String user_pass) {
        this.user_email = user_email;
        this.user_pass = user_pass;
    }


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


    @Override
    public boolean validateEmail(String email) {

        if (email == null || email.isEmpty()) {
            return false;
        }

        return email.contains("@")
                && email.contains(".com");
    }


    @Override
    public boolean validatePassword(String pass) {

        if (pass == null || pass.length() < 6) {
            return false;
        }

        return true;
    }


    @Override
    public String login(User user) {

        String email = user.getUser_email();
        String password = user.getUserPassword();

        if (!validateEmail(email)) {

            return "This email is invalid. Please enter a valid email!";
        }

        if (!validatePassword(password)) {

            return "Password must be at least 6 characters long.";
        }


        UserFile ufl = new UserFile();

        boolean loginRes =
                ufl.userRegisteredCheck(email, password);


        if (loginRes) {

            USER_EXISTS =
                    UserFile.getCurrentUser(email);

            this.user_id = USER_EXISTS;

            return "User login successful.";

        } else {

            return "Failed to Login.\n"
                    + "Invalid Email or Password\n"
                    + "Or, No User found! Please Register.";
        }
    }


    @Override
    public String register(User user) {

        if (!validateEmail(user.getUser_email())) {

            return "Invalid email!";
        }

        if (!validatePassword(user.getUserPassword())) {

            return "Password must be at least 6 characters long.";
        }


        UserFile ufl = new UserFile();

        boolean registerResult =
                ufl.createNewUser(user);


        if (registerResult) {

            USER_EXISTS =
                    UserFile.getCurrentUser(
                            user.getUser_email()
                    );

            this.user_id = USER_EXISTS;

            return "User registered successfully.";
        }

        return "Failed to Register User. Please try again!";
    }


    private String generateID() {

        userCount++;

        return "USER" + userCount;
    }
}
