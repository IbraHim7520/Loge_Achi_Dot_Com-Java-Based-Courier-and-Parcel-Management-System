package model;

import custom_exception.InvalidAmountException;
import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import file.ParcelFile;

import java.time.LocalTime;
import java.util.ArrayList;

public class Parcel {

    public enum ParcelStatus {
        PENDING,
        ACCEPTED,
        ON_TRANSIT,
        REACHED_DESTINATION,
        DELIVERED,
        CANCELED
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

    public Parcel(User user) throws UnauthorizedAccessException {

        if (!validateUser(user)) {
            throw new UnauthorizedAccessException("Unauthorized Access! Invalid user.");
        }

        this.senderEmail = user.getUser_email();
        this.senderId = user.getUser_id();
        this.parcelStatus = String.valueOf(ParcelStatus.PENDING);
        this.parcelID = generateParcelID();
        this.riderId = null;
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

    public void setWeight(double weight) throws InvalidAmountException {

        if (weight <= 0) {
            throw new InvalidAmountException("Weight must be greater than zero!");
        }

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

    public void setDeliveryCharge(double deliveryCharge) throws InvalidAmountException {

        if (deliveryCharge < 0) {
            throw new InvalidAmountException("Delivery charge cannot be negative!");
        }

        this.deliveryCharge = deliveryCharge;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public String sendOneParcel(String parcelName, String reciverAddress, String reciverPhone, double weight) throws InvalidAmountException {

        if (weight <= 0) {
            throw new InvalidAmountException("Weight must be greater than zero!");
        }

        this.parcelName = parcelName;
        this.reciverAddress = reciverAddress;
        this.reciverPhone = reciverPhone;
        this.weight = weight;

        this.parcelID = generateParcelID();
        this.deliveryCharge = weight * chargePerWeight;

        boolean result = ParcelFile.saveParcel(
                getParcelName(),
                getReciverAddress(),
                getReciverPhone(),
                getParcelID(),
                getWeight(),
                getSenderEmail(),
                getSenderId(),
                getParcelStatus(),
                getDeliveryCharge(),
                getRiderId()
        );

        if (result) {
            return "Parcel sent successfully!";
        }
        return "Failed to send parcel!";
    }

    public ArrayList<String> getMyAllParcels() throws NotFoundException {
        return ParcelFile.getMyAllParcels(getSenderId());
    }

    public boolean cancelParcel(String parcelId) throws NotFoundException {
        return ParcelFile.cancelParcel(parcelId, getSenderId());
    }

    public Parcel trackParcel(String parcelId)
            throws NotFoundException,
            UnauthorizedAccessException,
            InvalidAmountException {
        return ParcelFile.trackParcel(parcelId, getSenderId());
    }

    public boolean deleteMyParcel(String parcelID) throws NotFoundException {
        return ParcelFile.deleteParcel(parcelID, getSenderId());
    }

    public String generateParcelID() {
        LocalTime time = LocalTime.now();
        return "P" + time.toString().replace(":", "").replace(".", "");
    }

    private boolean validateUser(User user) {

        if (user == null) {
            return false;
        }

        if (user.getUser_id() == null
                || user.getUser_id().trim().isEmpty()) {
            return false;
        }

        if (user.getUser_role() == null) {
            return false;
        }

        if (user.getUser_role().equals(String.valueOf(User.UserRole.ADMIN))) {
            return false;
        }

        return true;
    }
}