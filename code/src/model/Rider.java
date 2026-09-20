package model;

import file.RiderFile;
import file.ParcelFile;

import java.util.ArrayList;

public class Rider {

    private String rider_id;
    private String rider_name;
    private String rider_phone;
    private String rider_address;
    private String rider_status;

    public Rider(String rider_id, String rider_name, String rider_phone, String rider_address) {
        this.rider_id = rider_id;
        this.rider_name = rider_name;
        this.rider_phone = rider_phone;
        this.rider_address = rider_address;
        this.rider_status = "Available";
    }

    public String getRider_id() {
        return rider_id;
    }

    public String getRider_name() {
        return rider_name;
    }

    public String getRider_phone() {
        return rider_phone;
    }

    public String getRider_address() {
        return rider_address;
    }

    public String getRider_status() {
        return rider_status;
    }

    public void setRider_name(String rider_name) {
        this.rider_name = rider_name;
    }

    public void setRider_phone(String rider_phone) {
        this.rider_phone = rider_phone;
    }

    public void setRider_address(String rider_address) {
        this.rider_address = rider_address;
    }

    public void setRider_status(String rider_status) {
        this.rider_status = rider_status;
    }


    public boolean acceptParcel(Parcel parcel) {

        if (parcel == null) {
            return false;
        }

        if (!rider_status.equals("Available")) {
            return false;
        }

        if (!rider_id.equals(parcel.getRider_id())) {
            return false;
        }

        parcel.setParcel_status("Picked Up");
        rider_status = "Busy";

        ParcelFile parcelFile = new ParcelFile();
        RiderFile riderFile = new RiderFile();

        boolean parcelUpdated = parcelFile.updateParcel(parcel);
        boolean riderUpdated = riderFile.updateRider(this);

        return parcelUpdated && riderUpdated;
    }


    public boolean updateParcelStatus(Parcel parcel, String status) {

        if (parcel == null || status == null || status.isEmpty()) {
            return false;
        }

        if (!rider_id.equals(parcel.getRider_id())) {
            return false;
        }

        if (!status.equals("Picked Up")
                && !status.equals("In Transit")
                && !status.equals("Delivered")) {
            return false;
        }

        parcel.setParcel_status(status);

        ParcelFile parcelFile = new ParcelFile();

        return parcelFile.updateParcel(parcel);
    }


    public boolean deliverParcel(Parcel parcel) {

        if (parcel == null) {
            return false;
        }

        if (!rider_id.equals(parcel.getRider_id())) {
            return false;
        }

        if (!rider_status.equals("Busy")) {
            return false;
        }

        parcel.setParcel_status("Delivered");
        rider_status = "Available";

        ParcelFile parcelFile = new ParcelFile();
        RiderFile riderFile = new RiderFile();

        boolean parcelUpdated = parcelFile.updateParcel(parcel);
        boolean riderUpdated = riderFile.updateRider(this);

        return parcelUpdated && riderUpdated;
    }


    public boolean updateRiderStatus(String status) {

        if (status == null || status.isEmpty()) {
            return false;
        }

        if (!status.equals("Available")
                && !status.equals("Busy")
                && !status.equals("Offline")) {
            return false;
        }

        rider_status = status;

        RiderFile riderFile = new RiderFile();

        return riderFile.updateRider(this);
    }


    public ArrayList<Parcel> getAssignedParcels() {

        ParcelFile parcelFile = new ParcelFile();

        return parcelFile.getAssignedParcels(rider_id);
    }
}