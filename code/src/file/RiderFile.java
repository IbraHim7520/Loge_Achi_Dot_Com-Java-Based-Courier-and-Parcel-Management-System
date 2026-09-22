package file;

import model.Parcel;
import model.User;

import java.util.ArrayList;

public class RiderFile {

    private static final String RIDER_FILE =
            "code/src/database/users_db.txt";


    // Find a rider by Rider/User ID
    public static User findRider(String riderId) {
        // ekhane users_db.txt file theke
        // riderId diye rider search korbo

        return null;
    }


    // Get all registered riders
    public static ArrayList<User> getAllRiders() {
        // ekhane users_db.txt file theke
        // all users read kore sudhu RIDER der ber korbo

        return new ArrayList<>();
    }


    // Get all pending parcels
    public static ArrayList<Parcel> getPendingParcels() {
        // ekhane parcel file theke
        // all parcels read korbo
        // tarpor sudhu PENDING parcels return korbo

        return new ArrayList<>();
    }


    // Get parcels assigned to a specific rider
    public static ArrayList<Parcel> getMyAssignedParcels(String riderId) {
        // ekhane parcel file theke
        // riderId diye assigned parcels ber korbo

        return new ArrayList<>();
    }


    // Assign a parcel to a rider
    public static boolean assignParcel(
            String parcelId,
            String riderId) {

        // ekhane parcelId diye parcel khujbo
        // riderId diye rider verify korbo
        // tarpor parcel er riderId set korbo

        return false;
    }


    // Update parcel status by rider
    public static boolean updateParcelStatus(
            String parcelId,
            String riderId,
            String newStatus) {

        // ekhane parcelId diye parcel khujbo
        // riderId diye verify korbo je parcel ta
        // oi rider er assigned kina
        // tarpor parcel status update korbo

        return false;
    }


    // Search a parcel assigned to rider
    public static Parcel searchParcel(
            String parcelId,
            String riderId) {

        // ekhane parcelId diye parcel search korbo
        // riderId diye verify korbo je parcel ta
        // oi rider er assigned kina

        return null;
    }


    // Check whether a user is a rider
    public static boolean isRider(String userId) {
        // ekhane users_db.txt file theke
        // userId diye user khujbo
        // user er role RIDER kina check korbo

        return false;
    }
}