package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Parcel {

    public enum ParcelStatus {
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

    private final double chargePerWeight = 10.00;

    // Constructor: User validate korbe
    public Parcel(User user) {

        if (!validateUser(user)) {
            throw new IllegalArgumentException("Unauthorized Access!");
        }

        this.senderEmail = user.getUser_email();
        this.senderId = user.getUser_id();

        this.parcelStatus = String.valueOf(ParcelStatus.PENDING);
        this.parcelID = generateParcelID();

        this.riderId = null;
    }


    //Getter and Setter
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


    // Parcel details niye parcel send korbe
    public String sendOneParcel(String parcelName, String reciverAddress, String reciverPhone, double weight) {

        this.parcelName = parcelName;
        this.reciverAddress = reciverAddress;
        this.reciverPhone = reciverPhone;
        this.weight = weight;

        this.deliveryCharge = weight * chargePerWeight;

        // ekhane parcel send korar jonno
        // file handling er kaj korbo
        //parcel send success hole sender email and id set korbo

        return "Parcel sent successfully!";
    }


    public ArrayList<String> getMyAllParcels() {

        // ekhane senderId use kore
        // all user parcels file theke get korbo

        return new ArrayList<>();
    }


    public boolean cancelParcel(String parcelId) {

        // ekhane parcel ID diye
        // parcel cancel er business logic hobe

        return false;
    }


    public String trackParcel(String parcelId) {

        // ekhane file e parcel find korbo
        // senderId diye user verify kora jabe

        return parcelId;
    }


    public String generateParcelID() {
        LocalDate ld = LocalDate.now();
        return "PARCEL-" + ld.toString();
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

        // Admin parcel send korte parbe na
        if (user.getUser_role().contains("ADMIN")) {
            return false;
        }

        return true;
    }



}