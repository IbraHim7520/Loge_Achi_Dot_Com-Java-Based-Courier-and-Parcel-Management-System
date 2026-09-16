package model;


public class Parcel {


    private String parcel_id;
    private String sender_id;
    private String receiver_name;
    private String receiver_phone;
    private String receiver_address;
    private double weight;
    private double delivery_charge;
    private String parcel_status;
    private String rider_id;


    Parcel(String sender_id, String receiver_name, String receiver_phone, String receiver_address, double weight){
        this.sender_id = sender_id;
        this.receiver_name = receiver_name;
        this.receiver_phone = receiver_phone;
        this.receiver_address = receiver_address;
        this.weight = weight;
        this.delivery_charge = calculateDeliveryCharge(weight);
        this.rider_id = null;
        this.parcel_status = "Pending";
    }

    public double calculateDeliveryCharge(double weight){
        return weight * 5.0;
    }
    
    public void updateStatus(){}
    
    public String[] getMyParcels(String sender_id){

        return new String[]{};
    }


    public boolean SendParcel(){

        return true;
    }
    

    public String getParcel_id(){
        return this.parcel_id;
    }
    public String getSender_id(){
        return this.sender_id;
    }
    public String getReceiver_name(){
        return this.receiver_name;
    }
    public String getReceiver_phone(){
        return this.receiver_phone;
    }
    public String getReceiver_address(){
        return this.receiver_address;
    }
    public double getWeight(){
        return this.weight;
    }
    public double getDelivery_charge(){
        return this.delivery_charge;
    }
    public String getParcel_status(){
        return this.parcel_status;
    }
    public String getRider_id(){
        return this.rider_id;
    }

    public void setParcel_id(String parcel_id){
        this.parcel_id= parcel_id;
    }
    public void setSender_id(String sender_id){
        this.sender_id =sender_id;
    }
    public void setReceiver_name(String receiver_name){
        this.receiver_name = receiver_name;
    }
    public void setReceiver_phone(String receiver_phone){
        this.receiver_phone = receiver_phone;
    }
    public void setReceiver_address(String receiver_address){
        this.receiver_address =receiver_address;
    }
    public void setWeight(double weight){
        this.weight=weight;
    }
    public void setDelivery_charge(double delivery_charge){
        this.delivery_charge = delivery_charge;
    }
    public void setParcel_status(String parcel_status){
        this.parcel_status=parcel_status;
    }
    public void setRider_id(String rider_id){
        this.rider_id=rider_id;
    }
}
