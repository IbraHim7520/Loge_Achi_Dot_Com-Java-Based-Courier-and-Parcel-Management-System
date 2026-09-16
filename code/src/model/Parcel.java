
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


    public Parcel(String sender_id,
                  String receiver_name,
                  String receiver_phone,
                  String receiver_address,
                  double weight) {

        this.sender_id = sender_id;
        this.receiver_name = receiver_name;
        this.receiver_phone = receiver_phone;
        this.receiver_address = receiver_address;
        this.weight = weight;

        this.delivery_charge = calculateDeliveryCharge(weight);

        this.rider_id = null;
        this.parcel_status = "Pending";
    }


    public double calculateDeliveryCharge(double weight) {
        return weight * 5.0;
    }


    // Send Parcel
    public boolean sendParcel() {

        if (sender_id == null || sender_id.isEmpty()) {
            return false;
        }

        if (receiver_name == null || receiver_name.isEmpty()) {
            return false;
        }

        if (receiver_phone == null || receiver_phone.isEmpty()) {
            return false;
        }

        if (receiver_address == null || receiver_address.isEmpty()) {
            return false;
        }

        if (weight <= 0) {
            return false;
        }

        this.parcel_status = "Pending";

        return true;
    }


    // Get all parcels sent by a specific user
    public String[] getMyParcels(String sender_id) {


        return new String[]{};
    }


    // Assign Rider
    public void assignRider(String rider_id) {
        
        this.rider_id = rider_id;
        this.parcel_status = "Assigned";
    }


    // Update Parcel Status
    public void updateStatus(String status) {

        this.parcel_status = status;
    }


    // Getters

    public String getParcel_id() {
        return parcel_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public double getWeight() {
        return weight;
    }

    public double getDelivery_charge() {
        return delivery_charge;
    }

    public String getParcel_status() {
        return parcel_status;
    }

    public String getRider_id() {
        return rider_id;
    }


    // Setters

    public void setParcel_id(String parcel_id) {
        this.parcel_id = parcel_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public void setWeight(double weight) {

        this.weight = weight;
        this.delivery_charge = calculateDeliveryCharge(weight);
    }

    public void setDelivery_charge(double delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public void setParcel_status(String parcel_status) {
        this.parcel_status = parcel_status;
    }

    public void setRider_id(String rider_id) {
        this.rider_id = rider_id;
    }
}

