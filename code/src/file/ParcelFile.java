package file;

import model.Parcel;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParcelFile {
    private static final String PARCEL_FILE = "code/src/database/parcel_db.txt";

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

            if (riderId == null) {
                riderId = "null";
            }

            String newParcel = parcelName + "-"
                    + receiverAddress + "-"
                    + receiverPhone + "-"
                    + parcelID + "-"
                    + weight + "-"
                    + senderEmail + "-"
                    + senderId + "-"
                    + parcelStatus + "-"
                    + deliveryCharge + "-"
                    + riderId + "#\n";

            FileWriter writer = new FileWriter(PARCEL_FILE, true);
            writer.write(newParcel);
            writer.close();

            return true;

        } catch (IOException e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<String> getMyAllParcels(String senderId) {
        ArrayList<String> arr = new ArrayList<>();
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            return arr;
        }

        try {
            Scanner sc = new Scanner(file);

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

            sc.close();

        } catch (Exception e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        return arr;
    }

    public static String findParcel(String parcelId) {
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            return null;
        }

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String parcelData = sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                parcelData = parcelData.replace("#", "");
                String[] parcel = parcelData.split("-");

                if (parcel.length >= 10 && parcel[3].equals(parcelId)) {
                    sc.close();
                    return parcelData;
                }
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        return null;
    }

    public static boolean cancelParcel(String parcelId, String senderId) {
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            return false;
        }

        ArrayList<String> allParcels = new ArrayList<>();
        boolean cancelled = false;

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String parcelData = sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                parcelData = parcelData.replace("#", "");
                String[] parcel = parcelData.split("-");

                if (parcel.length >= 10
                        && parcel[3].equals(parcelId)
                        && parcel[6].equals(senderId)
                        && parcel[7].equals(String.valueOf(Parcel.ParcelStatus.PENDING))) {

                    parcel[7] = String.valueOf(Parcel.ParcelStatus.CANCELED);
                    parcelData = String.join("-", parcel);
                    cancelled = true;
                }

                allParcels.add(parcelData);
            }

            sc.close();

            if (!cancelled) {
                return false;
            }

            FileWriter writer = new FileWriter(PARCEL_FILE, false);

            for (String parcel : allParcels) {
                writer.write(parcel + "#\n");
            }

            writer.close();
            return true;

        } catch (Exception e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
            return false;
        }
    }

    public static Parcel trackParcel(String parcelId, String senderId) {
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            return null;
        }

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String data = sc.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                data = data.replace("#", "");
                String[] parcelData = data.split("-");

                if (parcelData.length >= 10
                        && parcelData[3].equals(parcelId)
                        && parcelData[6].equals(senderId)) {

                    User user = new User("", parcelData[5], "");
                    user.setUser_id(parcelData[6]);

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

                    sc.close();
                    return parcel;
                }
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        return null;
    }

    public static boolean updateParcelStatus(String parcelId, String newStatus) {
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            return false;
        }

        try {
            Parcel.ParcelStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }

        ArrayList<String> parcels = new ArrayList<>();
        boolean updated = false;

        try {
            Scanner sc = new Scanner(file);

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

            sc.close();

            if (!updated) {
                return false;
            }

            FileWriter writer = new FileWriter(PARCEL_FILE, false);

            for (String parcel : parcels) {
                writer.write(parcel + "#\n");
            }

            writer.close();
            return true;

        } catch (Exception e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
            return false;
        }
    }

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
                String[] parcel = cleanData.split("-");

                if (parcel.length >= 10
                        && parcel[3].equals(parcelId)
                        && parcel[6].equals(userId)) {

                    if (parcel[7].equals(String.valueOf(Parcel.ParcelStatus.PENDING))
                            || parcel[7].equals(String.valueOf(Parcel.ParcelStatus.CANCELED))) {

                        deleted = true;
                        continue;
                    }
                }

                parcels.add(cleanData);
            }

            sc.close();

            if (!deleted) {
                return false;
            }

            FileWriter writer = new FileWriter(PARCEL_FILE, false);

            for (String parcel : parcels) {
                writer.write(parcel + "#\n");
            }

            writer.close();
            return true;

        } catch (Exception e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
            return false;
        }
    }

    public static Parcel searchParcel(String parcelId) {
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            return null;
        }

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String data = sc.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                data = data.replace("#", "");
                String[] parcelData = data.split("-");

                if (parcelData.length >= 10 && parcelData[3].equals(parcelId)) {
                    User user = new User("", parcelData[5], "");
                    user.setUser_id(parcelData[6]);

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

                    sc.close();
                    return parcel;
                }
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Exception from ParcelFile: " + e.getMessage());
        }

        return null;
    }
}