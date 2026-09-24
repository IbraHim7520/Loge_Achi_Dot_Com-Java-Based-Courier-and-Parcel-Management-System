package model;
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
        ArrayList<String> parcels = RiderFile.getPendingParcels();

        if (parcels.isEmpty()) {
            System.out.println("No pending parcels found!");
            return;
        }

        for (String parcel : parcels) {
            System.out.println(parcel);
        }
    }


    public void acceptParcelsByRider(String parcelId, String riderID){

    }

    public void viewMyAssignedParcles() {
        ArrayList<String> parcels = RiderFile.getMyAssignedParcels(user.getUser_id());

        if (parcels.isEmpty()) {
            System.out.println("No assigned parcels found!");
            return;
        }

        for (String parcel : parcels) {
            System.out.println(parcel);
        }
    }

    public void updateParcelStatus(String parcelId, String newStatus) {
        boolean result = RiderFile.updateParcelStatusByRider(parcelId, user.getUser_id(), newStatus);

        if (result) {
            System.out.println("Parcel status updated successfully!");
        } else {
            System.out.println("Failed to update parcel status!");
        }
    }
//    public void searchAParcel(String parcelId){
//        //ekhane parcel id diye parcel search er kaj korbo;
//    }

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