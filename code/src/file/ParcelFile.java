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

    private static final String PARCEL_FILE =
            "code/src/database/parcel_db.txt";

    private static final String SEPARATOR = "|";
    private static final String RECORD_END = "#";



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

        // Basic null validation
        if (parcelName == null
                || receiverAddress == null
                || receiverPhone == null
                || parcelID == null
                || senderEmail == null
                || senderId == null
                || parcelStatus == null) {

            return false;
        }

        // Empty field validation
        if (parcelName.trim().isEmpty()
                || receiverAddress.trim().isEmpty()
                || receiverPhone.trim().isEmpty()
                || parcelID.trim().isEmpty()
                || senderEmail.trim().isEmpty()
                || senderId.trim().isEmpty()
                || parcelStatus.trim().isEmpty()) {

            return false;
        }

        // Validate numeric values
        if (!Double.isFinite(weight) || weight <= 0) {
            return false;
        }

        if (!Double.isFinite(deliveryCharge)
                || deliveryCharge < 0) {

            return false;
        }

        // Validate parcel status
        try {

            Parcel.ParcelStatus.valueOf(
                    parcelStatus.trim().toUpperCase()
            );

        } catch (IllegalArgumentException e) {

            return false;
        }

        // Convert null rider ID to "null"
        if (riderId == null
                || riderId.trim().isEmpty()) {

            riderId = "null";
        }

        if (parcelName.contains(SEPARATOR)
                || receiverAddress.contains(SEPARATOR)
                || receiverPhone.contains(SEPARATOR)
                || parcelID.contains(SEPARATOR)
                || senderEmail.contains(SEPARATOR)
                || senderId.contains(SEPARATOR)
                || parcelStatus.contains(SEPARATOR)
                || riderId.contains(SEPARATOR)) {

            return false;
        }

        if (parcelName.contains(RECORD_END)
                || receiverAddress.contains(RECORD_END)
                || receiverPhone.contains(RECORD_END)
                || parcelID.contains(RECORD_END)
                || senderEmail.contains(RECORD_END)
                || senderId.contains(RECORD_END)
                || parcelStatus.contains(RECORD_END)
                || riderId.contains(RECORD_END)) {

            return false;
        }

        File file = new File(PARCEL_FILE);

        try {

            // Create database directory if necessary
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {

                if (!parent.mkdirs()) {
                    return false;
                }
            }

            // Create database file if necessary
            if (!file.exists()) {

                if (!file.createNewFile()) {
                    return false;
                }
            }

            // Create parcel record
            String newParcel =
                    parcelName.trim()
                            + SEPARATOR
                            + receiverAddress.trim()
                            + SEPARATOR
                            + receiverPhone.trim()
                            + SEPARATOR
                            + parcelID.trim()
                            + SEPARATOR
                            + weight
                            + SEPARATOR
                            + senderEmail.trim()
                            + SEPARATOR
                            + senderId.trim()
                            + SEPARATOR
                            + parcelStatus.trim().toUpperCase()
                            + SEPARATOR
                            + deliveryCharge
                            + SEPARATOR
                            + riderId.trim()
                            + RECORD_END
                            + System.lineSeparator();

            // Append to database
            try (FileWriter writer =
                         new FileWriter(file, true)) {

                writer.write(newParcel);
            }

            return true;

        } catch (IOException e) {

            System.err.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );

            return false;
        }
    }


    public static ArrayList<String> getMyAllParcels(
            String senderId
    ) throws NotFoundException {

        if (senderId == null
                || senderId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Invalid sender ID!"
            );
        }

        ArrayList<String> arr =
                new ArrayList<>();

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {

            throw new NotFoundException(
                    "Parcel database not found!"
            );
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String parcelData =
                        scanner.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                String cleanData =
                        removeRecordEnd(parcelData);

                String[] parcel =
                        cleanData.split("\\|", -1);

                if (parcel.length >= 10
                        && parcel[6].equals(senderId.trim())) {

                    arr.add(cleanData);
                }
            }

        } catch (IOException e) {

            System.err.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );

            throw new NotFoundException(
                    "Failed to access parcel database!"
            );
        }

        if (arr.isEmpty()) {

            throw new NotFoundException(
                    "No parcels found for this user!"
            );
        }

        return arr;
    }



    public static String findParcel(
            String parcelId
    ) throws NotFoundException {

        if (parcelId == null
                || parcelId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Parcel ID is required!"
            );
        }

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {

            throw new NotFoundException(
                    "Parcel database not found!"
            );
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String parcelData =
                        scanner.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                String cleanData =
                        removeRecordEnd(parcelData);

                String[] parcel =
                        cleanData.split("\\|", -1);

                if (parcel.length >= 10
                        && parcel[3].equals(
                        parcelId.trim())) {

                    return cleanData;
                }
            }

        } catch (IOException e) {

            System.err.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );

            throw new NotFoundException(
                    "Failed to access parcel database!"
            );
        }

        throw new NotFoundException(
                "Parcel not found!"
        );
    }


    public static boolean cancelParcel(
            String parcelId,
            String senderId
    ) throws NotFoundException {

        if (parcelId == null
                || parcelId.trim().isEmpty()
                || senderId == null
                || senderId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Invalid parcel or sender information!"
            );
        }

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {

            throw new NotFoundException(
                    "Parcel database not found!"
            );
        }

        ArrayList<String> allParcels =
                new ArrayList<>();

        boolean cancelled = false;

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String parcelData =
                        scanner.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                String cleanData =
                        removeRecordEnd(parcelData);

                String[] parcel =
                        cleanData.split("\\|", -1);

                if (parcel.length >= 10
                        && parcel[3].equals(
                        parcelId.trim())
                        && parcel[6].equals(
                        senderId.trim())
                        && parcel[7].equals(
                        String.valueOf(
                                Parcel.ParcelStatus.PENDING
                        )
                )) {

                    parcel[7] =
                            String.valueOf(
                                    Parcel.ParcelStatus.CANCELED
                            );

                    cleanData =
                            String.join(
                                    SEPARATOR,
                                    parcel
                            );

                    cancelled = true;
                }

                allParcels.add(cleanData);
            }

        } catch (IOException e) {

            System.err.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );

            return false;
        }

        if (!cancelled) {

            throw new NotFoundException(
                    "Parcel not found or cannot be canceled!"
            );
        }

        return rewriteDatabase(allParcels);
    }



    public static Parcel trackParcel(
            String parcelId,
            String senderId
    ) throws NotFoundException,
            UnauthorizedAccessException,
            InvalidAmountException {

        if (parcelId == null
                || parcelId.trim().isEmpty()
                || senderId == null
                || senderId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Invalid parcel or sender information!"
            );
        }

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {

            throw new NotFoundException(
                    "Parcel database not found!"
            );
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String data =
                        scanner.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                Parcel parcel =
                        parseParcelFromLine(data);

                if (parcel != null
                        && parcel.getParcelID()
                        .equals(parcelId.trim())
                        && parcel.getSenderId()
                        .equals(senderId.trim())) {

                    return parcel;
                }
            }

        } catch (IOException e) {

            System.err.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );

            throw new NotFoundException(
                    "Failed to access parcel database!"
            );
        }

        throw new NotFoundException(
                "Parcel not found for this user!"
        );
    }



    public static boolean updateParcelStatus(
            String parcelId,
            String newStatus
    ) throws NotFoundException {

        if (parcelId == null
                || parcelId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Parcel ID is required!"
            );
        }

        if (newStatus == null
                || newStatus.trim().isEmpty()) {

            throw new NotFoundException(
                    "Parcel status is required!"
            );
        }

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {

            throw new NotFoundException(
                    "Parcel database not found!"
            );
        }

        String status =
                newStatus.trim().toUpperCase();

        // Validate status
        try {

            Parcel.ParcelStatus.valueOf(status);

        } catch (IllegalArgumentException e) {

            throw new NotFoundException(
                    "Invalid parcel status!"
            );
        }

        ArrayList<String> parcels =
                new ArrayList<>();

        boolean updated = false;

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String data =
                        scanner.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                String cleanData =
                        removeRecordEnd(data);

                String[] parcelData =
                        cleanData.split("\\|", -1);

                if (parcelData.length >= 10
                        && parcelData[3].equals(
                        parcelId.trim())) {

                    parcelData[7] = status;

                    cleanData =
                            String.join(
                                    SEPARATOR,
                                    parcelData
                            );

                    updated = true;
                }

                parcels.add(cleanData);
            }

        } catch (IOException e) {

            System.err.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );

            return false;
        }

        if (!updated) {

            throw new NotFoundException(
                    "Parcel not found!"
            );
        }

        return rewriteDatabase(parcels);
    }


    public static boolean deleteParcel(
            String parcelId,
            String userId
    ) throws NotFoundException {

        if (parcelId == null
                || parcelId.trim().isEmpty()
                || userId == null
                || userId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Invalid parcel or user information!"
            );
        }

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {

            throw new NotFoundException(
                    "Parcel database not found!"
            );
        }

        ArrayList<String> parcels =
                new ArrayList<>();

        boolean deleted = false;

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String parcelData =
                        scanner.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                String cleanData =
                        removeRecordEnd(parcelData);

                String[] parcel =
                        cleanData.split("\\|", -1);

                if (parcel.length >= 10
                        && parcel[3].equals(
                        parcelId.trim())
                        && parcel[6].equals(
                        userId.trim())) {

                    String status = parcel[7];


                    if (status.equals(
                            String.valueOf(
                                    Parcel.ParcelStatus.PENDING
                            )
                    )
                            || status.equals(
                            String.valueOf(
                                    Parcel.ParcelStatus.CANCELED
                            )
                    )) {

                        deleted = true;
                        continue;
                    }
                }

                parcels.add(cleanData);
            }

        } catch (IOException e) {

            System.err.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );

            return false;
        }

        if (!deleted) {

            throw new NotFoundException(
                    "Parcel not found or cannot be deleted!"
            );
        }

        return rewriteDatabase(parcels);
    }



    public static Parcel searchParcel(
            String parcelId
    ) throws NotFoundException,
            UnauthorizedAccessException,
            InvalidAmountException {

        if (parcelId == null
                || parcelId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Parcel ID is required!"
            );
        }

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {

            throw new NotFoundException(
                    "Parcel database not found!"
            );
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String data =
                        scanner.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                Parcel parcel =
                        parseParcelFromLine(data);

                if (parcel != null
                        && parcel.getParcelID()
                        .equals(parcelId.trim())) {

                    return parcel;
                }
            }

        } catch (IOException e) {

            System.err.println(
                    "Exception from ParcelFile: "
                            + e.getMessage()
            );

            throw new NotFoundException(
                    "Failed to access parcel database!"
            );
        }

        throw new NotFoundException(
                "Parcel not found!"
        );
    }


    private static Parcel parseParcelFromLine(
            String line
    ) throws UnauthorizedAccessException,
            InvalidAmountException {

        if (line == null
                || line.trim().isEmpty()) {

            return null;
        }

        String data =
                removeRecordEnd(line.trim());

        String[] parcelData =
                data.split("\\|", -1);


        if (parcelData.length < 10) {
            return null;
        }

        try {

            String parcelName =
                    parcelData[0].trim();

            String receiverAddress =
                    parcelData[1].trim();

            String receiverPhone =
                    parcelData[2].trim();

            String parcelId =
                    parcelData[3].trim();

            double weight =
                    Double.parseDouble(
                            parcelData[4].trim()
                    );

            String senderEmail =
                    parcelData[5].trim();

            String senderId =
                    parcelData[6].trim();

            String parcelStatus =
                    parcelData[7].trim().toUpperCase();

            double deliveryCharge =
                    Double.parseDouble(
                            parcelData[8].trim()
                    );

            String riderId =
                    parcelData[9].trim();


            // Validate required fields
            if (parcelName.isEmpty()
                    || receiverAddress.isEmpty()
                    || receiverPhone.isEmpty()
                    || parcelId.isEmpty()
                    || senderEmail.isEmpty()
                    || senderId.isEmpty()
                    || parcelStatus.isEmpty()) {

                return null;
            }


            // Validate numeric values
            if (!Double.isFinite(weight)
                    || weight <= 0) {

                return null;
            }

            if (!Double.isFinite(deliveryCharge)
                    || deliveryCharge < 0) {

                return null;
            }


            // Validate status
            try {

                Parcel.ParcelStatus.valueOf(
                        parcelStatus
                );

            } catch (IllegalArgumentException e) {

                return null;
            }



            User user =
                    new User(
                            "",
                            senderEmail,
                            ""
                    );

            user.setUser_id(senderId);

            user.setUser_role(
                    String.valueOf(
                            User.UserRole.USER
                    )
            );


            // Create parcel
            Parcel parcel =
                    new Parcel(user);


            // Set stored information
            parcel.setParcelName(parcelName);

            parcel.setReciverAddress(
                    receiverAddress
            );

            parcel.setReciverPhone(
                    receiverPhone
            );

            parcel.setParcelID(parcelId);

            parcel.setWeight(weight);

            parcel.setSenderEmail(
                    senderEmail
            );

            parcel.setSenderId(
                    senderId
            );

            parcel.setParcelStatus(
                    parcelStatus
            );

            parcel.setDeliveryCharge(
                    deliveryCharge
            );

            if (riderId.equalsIgnoreCase("null")
                    || riderId.isEmpty()) {

                parcel.setRiderId(null);

            } else {

                parcel.setRiderId(riderId);
            }

            return parcel;

        } catch (NumberFormatException e) {

            System.err.println(
                    "Error parsing numeric values in parcel row: "
                            + e.getMessage()
            );

            return null;
        }
    }


    private static String removeRecordEnd(
            String data
    ) {

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



    private static boolean rewriteDatabase(
            ArrayList<String> parcels
    ) {

        File file = new File(PARCEL_FILE);

        try {

            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {

                if (!parent.mkdirs()) {
                    return false;
                }
            }

            try (FileWriter writer =
                         new FileWriter(file, false)) {

                for (String parcel : parcels) {

                    if (parcel == null
                            || parcel.trim().isEmpty()) {
                        continue;
                    }

                    String cleanParcel =
                            removeRecordEnd(parcel);

                    writer.write(
                            cleanParcel
                                    + RECORD_END
                                    + System.lineSeparator()
                    );
                }
            }

            return true;

        } catch (IOException e) {

            System.err.println(
                    "Exception while rewriting ParcelFile: "
                            + e.getMessage()
            );

            return false;
        }
    }
}