package file;

import model.Parcel;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RiderFile {

    private static final String RIDER_FILE = "code/src/database/users_db.txt";
    private static final String PARCEL_FILE = "code/src/database/parcel_db.txt";


    // Find a rider by Rider/User ID
    public static User findRider(String riderId) {
        // ekhane users_db.txt file theke
        // riderId diye rider search korbo

        return null;
    }


    // Get all registered riders


    // Get all pending parcels
    public static ArrayList<String> getPendingParcels() {
        ArrayList<String> pendingParcels = new ArrayList<String>();
        File file = new File(PARCEL_FILE);
        if (!file.exists()) {
            return pendingParcels;
        }
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String parcelData = sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }
                parcelData = parcelData.replace("#", "");

                if (parcelData.contains(String.valueOf(Parcel.ParcelStatus.PENDING))) {
                    pendingParcels.add(parcelData);
                }
            }
            sc.close();
            return pendingParcels;

        } catch (Exception e) {
            System.out.println("Exception from Rider File " + e.getMessage());
        }
        return new ArrayList<>();
    }


    // Get parcels assigned to a specific rider
    public static ArrayList<String> getMyAssignedParcels(String riderId) {

        ArrayList<String> assignedParcels = new ArrayList<>();
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            return assignedParcels;
        }
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String parcelData = sc.nextLine().trim();
                if (parcelData.isEmpty()) {
                    continue;
                }
                parcelData = parcelData.replace("#", "");
                if (parcelData.contains(riderId)) {
                    assignedParcels.add(parcelData);
                }
            }
            sc.close();
            return assignedParcels;
        } catch (Exception e) {
            System.out.println("Exception from Rider File " + e.getMessage());
        }
        return new ArrayList<>();
    }


    // Assign a parcel to a rider
    public static boolean assignParcel(String parcelId, String riderId) {

        File file = new File(PARCEL_FILE);
        if (!file.exists()) {
            return false;
        }
        ArrayList<String> parcels = new ArrayList<>();
        boolean assigned = false;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String parcelData = sc.nextLine().trim();
                if (parcelData.isEmpty()) {
                    continue;
                }
                parcelData = parcelData.replace("#", "");
                if (parcelData.contains(parcelId)
                        && parcelData.contains(String.valueOf(Parcel.ParcelStatus.PENDING))) {
                    parcelData = parcelData.replace(
                            String.valueOf(Parcel.ParcelStatus.PENDING),
                            String.valueOf(Parcel.ParcelStatus.ACCEPTED)
                    );
                    String[] arr = parcelData.split("-");
                    arr[9] = riderId;
                    parcelData = String.join("-", arr);
                    assigned = true;
                }
                parcels.add(parcelData);
            }
            sc.close();
            if (assigned) {
                FileWriter writer = new FileWriter(PARCEL_FILE, false);
                for (String parcel : parcels) {
                    writer.write(parcel + "#\n");
                }
                writer.close();
            }
            return assigned;
        } catch (Exception e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }
        return false;
    }


    // Update parcel status by rider
    public static boolean updateParcelStatusByRider(String parcelId, String riderId, String newStatus) {

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            return false;
        }

        ArrayList<String> parcels = new ArrayList<>();
        boolean updated = false;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String parcelData = sc.nextLine().trim();
                if (parcelData.isEmpty()) {
                    continue;
                }
                parcelData = parcelData.replace("#", "");
                if (parcelData.contains(parcelId)
                        && parcelData.contains(riderId)) {
                    String[] arr = parcelData.split("-");
                    arr[7] = newStatus;
                    parcelData = String.join("-", arr);
                    updated = true;
                }
                parcels.add(parcelData);
            }
            sc.close();
            if (updated) {
                FileWriter writer = new FileWriter(PARCEL_FILE, false);
                for (String parcel : parcels) {
                    writer.write(parcel + "#\n");
                }
                writer.close();
            }
            return updated;
        } catch (Exception e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }
        return false;
    }

    // Search a parcel assigned to rider
//    public static Parcel searchParcel(
//            String parcelId,
//            String riderId) {
//
//        // ekhane parcelId diye parcel search korbo
//        // riderId diye verify korbo je parcel ta
//        // oi rider er assigned kina
//
//        return null;
//    }


//    // Check whether a user is a rider
//    public static boolean isRider(String userId) {
//        // ekhane users_db.txt file theke
//        // userId diye user khujbo
//        // user er role RIDER kina check korbo
//
//        return false;
//    }
}