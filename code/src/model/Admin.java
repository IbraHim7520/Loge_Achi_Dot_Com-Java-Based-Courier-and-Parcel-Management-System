package model;



import java.util.List;

public class Admin {

    private String admin_id;
    private String admin_name;

    public Admin(String admin_id, String admin_name) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public List<User> getAllUsers() {
        return null;
    }

    public User getUser(String user_id) {
        return null;
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

    public boolean addRider(Rider rider) {
        return false;
    }

    public boolean removeRider(String rider_id) {
        return false;
    }
}
