package file;

import model.Parcel;

import java.util.ArrayList;

public class ParcelFile {

    private static final String PARCEL_FILE =
            "code/src/database/parcel_db.txt";


    // Save a new parcel into the file
    public static boolean saveParcel(Parcel parcel) {
        // ekhane parcel file e save korar business logic korbo

        return false;
    }


    // Get all parcels from the file
    public static ArrayList<Parcel> getAllParcels() {
        // ekhane file theke all parcels read korbo

        return new ArrayList<>();
    }


    // Get all parcels of a specific sender
    public static ArrayList<Parcel> getMyAllParcels(String senderId) {
        // ekhane senderId diye
        // oi user er all parcels file theke ber korbo

        return new ArrayList<>();
    }


    // Find a parcel by Parcel ID
    public static Parcel findParcel(String parcelId) {
        // ekhane parcelId diye file e parcel search korbo

        return null;
    }


    // Cancel a parcel
    public static boolean cancelParcel(String parcelId, String senderId) {
        // ekhane parcelId diye parcel khujbo
        // senderId diye verify korbo je parcel ta oi user er kina
        // tarpor parcel cancel korar business logic korbo

        return false;
    }


    // Track a parcel
    public static Parcel trackParcel(String parcelId, String senderId) {
        // ekhane parcelId diye parcel khujbo
        // senderId diye verify korbo
        // tarpor parcel er current status return korbo

        return null;
    }


    // Update parcel status
    public static boolean updateParcelStatus(
            String parcelId,
            String newStatus) {

        // ekhane parcelId diye parcel khujbo
        // tarpor parcel er status update korbo

        return false;
    }


    // Delete a parcel
    public static boolean deleteParcel(String parcelId) {
        // ekhane parcelId diye parcel khujbo
        // tarpor file theke parcel delete korbo

        return false;
    }


    // Search parcel
    public static Parcel searchParcel(String parcelId) {
        // ekhane parcelId diye parcel search korbo

        return null;
    }
}