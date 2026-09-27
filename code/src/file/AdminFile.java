package file;

import model.Parcel;
import model.User;

import java.io.File;
import java.io.FileWriter;
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
    // Admin.viewAllUser()
    public static ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();

        File file = new File(USER_FILE);

        if (!file.exists()) {
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

                    User user = new User(
                            userData[0],
                            userData[1],
                            userData[2]
                    );

                    user.setUser_role(userData[3]);
                    user.setUser_id(userData[4]);

                    users.add(user);
                }
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println(
                    "Exception from AdminFile: "
                            + e.getMessage()
            );
        }

        return users;
    }

    // Admin.registerNewRider()
    public static boolean registerNewRider(String name, String email, String password) {
        User rider = new User(name, email, password);

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        if (!rider.validateEmail(email) || !rider.validatePassword(password)) {
            return false;
        }

        if (UserFile.IsUserExists(rider)) {
            return false;
        }

        String riderId = rider.generateUserID();
        String role = String.valueOf(User.UserRole.RIDER);

        String data = name + "-" + email + "-" + password + "-" + role + "-" + riderId + "#\n";

        try {
            File userFile = new File(USER_FILE);

            if (!userFile.exists()) {
                userFile.createNewFile();
            }

            FileWriter userWriter = new FileWriter(USER_FILE, true);
            userWriter.write(data);
            userWriter.close();

            File riderFile = new File(USER_FILE);

            if (!riderFile.exists()) {
                riderFile.createNewFile();
            }

            FileWriter riderWriter = new FileWriter(USER_FILE, true);
            riderWriter.write(data);
            riderWriter.close();

            return true;
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
            return false;
        }
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
    public static ArrayList<String> getAllParcels() {
        ArrayList<String> parcels = new ArrayList<>();
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            return parcels;
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();

                if (!data.isEmpty()) {
                    parcels.add(data.replace("#", ""));
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
        }

        return parcels;
    }


    // Admin.searchParcel()
    public static String searchParcel(String parcelId) {
        File file = new File(PARCEL_FILE);

        if (!file.exists()) {
            return null;
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                data = data.replace("#", "");
                String[] parcelData = data.split("-");

                if (parcelData.length >= 10 && parcelData[3].equals(parcelId)) {
                    scanner.close();
                    return data;
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception from AdminFile: " + e.getMessage());
        }

        return null;
    }


    // Admin.deleteParcel()
    public static boolean deleteParcel(String parcelId) {
        // ekhane parcelId diye PARCEL_FILE e
        // parcel khujbo
        // tarpor parcel delete korbo

        return false;
    }

    // Get all riders
    public static ArrayList<User> getAllRiders() {

        ArrayList<User> riders =
                new ArrayList<>();

        File file =
                new File(USER_FILE);

        if (!file.exists()) {
            return riders;
        }

        try {

            Scanner scanner =
                    new Scanner(file);

            while (scanner.hasNextLine()) {

                String data =
                        scanner.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                data = data.replace("#", "");

                String[] userData =
                        data.split("-");

                if (userData.length >= 5
                        && userData[3].equals(
                        String.valueOf(
                                User.UserRole.RIDER))) {

                    User rider =
                            new User(
                                    userData[0],
                                    userData[1],
                                    userData[2]
                            );

                    rider.setUser_role(
                            userData[3]
                    );

                    rider.setUser_id(
                            userData[4]
                    );

                    riders.add(rider);
                }
            }

            scanner.close();

        } catch (Exception e) {

            System.out.println(
                    "Exception from AdminFile: "
                            + e.getMessage()
            );
        }

        return riders;
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