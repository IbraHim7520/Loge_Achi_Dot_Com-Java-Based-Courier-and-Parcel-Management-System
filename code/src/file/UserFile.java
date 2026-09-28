package file;

import custom_exception.NotFoundException;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserFile {

    private static final String USER_FILE =
            "code/src/database/users_db.txt";

    // New database separator
    private static final String SEPARATOR = "|";

    public static boolean IsUserExists(User user) {

        if (user == null
                || user.getUser_email() == null
                || user.getUser_email().trim().isEmpty()) {

            return false;
        }

        File file = new File(USER_FILE);

        if (!file.exists()) {
            return false;
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                User storedUser = parseUser(data);

                if (storedUser != null
                        && storedUser.getUser_email() != null
                        && storedUser.getUser_email()
                        .equalsIgnoreCase(user.getUser_email().trim())) {

                    return true;
                }
            }

        } catch (IOException e) {

            System.err.println(
                    "Exception from UserFile: " + e.getMessage());
        }

        return false;
    }


    public static boolean createNewUser(User user) {

        if (user == null
                || user.getUser_name() == null
                || user.getUser_email() == null
                || user.getUserPassword() == null) {

            return false;
        }

        String name = user.getUser_name().trim();
        String email = user.getUser_email().trim();
        String password = user.getUserPassword();

        if (name.isEmpty()
                || email.isEmpty()
                || password.isEmpty()) {

            return false;
        }


        if (name.contains("|")
                || email.contains("|")
                || password.contains("|")
                || name.contains("#")
                || email.contains("#")
                || password.contains("#")) {

            return false;
        }

        user.setUser_name(name);
        user.setUser_email(email);


        if (IsUserExists(user)) {
            return false;
        }

        String role = String.valueOf(User.UserRole.USER);
        String userId = user.generateUserID();

        try {

            File file = new File(USER_FILE);

            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {

                if (!parent.mkdirs()) {
                    return false;
                }
            }

            if (!file.exists()) {

                if (!file.createNewFile()) {
                    return false;
                }
            }

            String newUser =
                    name + SEPARATOR
                            + email + SEPARATOR
                            + password + SEPARATOR
                            + role + SEPARATOR
                            + userId
                            + "#"
                            + System.lineSeparator();

            try (FileWriter writer =
                         new FileWriter(file, true)) {

                writer.write(newUser);
            }

            // Update current User object
            user.setUser_id(userId);
            user.setUser_role(role);

            return true;

        } catch (IOException e) {

            System.err.println(
                    "Exception from UserFile: " + e.getMessage());

            return false;
        }
    }

    public User loginUser(String email, String password)
            throws NotFoundException {

        if (email == null
                || email.trim().isEmpty()
                || password == null
                || password.isEmpty()) {

            throw new NotFoundException(
                    "Email and password are required!");
        }

        File file = new File(USER_FILE);

        if (!file.exists()) {

            throw new NotFoundException(
                    "User database not found!");
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                User storedUser = parseUser(data);

                if (storedUser == null) {
                    continue;
                }

                String storedEmail =
                        storedUser.getUser_email();

                String storedPassword =
                        storedUser.getUserPassword();

                if (storedEmail != null
                        && storedPassword != null
                        && storedEmail.equalsIgnoreCase(email.trim())
                        && storedPassword.equals(password)) {

                    return storedUser;
                }
            }

        } catch (IOException e) {

            throw new NotFoundException(
                    "Unable to read user database!");
        }

        throw new NotFoundException(
                "User not found or invalid email/password!");
    }

    private static User parseUser(String data) {

        if (data == null || data.trim().isEmpty()) {
            return null;
        }

        // Remove only the ending # character
        String cleanData = data.trim();

        if (cleanData.endsWith("#")) {
            cleanData =
                    cleanData.substring(0, cleanData.length() - 1);
        }

        String[] userData;


        if (cleanData.contains("|")) {

            userData = cleanData.split("\\|", -1);

        }

        else {

            userData = cleanData.split("-", -1);
        }

        if (userData.length < 5) {
            return null;
        }

        String storedName = userData[0].trim();
        String storedEmail = userData[1].trim();
        String storedPassword = userData[2];
        String storedRole = userData[3].trim();
        String storedId = userData[4].trim();

        if (storedName.isEmpty()
                || storedEmail.isEmpty()
                || storedPassword.isEmpty()
                || storedRole.isEmpty()
                || storedId.isEmpty()) {

            return null;
        }

        User user = new User(
                storedName,
                storedEmail,
                storedPassword
        );

        user.setUser_role(storedRole);
        user.setUser_id(storedId);

        return user;
    }
}