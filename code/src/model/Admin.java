package model;
import java.util.*;

import file.AdminFile;
import file.UserFile;

public class Admin {

    private String admin_id;
    private String admin_email;

    public Admin() {
        User user = new User();
        String userEmail = user.getUser_email();
        if(!userEmail.endsWith("@admin.com")) {
            throw new IllegalArgumentException("Invalid Admin email or Youre not a Admin");
        }
        String userId = UserFile.getCurrentUser(userEmail);
        if(!userId.equals(admin_id)) {
            throw new IllegalArgumentException("Invalid Admin ID or Youre not a Admin");
        }
        this.admin_id = userId;
        this.admin_email = userEmail;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }


    public ArrayList<User> getAllUsers() {

        UserFile userFile = new UserFile();
        return userFile.getAllUsers();
    }

    public boolean removeUser(String user_id) {
        return false;
    }

    public boolean changeUserRole(String user_id, String newRole) {
        return false;
    }

    public List<Parcel> getAllParcels() {
        return null;
    }

    public boolean assignRider(Parcel parcel, Rider rider) {
        return false;
    }

    public boolean cancelParcel(Parcel parcel) {
        return false;
    }

    public List<Rider> getAllRiders() {
        return null;
    }

    public boolean addRider(String email) {
        return false;
    }

    public boolean removeRider(String rider_id) {
        return false;
    }

    public int getTotalUsers(){
        try {
            AdminFile adf = new AdminFile();
            int result = adf.countUsers();

            return result;
        }catch (Exception e){
            return 0;
        }
    }

    public int getTotalRiders(){
        try {
            AdminFile adminFile = new AdminFile();
            int result = adminFile.countRider();
            return result;
        } catch (Exception e) {
            return 0;
        }
    }

    public int getTotalParcels(){
        try {
            AdminFile adfile = new AdminFile();
            int cnt = adfile.countTotalParcels();
            return cnt;
        } catch (Exception e) {
            return 0;
        }
    }
}
