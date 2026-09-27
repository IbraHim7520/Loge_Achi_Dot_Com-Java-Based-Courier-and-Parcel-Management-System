package file;

import custom_exception.NotFoundException;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserFile {

    private static final String USER_FILE = "code/src/database/users_db.txt";

    public static boolean IsUserExists(User user) {

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

                data = data.replace("#", "");

                String[] userData = data.split("-");

                if (userData.length >= 5) {
                    String storedEmail = userData[1];

                    if (storedEmail.equals(user.getUser_email())) {
                        return true;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Exception from UserFile: " + e.getMessage());
        }

        return false;
    }

    public boolean createNewUser(User user) {

        if (IsUserExists(user)) {
            return false;
        }

        String name = user.getUser_name();
        String email = user.getUser_email();
        String password = user.getUserPassword();
        String role = String.valueOf(User.UserRole.USER);
        String userId = user.generateUserID();

        try {

            File file = new File(USER_FILE);

            if (!file.exists()) {
                file.createNewFile();
            }

            String newUser = name + "-" + email + "-" + password + "-" + role + "-" + userId + "#\n";

            try (FileWriter writer = new FileWriter(USER_FILE, true)) {

                writer.write(newUser);
            }

            user.setUser_id(userId);
            user.setUser_role(role);

            return true;

        } catch (IOException e) {
            System.out.println("Exception from UserFile: " + e.getMessage());
            return false;
        }
    }

    public User loginUser(String email, String password) throws NotFoundException {

        File file = new File(USER_FILE);

        if (!file.exists()) {
            throw new NotFoundException("User database not found!");
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                data = data.replace("#", "");

                String[] userData = data.split("-");

                if (userData.length >= 5) {

                    String storedName = userData[0];
                    String storedEmail = userData[1];
                    String storedPassword = userData[2];
                    String storedRole = userData[3];
                    String storedId = userData[4];

                    if (storedEmail.equals(email) && storedPassword.equals(password)) {
                        User user = new User(storedName, storedEmail, storedPassword);
                        user.setUser_role(storedRole);
                        user.setUser_id(storedId);

                        return user;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Exception from UserFile: " + e.getMessage());
        }

        throw new NotFoundException("User not found or invalid email/password!");
    }
}