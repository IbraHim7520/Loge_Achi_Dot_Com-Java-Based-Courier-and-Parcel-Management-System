package file;

import custom_exception.NotFoundException;
import model.Parcel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RiderFile {

    private static final String PARCEL_FILE =
            "code/src/database/parcel_db.txt";

    public static ArrayList<String> getPendingParcels() throws NotFoundException {

        ArrayList<String> pendingParcels = new ArrayList<>();
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            throw new NotFoundException("Parcel database not found!");
        }

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String parcelData = sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                parcelData = parcelData.replace("#", "");

                String[] parcel = parcelData.split("-");

                if (parcel.length >= 10
                        && parcel[7].equals(
                        String.valueOf(Parcel.ParcelStatus.PENDING))) {

                    pendingParcels.add(parcelData);
                }
            }

        } catch (IOException e) {
            System.out.println("Exception from RiderFile: " + e.getMessage());
        }

        if (pendingParcels.isEmpty()) {
            throw new NotFoundException("No pending parcels found!");
        }

        return pendingParcels;
    }

    public static ArrayList<String> getMyAssignedParcels(String riderId) throws NotFoundException {

        ArrayList<String> assignedParcels = new ArrayList<>();
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            throw new NotFoundException("Parcel database not found!");
        }

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String parcelData = sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                parcelData = parcelData.replace("#", "");

                String[] parcel = parcelData.split("-");

                if (parcel.length >= 10 && parcel[9].equals(riderId)) {
                    assignedParcels.add(parcelData);
                }
            }

        } catch (IOException e) {
            System.out.println("Exception from RiderFile: " + e.getMessage());
        }

        if (assignedParcels.isEmpty()) {
            throw new NotFoundException("No assigned parcels found!");
        }
        return assignedParcels;
    }

    public static boolean assignParcel(String parcelId, String riderId) throws NotFoundException {

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            throw new NotFoundException("Parcel database not found!");
        }

        ArrayList<String> parcels = new ArrayList<>();
        boolean assigned = false;

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String parcelData = sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                parcelData = parcelData.replace("#", "");

                String[] arr = parcelData.split("-");

                if (arr.length >= 10
                        && arr[3].equals(parcelId)
                        && arr[7].equals(
                        String.valueOf(Parcel.ParcelStatus.PENDING))) {

                    arr[7] = String.valueOf(
                            Parcel.ParcelStatus.ACCEPTED
                    );

                    arr[9] = riderId;

                    parcelData = String.join("-", arr);

                    assigned = true;
                }

                parcels.add(parcelData);
            }

        } catch (IOException e) {
            System.out.println("Exception from RiderFile: " + e.getMessage());
        }

        if (!assigned) {
            throw new NotFoundException("Parcel not found or parcel is not pending!");
        }

        try (FileWriter writer = new FileWriter(PARCEL_FILE, false)) {

            for (String parcel : parcels) {
                writer.write(parcel + "#\n");
            }

        } catch (IOException e) {
            System.out.println("Exception from RiderFile: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean updateParcelStatusByRider(String parcelId, String riderId, String newStatus) throws NotFoundException {

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            throw new NotFoundException("Parcel database not found!");
        }

        try {
            Parcel.ParcelStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Invalid parcel status!");
        }

        ArrayList<String> parcels = new ArrayList<>();
        boolean updated = false;

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String parcelData = sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                parcelData = parcelData.replace("#", "");

                String[] arr = parcelData.split("-");

                if (arr.length >= 10 && arr[3].equals(parcelId) && arr[9].equals(riderId)) {

                    arr[7] = newStatus.toUpperCase();

                    parcelData = String.join("-", arr);

                    updated = true;
                }

                parcels.add(parcelData);
            }

        } catch (IOException e) {
            System.out.println("Exception from RiderFile: " + e.getMessage());
        }

        if (!updated) {
            throw new NotFoundException("Parcel not found or parcel is not assigned to this rider!");
        }

        try {
            FileWriter writer = new FileWriter(PARCEL_FILE, false);
            for (String parcel : parcels) {
                    writer.write(parcel + "#\n");
                }
        } catch (IOException e) {
            System.out.println("Exception from RiderFile: " + e.getMessage());
            return false;
        }

        return true;
    }
}