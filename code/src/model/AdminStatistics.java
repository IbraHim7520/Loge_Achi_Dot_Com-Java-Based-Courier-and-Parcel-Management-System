package model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AdminStatistics {

    private static final String USER_FILE =
            "code/src/database/users_db.txt";

    private static final String PARCEL_FILE =
            "code/src/database/parcel_db.txt";

    private static final String SEPARATOR = "|";

    private static final String RECORD_END = "#";

    private int total_parcels;
    private int total_users;
    private int total_riders;
    private int total_delivered_parcels;
    private int total_canceled_parcels;

    public AdminStatistics() {
        loadStatistics();
    }

    public void loadStatistics() {

        total_parcels = 0;
        total_users = 0;
        total_riders = 0;
        total_delivered_parcels = 0;
        total_canceled_parcels = 0;

        loadUserStatistics();
        loadParcelStatistics();
    }


    private void loadUserStatistics() {

        File file =
                new File(USER_FILE);

        if (!file.exists()) {
            return;
        }

        try (Scanner scanner =
                     new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String data =
                        scanner.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                data =
                        removeRecordEnd(data);

                String[] userData =
                        data.split(
                                "\\" + SEPARATOR,
                                -1
                        );

                if (userData.length >= 5) {

                    String role =
                            userData[3].trim();

                    if (role.equalsIgnoreCase(
                            String.valueOf(
                                    User.UserRole.USER
                            )
                    )) {

                        total_users++;

                    } else if (role.equalsIgnoreCase(
                            String.valueOf(
                                    User.UserRole.RIDER
                            )
                    )) {

                        total_riders++;
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Exception from AdminStatistics: "
                            + e.getMessage()
            );
        }
    }
    private void loadParcelStatistics() {

        File file =
                new File(PARCEL_FILE);

        if (!file.exists()) {
            return;
        }

        try (Scanner scanner =
                     new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String data =
                        scanner.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                data =
                        removeRecordEnd(data);

                String[] parcelData =
                        data.split(
                                "\\" + SEPARATOR,
                                -1
                        );

                if (parcelData.length >= 10) {

                    total_parcels++;

                    String status =
                            parcelData[7].trim();

                    if (status.equalsIgnoreCase(
                            String.valueOf(
                                    Parcel.ParcelStatus.DELIVERED
                            )
                    )) {

                        total_delivered_parcels++;
                    }

                    if (status.equalsIgnoreCase(
                            String.valueOf(
                                    Parcel.ParcelStatus.CANCELED
                            )
                    )) {

                        total_canceled_parcels++;
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Exception from AdminStatistics: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // REMOVE RECORD END
    // =====================================================

    private String removeRecordEnd(
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

    // =====================================================
    // GETTERS
    // =====================================================

    public int getTotal_parcels() {
        return total_parcels;
    }

    public int getTotal_users() {
        return total_users;
    }

    public int getTotal_riders() {
        return total_riders;
    }

    public int getTotal_delivered_parcels() {
        return total_delivered_parcels;
    }

    public int getTotal_canceled_parcels() {
        return total_canceled_parcels;
    }

    // =====================================================
    // DISPLAY
    // =====================================================

    public void displayStatistics() {

        System.out.println(
                "\n========== ADMIN STATISTICS =========="
        );

        System.out.println(
                "Total Users             : "
                        + total_users
        );

        System.out.println(
                "Total Riders            : "
                        + total_riders
        );

        System.out.println(
                "Total Parcels           : "
                        + total_parcels
        );

        System.out.println(
                "Total Delivered Parcels : "
                        + total_delivered_parcels
        );

        System.out.println(
                "Total Canceled Parcels  : "
                        + total_canceled_parcels
        );

        System.out.println(
                "======================================"
        );
    }
}