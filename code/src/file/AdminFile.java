package file;
import model.Parcel;
import model.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminFile {
    private static final String USER_FILE = "code/src/database/users_db.txt";
    private static final String PARCEL_FILE = "code/src/database/parcel_db.txt";

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        File file = new File(USER_FILE);

        if (!file.exists()){
            return users;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {

                String data = scanner.nextLine().trim();
                if (data.isEmpty()) {
                    continue;
                }
                data = data.replace("#", "");
                String[] userData = data.split("-");
                if (userData.length >= 5) {
                    User user = new User(userData[0], userData[1], userData[2]);
                    user.setUser_role(userData[3]);
                    user.setUser_id(userData[4]);
                    users.add(user);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
        }
        return users;
    }

    public static boolean registerNewRider(String name,String email,String password) {
        User rider = new User(name,email,password);
        if (name == null || name.trim().isEmpty()){
            return false;
        }
        if (!rider.validateEmail(email) || !rider.validatePassword(password)){
            return false;
        }
        if (UserFile.IsUserExists(rider)) {
            return false;
        }
        String riderId = rider.generateUserID();
        String role = String.valueOf(User.UserRole.RIDER);
        String data = name + "-" + email + "-" + password + "-" + role + "-" + riderId + "#\n";

        try {
            File file = new File(USER_FILE);
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(USER_FILE,true);
            writer.write(data);
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateUser(String userId,String name,String email,String password) {
        File file = new File(USER_FILE);
        if (!file.exists()){
            return false;
        }
        ArrayList<String> users = new ArrayList<>();
        boolean updated = false;
        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine().trim();
                if (data.isEmpty()) {
                    continue;
                }
                data = data.replace("#","");
                String[] userData = data.split("-");

                if (userData.length >= 5 && userData[4].equals(userId)) {
                    User user = new User(name,email,password);
                    if (!user.validateEmail(email) || !user.validatePassword(password)) {
                        scanner.close();
                        return false;
                    }
                    data = name + "-" + email + "-" + password + "-" + userData[3] + "-" + userData[4];
                    updated = true;
                }
                users.add(data);
            }
            scanner.close();
            if (!updated) {
                return false;
            }
            FileWriter writer = new FileWriter(USER_FILE,false);
            for (String user : users) writer.write(user + "#\n");
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
            return false;
        }
    }

    public static User searchUser(String userId) {
        File file = new File(USER_FILE);
        if (!file.exists()){
            return null;
        }
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (data.isEmpty()){
                    continue;
                }
                data = data.replace("#","");
                String[] userData = data.split("-");

                if (userData.length >= 5 && userData[4].equals(userId)) {
                    User user = new User(userData[0],userData[1],userData[2]);
                    user.setUser_role(userData[3]);
                    user.setUser_id(userData[4]);
                    scanner.close();
                    return user;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
        }
        return null;
    }

    public static boolean deleteUser(String userId) {
        File file = new File(USER_FILE);
        if (!file.exists()){
            return false;
        }
        ArrayList<String> users = new ArrayList<>();
        boolean deleted = false;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (data.isEmpty()) {
                    continue;
                }
                data = data.replace("#","");
                String[] userData = data.split("-");

                if (userData.length >= 5 && userData[4].equals(userId)) {
                    deleted = true;
                    continue;
                }
                users.add(data);
            }
            scanner.close();
            if (!deleted){
                return false;
            }
            FileWriter writer = new FileWriter(USER_FILE,false);
            for (String user : users){
                writer.write(user + "#\n");
            }
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Parcel> getAllParcels() {
        ArrayList<Parcel> parcels = new ArrayList<>();
        File file = new File(PARCEL_FILE);
        if (!file.exists()) {
            return parcels;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (data.isEmpty()) {
                    continue;
                }
                data = data.replace("#","");
                String[] parcelData = data.split("-");
                if (parcelData.length >= 10) {
                    User sender = new User("",parcelData[5],"");
                    sender.setUser_id(parcelData[6]);
                    Parcel parcel = new Parcel(sender);
                    parcel.setParcelName(parcelData[0]);
                    parcel.setReciverAddress(parcelData[1]);
                    parcel.setReciverPhone(parcelData[2]);
                    parcel.setParcelID(parcelData[3]);
                    parcel.setWeight(Double.parseDouble(parcelData[4]));
                    parcel.setSenderEmail(parcelData[5]);
                    parcel.setSenderId(parcelData[6]);
                    parcel.setParcelStatus(parcelData[7]);
                    parcel.setDeliveryCharge(Double.parseDouble(parcelData[8]));
                    parcel.setRiderId(parcelData[9].equals("null") ? null : parcelData[9]);
                    parcels.add(parcel);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
        }
        return parcels;
    }

    public static Parcel searchParcel(String parcelId) {
        File file = new File(PARCEL_FILE);
        if (!file.exists()){
            return null;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (data.isEmpty()) {
                    continue;
                }
                data = data.replace("#","");
                String[] parcelData = data.split("-");

                if (parcelData.length >= 10 && parcelData[3].equals(parcelId)) {
                    User sender = new User("",parcelData[5],"");
                    sender.setUser_id(parcelData[6]);
                    Parcel parcel = new Parcel(sender);
                    parcel.setParcelName(parcelData[0]);
                    parcel.setReciverAddress(parcelData[1]);
                    parcel.setReciverPhone(parcelData[2]);
                    parcel.setParcelID(parcelData[3]);
                    parcel.setWeight(Double.parseDouble(parcelData[4]));
                    parcel.setSenderEmail(parcelData[5]);
                    parcel.setSenderId(parcelData[6]);
                    parcel.setParcelStatus(parcelData[7]);
                    parcel.setDeliveryCharge(Double.parseDouble(parcelData[8]));
                    parcel.setRiderId(parcelData[9].equals("null") ? null : parcelData[9]);
                    scanner.close();
                    return parcel;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
        }
        return null;
    }

    public static boolean deleteParcel(String parcelId) {
        File file = new File(PARCEL_FILE);
        if (!file.exists()) {
            return false;
        }
        ArrayList<String> parcels = new ArrayList<>();
        boolean deleted = false;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (data.isEmpty()) {
                    continue;
                }
                data = data.replace("#","");
                String[] parcelData = data.split("-");
                if (parcelData.length >= 10 && parcelData[3].equals(parcelId)) {
                    deleted = true;
                    continue;
                }
                parcels.add(data);
            }
            scanner.close();
            if (!deleted) {
                return false;
            }
            FileWriter writer = new FileWriter(PARCEL_FILE,false);
            for (String parcel : parcels) writer.write(parcel + "#\n");
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<User> getAllRiders() {
        ArrayList<User> riders = new ArrayList<>();
        File file = new File(USER_FILE);
        if (!file.exists()) return riders;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (data.isEmpty()) {
                    continue;
                }
                data = data.replace("#","");
                String[] userData = data.split("-");
                if (userData.length >= 5 && userData[3].equals(String.valueOf(User.UserRole.RIDER))) {
                    User rider = new User(userData[0],userData[1],userData[2]);
                    rider.setUser_role(userData[3]);
                    rider.setUser_id(userData[4]);
                    riders.add(rider);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
        }
        return riders;
    }

    public static boolean updateParcelStatus(String parcelId,String newStatus) {
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
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (data.isEmpty()) {
                    continue;
                }
                data = data.replace("#","");
                String[] parcelData = data.split("-");
                if (parcelData.length >= 10 && parcelData[3].equals(parcelId)) {
                    parcelData[7] = newStatus.toUpperCase();
                    data = String.join("-",parcelData);
                    updated = true;
                }
                parcels.add(data);
            }
            scanner.close();
            if (!updated) {
                return false;
            }
            FileWriter writer = new FileWriter(PARCEL_FILE,false);
            for (String parcel : parcels) writer.write(parcel + "#\n");
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
            return false;
        }
    }
}