package file;

import custom_exception.NotFoundException;
import model.Parcel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RiderFile {

    private static final String PARCEL_FILE =
            "code/src/database/parcel_db.txt";

    private static final String SEPARATOR = "|";
    private static final String RECORD_END = "#";



    public static ArrayList<String> getPendingParcels()
            throws NotFoundException {

        ArrayList<String> pendingParcels =

                new ArrayList<>();

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {

            throw new NotFoundException(
                    "Parcel database not found!"
            );
        }

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String parcelData =
                        sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                String cleanData =
                        removeRecordEnd(parcelData);

                String[] parcel =
                        cleanData.split("\\|", -1);


                if (parcel.length >= 10
                        && parcel[7].equalsIgnoreCase(
                        String.valueOf(
                                Parcel.ParcelStatus.PENDING
                        )
                )) {

                    pendingParcels.add(cleanData);
                }
            }

        } catch (IOException e) {

            throw new NotFoundException(
                    "Unable to read parcel database!"
            );
        }

        if (pendingParcels.isEmpty()) {

            throw new NotFoundException(
                    "No pending parcels found!"
            );
        }

        return pendingParcels;
    }


    public static ArrayList<String> getMyAssignedParcels(
            String riderId
    ) throws NotFoundException {

        if (riderId == null
                || riderId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Invalid rider ID!"
            );
        }

        ArrayList<String> assignedParcels =
                new ArrayList<>();

        File file = new File(PARCEL_FILE);

        if (!file.exists()) {

            throw new NotFoundException(
                    "Parcel database not found!"
            );
        }

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String parcelData =
                        sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                String cleanData =
                        removeRecordEnd(parcelData);

                String[] parcel =
                        cleanData.split("\\|", -1);

                if (parcel.length >= 10
                        && parcel[9].equals(
                        riderId.trim())) {

                    assignedParcels.add(cleanData);
                }
            }

        } catch (IOException e) {

            throw new NotFoundException(
                    "Unable to read parcel database!"
            );
        }

        if (assignedParcels.isEmpty()) {

            throw new NotFoundException(
                    "No assigned parcels found!"
            );
        }

        return assignedParcels;
    }


    public static boolean assignParcel(
            String parcelId,
            String riderId
    ) throws NotFoundException {

        if (parcelId == null
                || parcelId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Invalid parcel ID!"
            );
        }

        if (riderId == null
                || riderId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Invalid rider ID!"
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

        boolean assigned = false;

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String parcelData =
                        sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                String cleanData =
                        removeRecordEnd(parcelData);

                String[] arr =
                        cleanData.split("\\|", -1);



                if (arr.length >= 10
                        && arr[3].equals(
                        parcelId.trim())
                        && arr[7].equalsIgnoreCase(
                        String.valueOf(
                                Parcel.ParcelStatus.PENDING
                        )
                )) {

                    arr[7] =
                            String.valueOf(
                                    Parcel.ParcelStatus.ACCEPTED
                            );

                    arr[9] =
                            riderId.trim();

                    cleanData =
                            String.join(
                                    SEPARATOR,
                                    arr
                            );

                    assigned = true;
                }

                parcels.add(cleanData);
            }

        } catch (IOException e) {

            throw new NotFoundException(
                    "Unable to read parcel database!"
            );
        }

        if (!assigned) {

            throw new NotFoundException(
                    "Parcel not found or parcel is not pending!"
            );
        }

        return rewriteDatabase(parcels);
    }



    public static boolean updateParcelStatusByRider(
            String parcelId,
            String riderId,
            String newStatus
    ) throws NotFoundException {

        if (parcelId == null
                || parcelId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Invalid parcel ID!"
            );
        }

        if (riderId == null
                || riderId.trim().isEmpty()) {

            throw new NotFoundException(
                    "Invalid rider ID!"
            );
        }

        if (newStatus == null
                || newStatus.trim().isEmpty()) {

            throw new NotFoundException(
                    "Invalid parcel status!"
            );
        }

        newStatus =
                newStatus.trim().toUpperCase();


        // Validate status
        try {

            Parcel.ParcelStatus.valueOf(
                    newStatus
            );

        } catch (IllegalArgumentException e) {

            throw new NotFoundException(
                    "Invalid parcel status!"
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

        boolean updated = false;

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String parcelData =
                        sc.nextLine().trim();

                if (parcelData.isEmpty()) {
                    continue;
                }

                String cleanData =
                        removeRecordEnd(parcelData);

                String[] arr =
                        cleanData.split("\\|", -1);

                /*
                 * Update only when:
                 *
                 * Parcel ID matches
                 * AND
                 * Rider ID matches
                 */

                if (arr.length >= 10
                        && arr[3].equals(
                        parcelId.trim())
                        && arr[9].equals(
                        riderId.trim())) {

                    arr[7] = newStatus;

                    cleanData =
                            String.join(
                                    SEPARATOR,
                                    arr
                            );

                    updated = true;
                }

                parcels.add(cleanData);
            }

        } catch (IOException e) {

            throw new NotFoundException(
                    "Unable to read parcel database!"
            );
        }

        if (!updated) {

            throw new NotFoundException(
                    "Parcel not found or parcel is not assigned to this rider!"
            );
        }

        return rewriteDatabase(parcels);
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

            File parent =
                    file.getParentFile();

            if (parent != null
                    && !parent.exists()) {

                if (!parent.mkdirs()) {
                    return false;
                }
            }

            try (FileWriter writer =
                         new FileWriter(
                                 file,
                                 false
                         )) {

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
                    "Exception while rewriting parcel database: "
                            + e.getMessage()
            );

            return false;
        }
    }
}