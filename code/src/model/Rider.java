package model;

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

    public void viewPendingParcels(){
        //ekhane Pending parcel show er kaj korbo

    }

    public void viewMyAssignedParcles(){
            //ekhane file theke all percels ene show korabo;
    }


    public void updateParcelStatus(String newStatus){
        //ekhane file e parcel er status update er kaj korbo

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