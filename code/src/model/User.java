package model;

import file.UserFile;

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
        this.user_id = generateID();
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
    public String getUserPassword(){
        return this.user_pass;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    public void setPassword(String user_pass) {this.user_pass = user_pass;}
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
        String email = user.getUser_email();
        String password = user.getUserPassword();
        UserFile ufl = new UserFile();
        boolean loginRes = ufl.userRegisteredCheck(email, password);
        if(loginRes){
            return "User login successfull.";
        }else{
            return "Failed to Login.\nInvalid Email or Password \nOr, No User found! Please Register.";
        }
    }

    @Override
    public String register(User user) {
        UserFile ufl = new UserFile();
        boolean registerResult = ufl.createNewUser(user);
        if(registerResult){
            return "User Registered in successfully";
        }

        return  "Failed to Register User, Please try again!";
    }

    private String generateID(){
        int UserCount = 100;
        UserCount = UserCount+1;
        String id = new String("USER"+UserCount);
        return  id;
    }

}
