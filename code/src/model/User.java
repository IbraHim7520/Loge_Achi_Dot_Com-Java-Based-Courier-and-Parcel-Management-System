package model;

public class User extends Authentication {
    private String user_name;
    private String user_email;
    private String user_pass;
    private String user_role;
    private String user_id;

  

    public User(String user_name, String user_email , String user_pass, String user_role, String user_id){
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_pass = user_pass;
        this.user_role = user_role;
        this.user_id = user_id;
    }

    public User(String user_email , String user_pass){
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
    public void setUser_name(String user_name) {
        this.user_name = user_name;
        
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
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
    public String login(User user) {
        return "User logged in successfully";
    }

    @Override
    public String register(User user) {
        return "User registered successfully";
    }
}
