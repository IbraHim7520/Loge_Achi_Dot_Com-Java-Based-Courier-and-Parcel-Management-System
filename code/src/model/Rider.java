package model;

import file.ParcelFile;
import file.RiderFile;

import java.util.ArrayList;

public class Rider {

    private User user;
    Rider(User user){
        if(!validateUser(user)){
            System.out.println("Unauthorized Access!");
            this.user = null;
        } else {
            this.user = user;
        }

    }

    public boolean validateRider(User user){
        if(user==null){
            System.out.println("Unauthorized Access!");
            return  false;
        }

        if(user.getUser_id()==null || user.getUser_role()!=String.valueOf(User.UserRole.RIDER)){
            System.out.println("Unauthorized Access");
            return false;
        }
        return true;
    }

    public void viewPendingParcels() {

    }

    public void acceptParcelsByRider(String parcelId, String riderID){

    }

    public void viewMyAssignedParcles() {

    }

    public void updateParcelStatus(String parcelId, String newStatus) {

    }

    public void searchAParcel(String parcelId){
        //ekhane parcel id diye parcel search er kaj korbo;
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

        if (!user.getUser_role().contains("RIDER")) {
            return false;
        }

        return true;
    }
}