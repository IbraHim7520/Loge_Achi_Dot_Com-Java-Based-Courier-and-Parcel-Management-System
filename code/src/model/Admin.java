package model;


public class Admin {
    User user;
    Admin(User user){
        if(!validateUser(user)){
            System.out.println("Unauthorized Access! Please be authorize before try again.");
        }else{
            this.user = user;
        }
    }

    // View all users
    public void viewAllUser() {
        // ekhane file theke all users ene show korabo
    }

    // Register a new rider
    public void registerNewRider() {
        // ekhane new rider register korar kaj korbo
    }

    // Update user information
    public void updateUser() {
        // ekhane user information update korar kaj korbo
    }

    // View all parcels
    public void viewAllParcel() {
        // ekhane file theke all parcels ene show korabo
    }

    // Search a parcel
    public void searchParcel(String parcelID) {
        // ekhane parcel ID diye parcel search korbo
    }

    // Search a user
    public void searchUser(String userID) {
        // ekhane user ID diye user search korbo
    }

    // Delete a user
    public void deleteUser(String userID) {
        // ekhane user delete korar kaj korbo
    }

    // Delete a parcel
    public void deleteParcel(String parcelID) {
        // ekhane parcel delete korar kaj korbo
    }

    // Update parcel status
    public void updateParcelStatus(String parcelID, String newStatus) {
        // ekhane parcel er status update korbo
    }

    private boolean validateUser(User user) {

        if (user == null) {
            return false;
        }

        if (user.getUser_id() == null) {
            return false;
        }

        if (user.getUser_role() == null) {
            return false;
        }

        if (!user.getUser_role().contains("ADMIN")) {
            return false;
        }

        return true;
    }
}