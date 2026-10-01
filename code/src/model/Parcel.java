package model;

import custom_exception.InvalidAmountException;
import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import file.ParcelFile;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

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
    private static final double CHARGE_PER_WEIGHT = 10.00;


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


    public Parcel(String parcelID, String senderId, String parcelName, String receiverAddress, double weight, double deliveryCharge) throws InvalidAmountException {

        if (parcelID == null || parcelID.trim().isEmpty()) {

            throw new IllegalArgumentException("Parcel ID cannot be empty.");
        }

        if (senderId == null || senderId.trim().isEmpty()) {

            throw new IllegalArgumentException("Sender ID cannot be empty.");
        }

        if (parcelName == null || parcelName.trim().isEmpty()) {
            throw new IllegalArgumentException("Parcel name cannot be empty.");
        }

        if (receiverAddress == null || receiverAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("Receiver address cannot be empty.");
        }

        validateWeight(weight);
        validateDeliveryCharge(deliveryCharge);

        this.parcelID = parcelID.trim();
        this.senderId = senderId.trim();
        this.parcelName = parcelName.trim();
        this.reciverAddress = receiverAddress.trim();

        this.weight = weight;
        this.deliveryCharge = deliveryCharge;

        this.parcelStatus =
                String.valueOf(ParcelStatus.PENDING);

        this.riderId = null;
    }

    public String getParcelName() {
        return parcelName;
    }

    public String getReciverAddress() {
        return reciverAddress;
    }

    public String getReciverPhone() {
        return reciverPhone;
    }

    public String getParcelID() {
        return parcelID;
    }

    public double getWeight() {
        return weight;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getParcelStatus() {
        return parcelStatus;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public String getRiderId() {
        return riderId;
    }



    public void setParcelName(String parcelName) {

        if (parcelName == null || parcelName.trim().isEmpty()) {
            return;
        }
        this.parcelName = parcelName.trim();
    }


    public void setReciverAddress(String reciverAddress) {
        if (reciverAddress == null || reciverAddress.trim().isEmpty()) {
            return;
        }
        this.reciverAddress = reciverAddress.trim();
    }


    public void setReciverPhone(String reciverPhone) {
        if (reciverPhone == null || reciverPhone.trim().isEmpty()) {
            return;
        }
        this.reciverPhone = reciverPhone.trim();
    }


    public void setParcelID(String parcelID) {
        if (parcelID == null || parcelID.trim().isEmpty()) {
            return;
        }
        this.parcelID = parcelID.trim();
    }


    public void setWeight(double weight) throws InvalidAmountException {
        validateWeight(weight);
        this.weight = weight;
    }


    public void setSenderEmail(String senderEmail) {

        if (senderEmail == null
                || senderEmail.trim().isEmpty()) {

            return;
        }

        this.senderEmail = senderEmail.trim();
    }


    public void setSenderId(String senderId) {

        if (senderId == null
                || senderId.trim().isEmpty()) {

            return;
        }

        this.senderId = senderId.trim();
    }


    public void setParcelStatus(String parcelStatus) {

        if (parcelStatus == null || parcelStatus.trim().isEmpty()) {

            return;
        }

        String status = parcelStatus.trim().toUpperCase();

        try {

            ParcelStatus.valueOf(status);

            this.parcelStatus = status;

        } catch (IllegalArgumentException e) {

        }
    }


    public void setDeliveryCharge(double deliveryCharge) throws InvalidAmountException {

        validateDeliveryCharge(deliveryCharge);

        this.deliveryCharge = deliveryCharge;
    }


    public void setRiderId(String riderId) {

        if (riderId == null || riderId.trim().isEmpty() || riderId.trim().equalsIgnoreCase("null")) {

            this.riderId = null;
            return;
        }

        this.riderId = riderId.trim();
    }



    public String sendOneParcel(String parcelName, String reciverAddress, String reciverPhone, double weight) throws InvalidAmountException {

        if (parcelName == null || parcelName.trim().isEmpty()) {

            throw new IllegalArgumentException("Parcel name cannot be empty.");
        }

        if (reciverAddress == null || reciverAddress.trim().isEmpty()) {

            throw new IllegalArgumentException("Receiver address cannot be empty.");
        }

        if (reciverPhone == null || reciverPhone.trim().isEmpty()) {

            throw new IllegalArgumentException("Receiver phone cannot be empty.");
        }

        validateWeight(weight);

        this.parcelName = parcelName.trim();
        this.reciverAddress = reciverAddress.trim();
        this.reciverPhone = reciverPhone.trim();
        this.weight = weight;
        this.parcelStatus = String.valueOf(ParcelStatus.PENDING);
        if (this.parcelID == null || this.parcelID.trim().isEmpty()) {
            this.parcelID = generateParcelID();
        }

        this.deliveryCharge = weight * CHARGE_PER_WEIGHT;

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
        LocalTime lt = LocalTime.now();
        Random rnd = new Random();
        int randomInt = rnd.nextInt(999);
        return "P" + (lt.getSecond()*randomInt);
    }



    private boolean validateUser(User user) {

        if (user == null) {
            return false;
        }

        if (user.getUser_id() == null || user.getUser_id().trim().isEmpty()) {
            return false;
        }

        if (user.getUser_role() == null) {
            return false;
        }

        return user.getUser_role().equalsIgnoreCase(String.valueOf(User.UserRole.USER));
    }



    private void validateWeight(double weight) throws InvalidAmountException {

        if (!Double.isFinite(weight) || weight <= 0) {
            throw new InvalidAmountException("Weight must be greater than zero!");
        }
    }



    private void validateDeliveryCharge(
            double deliveryCharge
    ) throws InvalidAmountException {

        if (!Double.isFinite(deliveryCharge)
                || deliveryCharge < 0) {

            throw new InvalidAmountException(
                    "Delivery charge cannot be negative!"
            );
        }
    }
}