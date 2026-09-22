package model;


import java.time.LocalDate;
import java.util.ArrayList;

public class Parcel {
     public enum ParcelStatus{
         PENDING,
         ACCEPTED,
         ON_TRANSIT,
         REACHED_DESTINATION,
         DELIVERED
     }

     private String parcelName;
     private String reciverAddress;
     private String reciverPhone;
     private String parcelID;
     private double weight;
     private String senderEmail;
     private String senderId;
     private String parcelStatus;
     private double deliveryCharge;
     private String riderId;

     private final double  chargePerWeight = 10.00;

     Parcel(String parcelName,String reciverAddress, String reciverPhone, double weigh){
         this.parcelName = parcelName;
         this.reciverAddress = reciverAddress;
         this.reciverPhone = reciverPhone;
         this.weight = weigh;
         this.parcelStatus = String.valueOf(ParcelStatus.PENDING);

         this.parcelID = generateParcelID();
         this.senderId = null;
         this.senderEmail = null;
         this.riderId = null;
         this.deliveryCharge = weight*chargePerWeight;
     }


    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public String getReciverAddress() {
        return reciverAddress;
    }

    public void setReciverAddress(String reciverAddress) {
        this.reciverAddress = reciverAddress;
    }

    public String getReciverPhone() {
        return reciverPhone;
    }

    public void setReciverPhone(String reciverPhone) {
        this.reciverPhone = reciverPhone;
    }

    public String getParcelID() {
        return parcelID;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(String parcelStatus) {
        this.parcelStatus = parcelStatus;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }


     public String sendParcel(User user){

         if(!isUserExists(user)){
             return "Please Before send Parcels!";
         }
         this.senderEmail  = user.getUser_email();
         this.senderId = user.getUser_id();

         //ekhane parcel send er jonno file handle er kaj korbo

         return "";
     }

     public ArrayList<String> getMyAllParcels(User user){
         if(!isUserExists(user)){
             return new ArrayList<>();
         }

         //ekhane all user parcel get korar file handle er kaj korbo;

         return new ArrayList<>();

     }


     public boolean cancelParcel(User user , String ParcelId){

         if(!isUserExists(user)){
             return false;
         }

         //ekhane parcel cancel er business logic hobe

         return false;
     }



     public String trackParcel(User user , String parcelId){
         if(!isUserExists(user)){
             return "Please Login First!";
         }
         //ekhane file e parcel find er kaj korbo;




         return parcelId;
     }


     public String generateParcelID(){
         LocalDate ld = LocalDate.now();
         return  "PARCEL-"+ld.toString();
     }

     private boolean isUserExists(User user){
         if(user.getUser_role().contains("ADMIN")){
             return false;
         }
         else if(user.getUser_role()==null || user.getUser_id()==null){
             return false;
         }

         return true;
     }

}