package model;
import model.Parcel;


public class Rider {
    private String rider_id;
    private String rider_name;
    private String rider_phone;
    private String rider_address;
    private String rider_status;

    public Rider(String rider_id, String rider_name, String rider_phone, String rider_address){
        this.rider_id = rider_id;
        this.rider_name = rider_name;

        this.rider_phone= rider_phone;
        this.rider_address = rider_address;

        this.rider_status = "Available";
    }


    public String getRider_id() {
        return rider_id;
    }
    public String getRider_name() {
        return rider_name;
    }
    public String getRider_phone(){
        return rider_phone;
    }
    public String getRider_address() {
        return rider_address;
    }
    public String getRider_status() {
        return rider_status;
    }


    public void setRider_name(String rider_name){
        this.rider_name = rider_name;
    }
    public void setRider_phone(String rider_phone){
        this.rider_phone = rider_phone;
    }
    public void setRider_address(String rider_address) {
        this.rider_address = rider_address;
    }
    public void setRider_status(String rider_status) {
        this.rider_status = rider_status;
    }


    public boolean acceptParcel(Parcel parcel){
        if(rider_status.equals("Available")){
            parcel.setParcel_status("Picked up");
            rider_status = "Busy";
            return true;
        }
        return false;
    }

    public boolean updateParcelStatus(Parcel parcel, String status){
        parcel.setParcel_status(status);
        return true;
    }

    public boolean deliverParcel(Parcel parcel){
        parcel.setParcel_status("Delivered");
        rider_status = "Available";
        return true;
    }

    public boolean updateRiderStatus(String status){
        if(status.equals("Available")||status.equals("Busy")||status.equals("offline")){
            rider_status = status;
            return true;
        }
        return false;
    }
}
