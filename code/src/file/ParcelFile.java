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
            if (riderId == null) {
                riderId = "null";
            }

            String newParcel =
                    parcelName + "-"
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

            FileWriter writer =
                    new FileWriter(PARCEL_FILE, true);

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


    // Get all parcels of a specific sender
    public static ArrayList<String> getMyAllParcels(
            String senderId
    ) {

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

                // Remove # from the end of the line
                parcelData = parcelData.replace("#", "");

                // Check sender ID
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
    public static String findParcel(String parcelId) {

        File fl = new File(PARCEL_FILE);

        if (!fl.exists()) {
            return null;
        }

        try {

            Scanner sc = new Scanner(fl);

            while (sc.hasNextLine()) {

                String parcelData =
                        sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                parcelData =
                        parcelData.replace("#", "");

                if (parcelData.contains(parcelId)) {

                    sc.close();

                    return parcelData;
                }
            }

            sc.close();

        } catch (Exception e) {

            System.out.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // Cancel a parcel
    public static boolean cancelParcel(
            String parcelId,
            String senderId
    ) {

        File fl = new File(PARCEL_FILE);

        if (!fl.exists()) {
            return false;
        }
        ArrayList<String> allParcels = new ArrayList<>();

        boolean cancelled = false;

        try {

            Scanner sc = new Scanner(fl);

            while (sc.hasNextLine()) {

                String parcelData = sc.nextLine().trim();
                if (parcelData.isEmpty()) {
                    continue;
                }
                if (parcelData.contains(parcelId) && parcelData.contains(senderId) && parcelData.contains(String.valueOf(Parcel.ParcelStatus.PENDING))) {
                    parcelData = parcelData.replace(String.valueOf(Parcel.ParcelStatus.PENDING), String.valueOf(Parcel.ParcelStatus.CANCELED));
                    cancelled = true;
                }
                allParcels.add(parcelData);
            }

            sc.close();

            FileWriter writer = new FileWriter(PARCEL_FILE, false);

            for (String parcel : allParcels) {
                writer.write(parcel + "#\n");
            }

            writer.close();

            return cancelled;

        } catch (Exception e) {

            System.out.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // Track a parcel
    public static Parcel trackParcel(
            String parcelId,
            String senderId
    ) {

        // Not implemented yet

        return null;
    }



    // Update parcel status
    public static boolean updateParcelStatus(
            String parcelId,
            String newStatus
    ) {

        // Not implemented yet

        return false;
    }


    // Delete a parcel
    public static boolean deleteParcel(String parcelId, String userId) {
        File file = new File(PARCEL_FILE);
        if (!file.exists()) {
            return false;
        }
        ArrayList<String> parcels = new ArrayList<>();
        boolean deleted = false;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String parcelData = sc.nextLine().trim();
                if (parcelData.isEmpty()) {
                    continue;
                }
                String cleanData = parcelData.replace("#", "");
                if (cleanData.contains(parcelId) && cleanData.contains(userId)) {
                    if (cleanData.contains(String.valueOf(Parcel.ParcelStatus.PENDING)) || cleanData.contains(String.valueOf(Parcel.ParcelStatus.CANCELED))) {
                        deleted = true;
                        continue;
                    }
                }
                parcels.add(cleanData);
            }
            sc.close();
            if (deleted) {
                FileWriter writer = new FileWriter(file, false);

                for (String parcel : parcels) {
                    writer.write(parcel + "#\n");
                }

                writer.close();
            }
            return deleted;
        } catch (Exception e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
            return false;
        }
    }


    // Search parcel
    public static Parcel searchParcel(
            String parcelId
    ) {

        // Not implemented yet

        return null;
    }
}