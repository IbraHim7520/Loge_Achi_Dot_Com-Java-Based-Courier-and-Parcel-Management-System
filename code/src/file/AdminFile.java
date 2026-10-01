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
import java.util.UUID;

public class AdminFile {

    private static final String USER_FILE =
            "code/src/database/users_db.txt";

    private static final String PARCEL_FILE =
            "code/src/database/parcel_db.txt";

    private static final String SEPARATOR = "|";
    private static final String RECORD_END = "#";

    private static void createFileIfNeeded(String filePath)
            throws IOException {

        File file = new File(filePath);

        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs()) {
                throw new IOException(
                        "Could not create database directory."
                );
            }
        }

        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException(
                        "Could not create database file."
                );
            }
        }
    }


    private static ArrayList<String> readLines(String filePath)
            throws IOException {

        createFileIfNeeded(filePath);

        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner =
                     new Scanner(new File(filePath))) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine().trim();

                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        }

        return lines;
    }



    private static void writeLines(
            String filePath,
            ArrayList<String> lines
    ) throws IOException {

        createFileIfNeeded(filePath);

        try (FileWriter writer =
                     new FileWriter(filePath, false)) {

            for (String line : lines) {

                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                String cleanLine = removeRecordEnd(line);

                writer.write(
                        cleanLine
                                + RECORD_END
                                + System.lineSeparator()
                );
            }
        }
    }




    private static String removeRecordEnd(String data) {

        if (data == null) {
            return "";
        }

        data = data.trim();

        if (data.endsWith(RECORD_END)) {
            return data.substring(
                    0,
                    data.length() - RECORD_END.length()
            );
        }

        return data;
    }



    private static String[] parseUser(String line) {

        if (line == null) {
            return new String[0];
        }

        String data = removeRecordEnd(line);

        if (data.contains(SEPARATOR)) {
            return data.split("\\|", -1);
        }

        return data.split("-", -1);
    }



    private static String[] parseParcel(String line) {

        if (line == null) {
            return new String[0];
        }

        String data = removeRecordEnd(line);

        return data.split("\\|", -1);
    }



    private static boolean isValidUserRecord(String[] data) {

        return data != null && data.length >= 5;
    }




    private static boolean isValidParcelRecord(String[] data) {

        return data != null && data.length >= 10;
    }



    private static boolean emailExists(
            String email,
            String excludedUserId
    ) throws IOException {

        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        ArrayList<String> lines =
                readLines(USER_FILE);

        for (String line : lines) {

            String[] data = parseUser(line);

            if (!isValidUserRecord(data)) {
                continue;
            }

            boolean sameEmail =
                    data[1].trim()
                            .equalsIgnoreCase(email.trim());

            boolean sameUser =
                    excludedUserId != null
                            && data[4].trim()
                            .equals(excludedUserId.trim());

            if (sameEmail && !sameUser) {
                return true;
            }
        }

        return false;
    }



    public static boolean registerNewRider(
            String name,
            String email,
            String password
    ) throws NotFoundException {

        if (name == null
                || email == null
                || password == null) {

            return false;
        }

        name = name.trim();
        email = email.trim();

        if (name.isEmpty()
                || email.isEmpty()
                || password.isEmpty()) {

            return false;
        }

        if (name.contains(SEPARATOR)
                || name.contains(RECORD_END)
                || email.contains(SEPARATOR)
                || email.contains(RECORD_END)
                || password.contains(SEPARATOR)
                || password.contains(RECORD_END)) {

            return false;
        }

        // Create temporary User object
        User tempUser =
                new User(
                        name,
                        email,
                        password
                );

        /*
         * IMPORTANT:
         * User.validateEmail() needs an argument.
         */
        if (!tempUser.validateEmail(email)) {
            return false;
        }

        if (!tempUser.validatePassword(password)) {
            return false;
        }

        try {

            if (emailExists(email, null)) {
                return false;
            }

            String riderId =
                    "R"
                            + UUID.randomUUID()
                            .toString()
                            .replace("-", "");

            ArrayList<String> lines =
                    readLines(USER_FILE);

            String newUser =
                    name
                            + SEPARATOR
                            + email
                            + SEPARATOR
                            + password
                            + SEPARATOR
                            + "RIDER"
                            + SEPARATOR
                            + riderId;

            lines.add(newUser);

            writeLines(
                    USER_FILE,
                    lines
            );

            return true;

        } catch (IOException e) {

            return false;
        }
    }



    public static ArrayList<User> getAllUsers()
            throws NotFoundException {

        ArrayList<User> users =
                new ArrayList<>();

        try {

            ArrayList<String> lines =
                    readLines(USER_FILE);

            for (String line : lines) {

                String[] data =
                        parseUser(line);

                if (!isValidUserRecord(data)) {
                    continue;
                }

                try {

                    User user =
                            new User(
                                    data[0].trim(),
                                    data[1].trim(),
                                    data[2]
                            );

                    user.setUser_role(
                            data[3].trim()
                    );

                    user.setUser_id(
                            data[4].trim()
                    );

                    users.add(user);

                } catch (Exception e) {

                    // Skip invalid user record
                }
            }

        } catch (IOException e) {

            throw new NotFoundException(
                    "Unable to read user database!"
            );
        }

        if (users.isEmpty()) {

            throw new NotFoundException(
                    "No users found!"
            );
        }

        return users;
    }



    public static boolean updateUser(
            String userID,
            String name,
            String email,
            String password
    ) throws NotFoundException {

        if (userID == null
                || userID.trim().isEmpty()) {

            return false;
        }

        if (name == null || email == null) {
            return false;
        }

        name = name.trim();
        email = email.trim();

        if (name.isEmpty()
                || email.isEmpty()) {

            return false;
        }

        if (name.contains(SEPARATOR)
                || name.contains(RECORD_END)
                || email.contains(SEPARATOR)
                || email.contains(RECORD_END)) {

            return false;
        }

        User tempUser =
                new User(
                        name,
                        email,
                        password == null
                                ? "123456"
                                : password
                );

        if (!tempUser.validateEmail(email)) {
            return false;
        }

        if (password != null
                && !password.isEmpty()
                && !tempUser.validatePassword(password)) {

            return false;
        }

        try {

            if (emailExists(email, userID)) {
                return false;
            }

            ArrayList<String> lines =
                    readLines(USER_FILE);

            ArrayList<String> updatedLines =
                    new ArrayList<>();

            boolean found = false;

            for (String line : lines) {

                String[] data =
                        parseUser(line);

                if (!isValidUserRecord(data)) {

                    updatedLines.add(line);
                    continue;
                }

                if (data[4].trim()
                        .equals(userID.trim())) {

                    String oldPassword =
                            data[2];

                    String finalPassword =
                            password == null
                                    || password.isEmpty()
                                    ? oldPassword
                                    : password;

                    updatedLines.add(
                            name
                                    + SEPARATOR
                                    + email
                                    + SEPARATOR
                                    + finalPassword
                                    + SEPARATOR
                                    + data[3].trim()
                                    + SEPARATOR
                                    + data[4].trim()
                    );

                    found = true;

                } else {

                    updatedLines.add(line);
                }
            }

            if (!found) {

                throw new NotFoundException(
                        "User not found."
                );
            }

            writeLines(
                    USER_FILE,
                    updatedLines
            );

            return true;

        } catch (IOException e) {

            return false;
        }
    }


    public static User searchUser(String userID)
            throws NotFoundException {

        if (userID == null
                || userID.trim().isEmpty()) {

            throw new NotFoundException(
                    "User ID is required!"
            );
        }

        try {

            ArrayList<String> lines =
                    readLines(USER_FILE);

            for (String line : lines) {

                String[] data =
                        parseUser(line);

                if (!isValidUserRecord(data)) {
                    continue;
                }

                if (data[4].trim()
                        .equals(userID.trim())) {

                    User user =
                            new User(
                                    data[0].trim(),
                                    data[1].trim(),
                                    data[2]
                            );

                    user.setUser_role(
                            data[3].trim()
                    );

                    user.setUser_id(
                            data[4].trim()
                    );

                    return user;
                }
            }

        } catch (IOException e) {

            throw new NotFoundException(
                    "Unable to read user database!"
            );
        }

        throw new NotFoundException(
                "User not found!"
        );
    }


    public static boolean deleteUser(String userID)
            throws NotFoundException {

        if (userID == null
                || userID.trim().isEmpty()) {

            return false;
        }

        try {

            /*
             * Don't delete a user if they have
             * existing parcels.
             */
            ArrayList<String> parcelLines =
                    readLines(PARCEL_FILE);

            for (String line : parcelLines) {

                String[] data =
                        parseParcel(line);

                if (!isValidParcelRecord(data)) {
                    continue;
                }

                // Sender ID = index 6
                if (data[6].trim()
                        .equals(userID.trim())) {

                    return false;
                }
            }


            ArrayList<String> lines =
                    readLines(USER_FILE);

            ArrayList<String> updatedLines =
                    new ArrayList<>();

            boolean found = false;

            for (String line : lines) {

                String[] data =
                        parseUser(line);

                if (!isValidUserRecord(data)) {

                    updatedLines.add(line);
                    continue;
                }

                if (data[4].trim()
                        .equals(userID.trim())) {

                    // Admin cannot be deleted
                    if (data[3].trim()
                            .equalsIgnoreCase("ADMIN")) {

                        return false;
                    }

                    found = true;

                } else {

                    updatedLines.add(line);
                }
            }

            if (!found) {

                throw new NotFoundException(
                        "User not found."
                );
            }

            writeLines(
                    USER_FILE,
                    updatedLines
            );

            return true;

        } catch (IOException e) {

            return false;
        }
    }


    public static ArrayList<Parcel> getAllParcels()
            throws NotFoundException,
            UnauthorizedAccessException,
            InvalidAmountException {

        ArrayList<Parcel> parcels =
                new ArrayList<>();

        try {

            ArrayList<String> lines =
                    readLines(PARCEL_FILE);

            for (String line : lines) {

                String[] data =
                        parseParcel(line);

                if (!isValidParcelRecord(data)) {
                    continue;
                }

                try {

                    Parcel parcel =
                            createParcelFromData(data);

                    parcels.add(parcel);

                } catch (NumberFormatException e) {

                    System.err.println(
                            "Skipping invalid parcel: "
                                    + line
                    );
                }
            }

        } catch (IOException e) {

            throw new NotFoundException(
                    "Unable to read parcel database!"
            );
        }

        if (parcels.isEmpty()) {

            throw new NotFoundException(
                    "No parcels found!"
            );
        }

        return parcels;
    }




    private static Parcel createParcelFromData(
            String[] data
    ) throws UnauthorizedAccessException,
            InvalidAmountException {


        double weight;

        double deliveryCharge;

        try {

            weight =
                    Double.parseDouble(
                            data[4].trim()
                    );

            deliveryCharge =
                    Double.parseDouble(
                            data[8].trim()
                    );

        } catch (NumberFormatException e) {

            throw new InvalidAmountException(
                    "Invalid parcel weight or delivery charge."
            );
        }


        User sender =
                new User(
                        "",
                        data[5].trim(),
                        "123456"
                );

        sender.setUser_id(
                data[6].trim()
        );

        sender.setUser_role(
                String.valueOf(
                        User.UserRole.USER
                )
        );


        Parcel parcel =
                new Parcel(sender);

        parcel.setParcelName(
                data[0].trim()
        );

        parcel.setReciverAddress(
                data[1].trim()
        );

        parcel.setReciverPhone(
                data[2].trim()
        );

        parcel.setParcelID(
                data[3].trim()
        );

        parcel.setWeight(weight);

        parcel.setSenderEmail(
                data[5].trim()
        );

        parcel.setSenderId(
                data[6].trim()
        );

        parcel.setParcelStatus(
                data[7].trim()
        );

        parcel.setDeliveryCharge(
                deliveryCharge
        );


        if (data[9].trim().isEmpty()
                || data[9].trim()
                .equalsIgnoreCase("null")) {

            parcel.setRiderId(null);

        } else {

            parcel.setRiderId(
                    data[9].trim()
            );
        }

        return parcel;
    }



    public static Parcel searchParcel(
            String parcelID
    ) throws NotFoundException,
            UnauthorizedAccessException,
            InvalidAmountException {

        if (parcelID == null
                || parcelID.trim().isEmpty()) {

            throw new NotFoundException(
                    "Parcel ID is required!"
            );
        }

        try {

            ArrayList<String> lines =
                    readLines(PARCEL_FILE);

            for (String line : lines) {

                String[] data =
                        parseParcel(line);

                if (!isValidParcelRecord(data)) {
                    continue;
                }

                // Parcel ID = index 3
                if (data[3].trim()
                        .equals(parcelID.trim())) {

                    return createParcelFromData(data);
                }
            }

        } catch (IOException e) {

            throw new NotFoundException(
                    "Unable to read parcel database!"
            );
        }

        throw new NotFoundException(
                "Parcel not found!"
        );
    }



    public static boolean deleteParcel(
            String parcelID
    ) throws NotFoundException {

        if (parcelID == null
                || parcelID.trim().isEmpty()) {

            return false;
        }

        try {

            ArrayList<String> lines =
                    readLines(PARCEL_FILE);

            ArrayList<String> updatedLines =
                    new ArrayList<>();

            boolean found = false;

            for (String line : lines) {

                String[] data =
                        parseParcel(line);

                if (!isValidParcelRecord(data)) {

                    updatedLines.add(line);
                    continue;
                }

                // Parcel ID = index 3
                if (data[3].trim()
                        .equals(parcelID.trim())) {

                    found = true;

                } else {

                    updatedLines.add(line);
                }
            }

            if (!found) {

                throw new NotFoundException(
                        "Parcel not found."
                );
            }

            writeLines(
                    PARCEL_FILE,
                    updatedLines
            );

            return true;

        } catch (IOException e) {

            return false;
        }
    }

    public static boolean updateParcelStatus(
            String parcelID,
            String newStatus
    ) throws NotFoundException {

        if (parcelID == null
                || parcelID.trim().isEmpty()) {

            return false;
        }

        if (newStatus == null
                || newStatus.trim().isEmpty()) {

            return false;
        }

        String status =
                newStatus.trim().toUpperCase();


        try {

            Parcel.ParcelStatus.valueOf(status);

        } catch (IllegalArgumentException e) {

            return false;
        }


        try {

            ArrayList<String> lines =
                    readLines(PARCEL_FILE);

            ArrayList<String> updatedLines =
                    new ArrayList<>();

            boolean found = false;

            for (String line : lines) {

                String[] data =
                        parseParcel(line);

                if (!isValidParcelRecord(data)) {

                    updatedLines.add(line);
                    continue;
                }

                // Parcel ID = index 3
                if (data[3].trim()
                        .equals(parcelID.trim())) {

                    // Status = index 7
                    data[7] = status;

                    String updatedRecord =
                            String.join(
                                    SEPARATOR,
                                    data
                            );

                    updatedLines.add(
                            updatedRecord
                    );

                    found = true;

                } else {

                    updatedLines.add(line);
                }
            }

            if (!found) {

                throw new NotFoundException(
                        "Parcel not found."
                );
            }

            writeLines(
                    PARCEL_FILE,
                    updatedLines
            );

            return true;

        } catch (IOException e) {

            return false;
        }
    }
}