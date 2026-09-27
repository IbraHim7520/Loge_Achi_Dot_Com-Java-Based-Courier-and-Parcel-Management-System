package file;

import custom_exception.InvalidAmountException;
import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import model.Parcel;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParcelFile {

    private static final String PARCEL_FILE = "code/src/database/parcel_db.txt";

    public static boolean saveParcel(String parcelName, String receiverAddress, String receiverPhone, String parcelID, double weight, String senderEmail, String senderId, String parcelStatus, double deliveryCharge, String riderId) {

        File file = new File(PARCEL_FILE);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            if (riderId == null) {
                riderId = "null";
            }

            String newParcel = parcelName + "-" + receiverAddress + "-" + receiverPhone + "-" + parcelID + "-" + weight + "-" + senderEmail + "-" + senderId + "-" + parcelStatus + "-" + deliveryCharge + "-" + riderId + "#\n";

            try {
                FileWriter writer = new FileWriter(PARCEL_FILE, true);

                writer.write(newParcel);
                writer.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            return true;

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<String> getMyAllParcels(String senderId) throws NotFoundException {

        ArrayList<String> arr = new ArrayList<>();
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

                if (parcel.length >= 10 && parcel[6].equals(senderId)) {
                    arr.add(parcelData);
                }
            }

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        if (arr.isEmpty()) {
            throw new NotFoundException("No parcels found for this user!");
        }

        return arr;
    }

    public static String findParcel(String parcelId) throws NotFoundException {

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

                if (parcel.length >= 10 && parcel[3].equals(parcelId)) {
                    return parcelData;
                }
            }

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        throw new NotFoundException("Parcel not found!");
    }

    public static boolean cancelParcel(String parcelId, String senderId) throws NotFoundException {

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            throw new NotFoundException("Parcel database not found!");
        }

        ArrayList<String> allParcels = new ArrayList<>();
        boolean cancelled = false;

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String parcelData = sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                parcelData = parcelData.replace("#", "");

                String[] parcel = parcelData.split("-");

                if (parcel.length >= 10 && parcel[3].equals(parcelId) && parcel[6].equals(senderId) && parcel[7].equals(String.valueOf(Parcel.ParcelStatus.PENDING))) {

                    parcel[7] = String.valueOf(Parcel.ParcelStatus.CANCELED);

                    parcelData = String.join("-", parcel);
                    cancelled = true;
                }

                allParcels.add(parcelData);
            }

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        if (!cancelled) {
            throw new NotFoundException("Parcel not found or cannot be canceled!");
        }

        try (FileWriter writer = new FileWriter(PARCEL_FILE, false)) {

            for (String parcel : allParcels) {
                writer.write(parcel + "#\n");
            }

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static Parcel trackParcel(String parcelId, String senderId) throws NotFoundException, UnauthorizedAccessException, InvalidAmountException {

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            throw new NotFoundException("Parcel database not found!");
        }

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String data = sc.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                data = data.replace("#", "");

                String[] parcelData = data.split("-");

                if (parcelData.length >= 10 && parcelData[3].equals(parcelId) && parcelData[6].equals(senderId)) {

                    User user = new User("", parcelData[5], "");

                    user.setUser_id(parcelData[6]);
                    user.setUser_role(String.valueOf(User.UserRole.USER));

                    Parcel parcel = new Parcel(user);

                    parcel.setParcelName(parcelData[0]);
                    parcel.setReciverAddress(parcelData[1]);
                    parcel.setReciverPhone(parcelData[2]);
                    parcel.setParcelID(parcelData[3]);

                    parcel.setWeight(Double.parseDouble(parcelData[4]));

                    parcel.setSenderEmail(parcelData[5]);
                    parcel.setSenderId(parcelData[6]);
                    parcel.setParcelStatus(parcelData[7]);

                    parcel.setDeliveryCharge(Double.parseDouble(parcelData[8]));

                    if (parcelData[9].equals("null")) {
                        parcel.setRiderId(null);
                    } else {
                        parcel.setRiderId(parcelData[9]);
                    }

                    return parcel;
                }
            }

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        throw new NotFoundException("Parcel not found for this user!");
    }

    public static boolean updateParcelStatus(String parcelId, String newStatus) throws NotFoundException {

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

                String data = sc.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }
                data = data.replace("#", "");

                String[] parcelData = data.split("-");

                if (parcelData.length >= 10 && parcelData[3].equals(parcelId)) {
                    parcelData[7] = newStatus.toUpperCase();
                    data = String.join("-", parcelData);
                    updated = true;
                }

                parcels.add(data);
            }

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        if (!updated) {
            throw new NotFoundException("Parcel not found!");
        }

        try (FileWriter writer = new FileWriter(PARCEL_FILE, false)) {

            for (String parcel : parcels) {
                writer.write(parcel + "#\n");
            }

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean deleteParcel(String parcelId, String userId) throws NotFoundException {

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            throw new NotFoundException("Parcel database not found!");
        }

        ArrayList<String> parcels = new ArrayList<>();
        boolean deleted = false;

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String parcelData = sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }
                String cleanData = parcelData.replace("#", "");

                String[] parcel = cleanData.split("-");

                if (parcel.length >= 10 && parcel[3].equals(parcelId) && parcel[6].equals(userId)) {
                    if (parcel[7].equals(String.valueOf(Parcel.ParcelStatus.PENDING)) || parcel[7].equals(String.valueOf(Parcel.ParcelStatus.CANCELED))) {
                        deleted = true;
                        continue;
                    }
                }

                parcels.add(cleanData);
            }

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        if (!deleted) {
            throw new NotFoundException("Parcel not found or cannot be deleted!");
        }

        try (FileWriter writer = new FileWriter(PARCEL_FILE, false)) {

            for (String parcel : parcels) {
                writer.write(parcel + "#\n");
            }

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static Parcel searchParcel(String parcelId) throws NotFoundException, UnauthorizedAccessException, InvalidAmountException {

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            throw new NotFoundException("Parcel database not found!");
        }

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String data = sc.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                data = data.replace("#", "");

                String[] parcelData = data.split("-");

                if (parcelData.length >= 10
                        && parcelData[3].equals(parcelId)) {

                    User user = new User("", parcelData[5], "");

                    user.setUser_id(parcelData[6]);
                    user.setUser_role(String.valueOf(User.UserRole.USER));

                    Parcel parcel = new Parcel(user);

                    parcel.setParcelName(parcelData[0]);
                    parcel.setReciverAddress(parcelData[1]);
                    parcel.setReciverPhone(parcelData[2]);
                    parcel.setParcelID(parcelData[3]);

                    parcel.setWeight(Double.parseDouble(parcelData[4]));

                    parcel.setSenderEmail(parcelData[5]);
                    parcel.setSenderId(parcelData[6]);
                    parcel.setParcelStatus(parcelData[7]);

                    parcel.setDeliveryCharge(Double.parseDouble(parcelData[8]));

                    if (parcelData[9].equals("null")) {
                        parcel.setRiderId(null);
                    } else {
                        parcel.setRiderId(parcelData[9]);
                    }

                    return parcel;
                }
            }

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        throw new NotFoundException("Parcel not found!");
    }
}