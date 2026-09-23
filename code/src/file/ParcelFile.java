package file;

import model.Parcel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParcelFile {

    private static final String PARCEL_FILE = "code/src/database/parcel_db.txt";


    // Save a new parcel into the file
    public static boolean saveParcel(
            String parcelName,
            String receiverAddress,
            String receiverPhone,
            String parcelID,
            double weight,
            String senderEmail,
            String senderId,
            String parcelStatus,
            double deliveryCharge,
            String riderId
    ) {
        File file = new File(PARCEL_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            // If rider is not assigned yet

            String newParcel = parcelName + "-"
                            + receiverAddress + "-"
                            + receiverPhone + "-"
                            + parcelID + "-"
                            + weight + "-"
                            + senderEmail + "-"
                            + senderId + "-"
                            + parcelStatus + "-"
                            + deliveryCharge + "-"
                            + riderId
                            + "#\n";

            FileWriter writer = new FileWriter(PARCEL_FILE, true);
            writer.write(newParcel);
            writer.close();
            return true;

        } catch (IOException e) {

            System.out.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );

            return false;
        }
    }


//    // Get all parcels from the file
//    public static ArrayList<Parcel> getAllParcels() {
//        // ekhane file theke all parcels read korbo
//
//    }


    // Get all parcels of a specific sender
    public static ArrayList<String> getMyAllParcels(String senderId) {

        ArrayList<String> arr = new ArrayList<>();

        File fl = new File(PARCEL_FILE);

        if (!fl.exists()) {
            return arr;
        }

        try {

            Scanner sc = new Scanner(fl);

            while (sc.hasNextLine()) {

                String parcelData = sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                parcelData = parcelData.replace("#", "");

                if (parcelData.contains(senderId)) {
                    arr.add(parcelData);
                }
            }

            sc.close();

        } catch (Exception e) {

            System.out.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );
        }

        return arr;
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