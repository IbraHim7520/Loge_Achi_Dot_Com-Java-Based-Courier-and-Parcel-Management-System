package file;

import model.Parcel;
import model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminFile {

    private static final String USER_FILE =
            "code/src/database/users_db.txt";

    private static final String PARCEL_FILE =
            "code/src/database/parcel_db.txt";

    private static final String RIDER_FILE =
            "code/src/database/rider_db.txt";


    // ==================== USER ====================

    // Admin.viewAllUser()
    public static ArrayList<User> getAllUsers() {
        // ekhane USER_FILE theke
        // all users read kore return korbo

        return new ArrayList<>();
    }


    // Admin.registerNewRider()
    public static boolean registerNewRider() {
        // ekhane new rider er information nibo
        // rider validate korbo
        // USER_FILE e rider save korbo
        // RIDER_FILE eo rider save korbo

        return false;
    }


    // Admin.updateUser()
    public static boolean updateUser() {
        // ekhane user information update korbo
        // USER_FILE e existing user khujbo
        // tarpor updated information save korbo

        return false;
    }


    // Admin.searchUser()
    public static User searchUser(String userId) {


        File file = new File(USER_FILE);


        if (!file.exists()) {
            return null;
        }


        try {


            Scanner scanner = new Scanner(file);


            while (scanner.hasNextLine()) {


                String data =
                        scanner.nextLine().trim();


                if (data.isEmpty()) {
                    continue;
                }


                data = data.replace("#", "");


                String[] userData = data.split("-");


                if (userData.length >= 5
                        && userData[4].equals(userId)) {


                    User user =
                            new User(
                                    userData[0],
                                    userData[1],
                                    userData[2]
                            );


                    user.setUser_role(userData[3]);
                    user.setUser_id(userData[4]);


                    scanner.close();


                    return user;
                }
            }


            scanner.close();


        } catch (Exception e) {


            System.out.println(
                    "Exception from AdminFile: "
                            + e.getMessage()
            );
        }


        return null;
    }



    // Admin.deleteUser()
    public static boolean deleteUser(String userId) {
        // ekhane userId diye USER_FILE e user khujbo
        // tarpor user delete korbo

        return false;
    }


    // ==================== PARCEL ====================

    // Admin.viewAllParcel()
    public static ArrayList<Parcel> getAllParcels() {
        // ekhane PARCEL_FILE theke
        // all parcels read kore return korbo

        return new ArrayList<>();
    }


    // Admin.searchParcel()
    public static Parcel searchParcel(String parcelId) {
        // ekhane parcelId diye PARCEL_FILE e
        // parcel search korbo

        return null;
    }


    // Admin.deleteParcel()
    public static boolean deleteParcel(String parcelId) {
        // ekhane parcelId diye PARCEL_FILE e
        // parcel khujbo
        // tarpor parcel delete korbo

        return false;
    }

    public static ArrayList<User> getAllRiders() {
        // ekhane users_db.txt file theke
        // all users read kore sudhu RIDER der ber korbo

        return new ArrayList<>();
    }



    // Admin.updateParcelStatus()
    public static boolean updateParcelStatus(
            String parcelId,
            String newStatus) {

        // ekhane parcelId diye PARCEL_FILE e
        // parcel khujbo
        // tarpor parcel er status update korbo

        return false;
    }

}