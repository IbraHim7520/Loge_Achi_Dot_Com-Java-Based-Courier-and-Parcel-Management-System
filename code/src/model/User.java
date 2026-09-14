package model;

public abstract class User extends Authentication {
    private String user_name;
    private String user_email;
    private String user_pass;
    private String user_role;
    private String user_id;

    private String generateID(String name){
          return  name;
    }

    public User(String user_name, String user_email , String user_pass, String user_role ){

    }
}
