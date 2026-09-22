package model;

public class Rider {

        User user;
    Rider(User user){
        if(!isUserExits(user)){
            System.out.println("Unauthorized Access!");
            this.user = null;
        }else {
            this.user = user;
        }

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

    private boolean isUserExits(User user){
        if(user.getUser_id()==null){
            return false;
        }else if(!user.getUser_role().contains("RIDER")){
            return false;
        }
        return true;
    }
}