
package file;

import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserFile {

    ArrayList<String> usersList = new ArrayList<>();

    private static final String USER_FILE =
            "code/src/database/users_db.txt";


    // Create New User
    public boolean createNewUser(User user) {

        String name = user.getUser_name();
        String email = user.getUser_email();
        String pass = user.getUserPassword();
        String role = user.getUser_role();
        String userid = user.getUser_id();

        try {

            File file = new File(USER_FILE);

            if (!file.exists()) {
                file.createNewFile();
            }

            String newUser =
                    name + " " +
                            email + " " +
                            pass + " " +
                            role + " " +
                            userid + "#\n";

            FileWriter fwtr =
                    new FileWriter(USER_FILE, true);

            fwtr.write(newUser);
            fwtr.close();

            return true;

        } catch (IOException e) {

            System.out.println(e.getMessage());
            return false;
        }
    }


    // Check User Login
    public boolean userRegisteredCheck(
            String email,
            String password) {

        File fl = new File(USER_FILE);

        try {

            if (!fl.exists()) {
                return false;
            }

            Scanner scn = new Scanner(fl);

            while (scn.hasNextLine()) {

                String data = scn.nextLine();

                String[] userData = data.split(" ");

                if (userData.length >= 5) {

                    String storedEmail = userData[1];
                    String storedPassword = userData[2];

                    if (storedEmail.equals(email)
                            && storedPassword.equals(password)) {

                        scn.close();
                        return true;
                    }
                }
            }

            scn.close();

        } catch (IOException e) {

            System.out.println(
                    "Something wrong happened!"
            );

            System.out.println(e.getMessage());
        }

        return false;
    }


    // Get Current User ID
    public static String getCurrentUser(String email) {

        File fl = new File(USER_FILE);

        try {

            if (!fl.exists()) {
                return null;
            }

            Scanner scn = new Scanner(fl);

            while (scn.hasNextLine()) {

                String data = scn.nextLine();

                String[] userData = data.split(" ");

                if (userData.length >= 5) {

                    String storedEmail = userData[1];

                    if (storedEmail.equals(email)) {

                        String userId = userData[4];

                        // Remove # from USER ID
                        userId = userId.replace("#", "");

                        scn.close();

                        return userId;
                    }
                }
            }

            scn.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }

        return null;
    }
}

