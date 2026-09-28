import custom_exception.NotFoundException;
import file.AdminFile;
import file.ParcelFile;
import model.Admin;
import model.Parcel;
import model.Rider;
import model.User;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n");
        System.out.println("======================================================");
        System.out.println("        COURIER MANAGEMENT SYSTEM - FULL TEST");
        System.out.println("======================================================");


        // ==================================================
        // TEST 1: CREATE 5 USERS
        // ==================================================

        System.out.println("\n\n========== TEST 1: CREATE 5 USERS ==========");

        User user1 = createUser(
                "Test User 1",
                "user1@test.com",
                "123456"
        );

        User user2 = createUser(
                "Test User 2",
                "user2@test.com",
                "123456"
        );

        User user3 = createUser(
                "Test User 3",
                "user3@test.com",
                "123456"
        );

        User user4 = createUser(
                "Test User 4",
                "user4@test.com",
                "123456"
        );

        User user5 = createUser(
                "Test User 5",
                "user5@test.com",
                "123456"
        );


        // ==================================================
        // PRINT USER IDS
        // ==================================================

        System.out.println("\nCreated Users:");

        printUser(user1);
        printUser(user2);
        printUser(user3);
        printUser(user4);
        printUser(user5);


        // ==================================================
        // TEST 2: CREATE RIDER
        // ==================================================

        System.out.println("\n\n========== TEST 2: CREATE RIDER ==========");

        try {

            boolean riderCreated =
                    AdminFile.registerNewRider(
                            "Test Rider",
                            "rider@test.com",
                            "123456"
                    );

            if (riderCreated) {

                System.out.println(
                        "PASS: Rider created successfully."
                );

            } else {

                System.out.println(
                        "FAIL: Rider creation failed."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "FAIL: Rider creation error -> "
                            + e.getMessage()
            );
        }


        // ==================================================
        // FIND RIDER
        // ==================================================

        User riderUser = findRider();

        if (riderUser == null) {

            System.out.println(
                    "FAIL: Rider could not be found."
            );

            return;
        }

        System.out.println(
                "\nRider ID: "
                        + riderUser.getUser_id()
        );


        // ==================================================
        // TEST 3: CREATE 10 PARCELS
        // ==================================================

        System.out.println(
                "\n\n========== TEST 3: CREATE 10 PARCELS =========="
        );

        /*
         * User 1 sends 5 parcels.
         * User 2 sends 5 parcels.
         *
         * Total = 10 parcels.
         */

        createParcel(
                "P001",
                user1,
                "Laptop",
                "Dhaka",
                "01711111111",
                2.0,
                100
        );

        createParcel(
                "P002",
                user1,
                "Books",
                "Mohakhali",
                "01711111112",
                1.5,
                80
        );

        createParcel(
                "P003",
                user1,
                "Documents",
                "Gulshan",
                "01711111113",
                0.5,
                50
        );

        createParcel(
                "P004",
                user1,
                "Clothes",
                "Banani",
                "01711111114",
                3.0,
                120
        );

        createParcel(
                "P005",
                user1,
                "Shoes",
                "Mirpur",
                "01711111115",
                2.5,
                100
        );


        createParcel(
                "P006",
                user2,
                "Mobile",
                "Dhanmondi",
                "01722222221",
                0.8,
                70
        );

        createParcel(
                "P007",
                user2,
                "Watch",
                "Uttara",
                "01722222222",
                0.4,
                50
        );

        createParcel(
                "P008",
                user2,
                "Gift",
                "Bashundhara",
                "01722222223",
                1.2,
                80
        );

        createParcel(
                "P009",
                user2,
                "Computer Parts",
                "Farmgate",
                "01722222224",
                4.0,
                150
        );

        createParcel(
                "P010",
                user2,
                "Documents",
                "Tejgaon",
                "01722222225",
                0.3,
                50
        );


        // ==================================================
        // TEST 4: USER 1 PARCELS
        // ==================================================

        System.out.println(
                "\n\n========== TEST 4: USER 1 PARCELS =========="
        );

        try {

            ArrayList<String> user1Parcels =
                    ParcelFile.getMyAllParcels(
                            user1.getUser_id()
                    );

            System.out.println(
                    "User 1 parcel count = "
                            + user1Parcels.size()
            );

            for (String parcel :
                    user1Parcels) {

                System.out.println(parcel);
            }

        } catch (Exception e) {

            System.out.println(
                    "FAIL: "
                            + e.getMessage()
            );
        }


        // ==================================================
        // TEST 5: USER 2 PARCELS
        // ==================================================

        System.out.println(
                "\n\n========== TEST 5: USER 2 PARCELS =========="
        );

        try {

            ArrayList<String> user2Parcels =
                    ParcelFile.getMyAllParcels(
                            user2.getUser_id()
                    );

            System.out.println(
                    "User 2 parcel count = "
                            + user2Parcels.size()
            );

            for (String parcel :
                    user2Parcels) {

                System.out.println(parcel);
            }

        } catch (Exception e) {

            System.out.println(
                    "FAIL: "
                            + e.getMessage()
            );
        }


        // ==================================================
        // TEST 6: SEARCH PARCEL
        // ==================================================

        System.out.println(
                "\n\n========== TEST 6: SEARCH PARCEL =========="
        );

        try {

            Parcel parcel =
                    AdminFile.searchParcel("P001");

            System.out.println(
                    "PASS: P001 found."
            );

            printParcel(parcel);

        } catch (Exception e) {

            System.out.println(
                    "FAIL: "
                            + e.getMessage()
            );
        }


        // ==================================================
        // TEST 7: TRACK PARCEL
        // ==================================================

        System.out.println(
                "\n\n========== TEST 7: TRACK PARCEL =========="
        );

        try {

            Parcel tracked =
                    ParcelFile.trackParcel(
                            "P002",
                            user1.getUser_id()
                    );

            System.out.println(
                    "PASS: P002 tracked successfully."
            );

            printParcel(tracked);

        } catch (Exception e) {

            System.out.println(
                    "FAIL: "
                            + e.getMessage()
            );
        }


        // ==================================================
        // TEST 8: CANCEL PARCEL
        // ==================================================

        System.out.println(
                "\n\n========== TEST 8: CANCEL PARCEL =========="
        );

        try {

            boolean cancelled =
                    ParcelFile.cancelParcel(
                            "P003",
                            user1.getUser_id()
                    );

            if (cancelled) {

                System.out.println(
                        "PASS: P003 cancelled."
                );

            } else {

                System.out.println(
                        "FAIL: P003 cancellation failed."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "FAIL: "
                            + e.getMessage()
            );
        }


        // ==================================================
        // TEST 9: RIDER - VIEW PENDING PARCELS
        // ==================================================

        System.out.println(
                "\n\n========== TEST 9: RIDER PENDING PARCELS =========="
        );

        try {

            Rider rider =
                    new Rider(riderUser);

            rider.viewPendingParcels();

        } catch (Exception e) {

            System.out.println(
                    "FAIL: "
                            + e.getMessage()
            );
        }


        // ==================================================
        // TEST 10: RIDER - ACCEPT PARCEL
        // ==================================================

        System.out.println(
                "\n\n========== TEST 10: RIDER ACCEPT PARCEL =========="
        );

        try {

            Rider rider =
                    new Rider(riderUser);

            rider.acceptParcelsByRider("P001");

            System.out.println(
                    "PASS: Rider accepted P001."
            );

        } catch (Exception e) {

            System.out.println(
                    "FAIL: "
                            + e.getMessage()
            );
        }


        // ==================================================
        // TEST 11: RIDER - VIEW ASSIGNED
        // ==================================================

        System.out.println(
                "\n\n========== TEST 11: RIDER ASSIGNED PARCELS =========="
        );

        try {

            Rider rider =
                    new Rider(riderUser);

            rider.viewMyAssignedParcels();

        } catch (Exception e) {

            System.out.println(
                    "FAIL: "
                            + e.getMessage()
            );
        }


        // ==================================================
        // TEST 12: RIDER - UPDATE STATUS
        // ==================================================

        System.out.println(
                "\n\n========== TEST 12: RIDER STATUS UPDATE =========="
        );

        try {

            Rider rider =
                    new Rider(riderUser);

            rider.updateParcelStatus(
                    "P001",
                    "ON_TRANSIT"
            );

            System.out.println(
                    "PASS: P001 status changed to ON_TRANSIT."
            );

        } catch (Exception e) {

            System.out.println(
                    "FAIL: "
                            + e.getMessage()
            );
        }


        // ==================================================
        // TEST 13: ADMIN - VIEW USERS
        // ==================================================

        System.out.println(
                "\n\n========== TEST 13: ADMIN VIEW USERS =========="
        );

        User adminUser =
                findAdmin();

        if (adminUser == null) {

            System.out.println(
                    "ADMIN TEST SKIPPED."
            );

            System.out.println(
                    "No ADMIN account found in users_db.txt."
            );

        } else {

            try {

                Admin admin =
                        new Admin(adminUser);

                admin.viewAllUser();

            } catch (Exception e) {

                System.out.println(
                        "FAIL: "
                                + e.getMessage()
                );
            }
        }


        // ==================================================
        // TEST 14: ADMIN - SEARCH USER
        // ==================================================

        System.out.println(
                "\n\n========== TEST 14: ADMIN SEARCH USER =========="
        );

        if (adminUser != null) {

            try {

                Admin admin =
                        new Admin(adminUser);

                admin.searchUser(
                        user4.getUser_id()
                );

            } catch (Exception e) {

                System.out.println(
                        "FAIL: "
                                + e.getMessage()
                );
            }
        }


        // ==================================================
        // TEST 15: ADMIN - UPDATE USER
        // ==================================================

        System.out.println(
                "\n\n========== TEST 15: ADMIN UPDATE USER =========="
        );

        if (adminUser != null) {

            try {

                Admin admin =
                        new Admin(adminUser);

                admin.updateUser(
                        user4.getUser_id(),
                        "Updated User 4",
                        "updated4@test.com",
                        "123456"
                );

                System.out.println(
                        "PASS: User 4 updated."
                );

            } catch (Exception e) {

                System.out.println(
                        "FAIL: "
                                + e.getMessage()
                );
            }
        }


        // ==================================================
        // TEST 16: ADMIN - REGISTER ANOTHER RIDER
        // ==================================================

        System.out.println(
                "\n\n========== TEST 16: ADMIN REGISTER RIDER =========="
        );

        if (adminUser != null) {

            try {

                Admin admin =
                        new Admin(adminUser);

                admin.registerNewRider(
                        "Second Rider",
                        "rider2@test.com",
                        "123456"
                );

                System.out.println(
                        "PASS: Second rider registered."
                );

            } catch (Exception e) {

                System.out.println(
                        "FAIL: "
                                + e.getMessage()
                );
            }
        }


        // ==================================================
        // TEST 17: ADMIN - VIEW ALL PARCELS
        // ==================================================

        System.out.println(
                "\n\n========== TEST 17: ADMIN VIEW ALL PARCELS =========="
        );

        if (adminUser != null) {

            try {

                Admin admin =
                        new Admin(adminUser);

                admin.viewAllParcel();

            } catch (Exception e) {

                System.out.println(
                        "FAIL: "
                                + e.getMessage()
                );
            }
        }


        // ==================================================
        // TEST 18: ADMIN - SEARCH PARCEL
        // ==================================================

        System.out.println(
                "\n\n========== TEST 18: ADMIN SEARCH PARCEL =========="
        );

        if (adminUser != null) {

            try {

                Admin admin =
                        new Admin(adminUser);

                admin.searchParcel("P004");

            } catch (Exception e) {

                System.out.println(
                        "FAIL: "
                                + e.getMessage()
                );
            }
        }


        // ==================================================
        // TEST 19: ADMIN - UPDATE PARCEL STATUS
        // ==================================================

        System.out.println(
                "\n\n========== TEST 19: ADMIN UPDATE PARCEL =========="
        );

        if (adminUser != null) {

            try {

                Admin admin =
                        new Admin(adminUser);

                admin.updateParcelStatus(
                        "P004",
                        "REACHED_DESTINATION"
                );

                System.out.println(
                        "PASS: P004 status updated."
                );

            } catch (Exception e) {

                System.out.println(
                        "FAIL: "
                                + e.getMessage()
                );
            }
        }


        // ==================================================
        // TEST 20: ADMIN - DELETE PARCEL
        // ==================================================

        System.out.println(
                "\n\n========== TEST 20: ADMIN DELETE PARCEL =========="
        );

        if (adminUser != null) {

            try {

                Admin admin =
                        new Admin(adminUser);

                /*
                 * P010 is still PENDING.
                 * Good candidate for delete testing.
                 */

                admin.deleteParcel("P010");

                System.out.println(
                        "PASS: P010 deleted."
                );

            } catch (Exception e) {

                System.out.println(
                        "FAIL: "
                                + e.getMessage()
                );
            }
        }


        // ==================================================
        // TEST 21: ADMIN - DELETE USER
        // ==================================================

        System.out.println(
                "\n\n========== TEST 21: ADMIN DELETE USER =========="
        );

        if (adminUser != null) {

            try {

                Admin admin =
                        new Admin(adminUser);

                /*
                 * User 5 has no parcel.
                 * Therefore this should succeed.
                 */

                admin.deleteUser(
                        user5.getUser_id()
                );

                System.out.println(
                        "PASS: User 5 deleted."
                );

            } catch (Exception e) {

                System.out.println(
                        "FAIL: "
                                + e.getMessage()
                );
            }
        }


        // ==================================================
        // TEST 22: FINAL PARCEL SEARCH
        // ==================================================

        System.out.println(
                "\n\n========== TEST 22: FINAL DATABASE CHECK =========="
        );

        try {

            Parcel finalParcel =
                    AdminFile.searchParcel("P001");

            System.out.println(
                    "P001 final status: "
                            + finalParcel.getParcelStatus()
            );

            System.out.println(
                    "P001 rider: "
                            + finalParcel.getRiderId()
            );

        } catch (Exception e) {

            System.out.println(
                    "FAIL: "
                            + e.getMessage()
            );
        }


        // ==================================================
        // FINAL
        // ==================================================

        System.out.println("\n\n");
        System.out.println("======================================================");
        System.out.println("             FULL TEST COMPLETED");
        System.out.println("======================================================");
        System.out.println("Users created       : 5");
        System.out.println("Parcels created     : 10");
        System.out.println("User 1 parcels      : P001 - P005");
        System.out.println("User 2 parcels      : P006 - P010");
        System.out.println("Rider operations    : Tested");
        System.out.println("Admin operations    : Tested");
        System.out.println("Tracking            : Tested");
        System.out.println("Cancellation        : Tested");
        System.out.println("Parcel status       : Tested");
        System.out.println("Search              : Tested");
        System.out.println("Delete              : Tested");
        System.out.println("======================================================");
    }


    // =========================================================
    // CREATE USER
    // =========================================================

    static User createUser(
            String name,
            String email,
            String password
    ) {

        User user =
                new User(
                        name,
                        email,
                        password
                );

        try {

            String result =
                    user.register(user);

            System.out.println(
                    result
            );

        } catch (Exception e) {

            System.out.println(
                    "User creation failed: "
                            + e.getMessage()
            );
        }

        return user;
    }


    // =========================================================
    // CREATE PARCEL
    // =========================================================

    static void createParcel(
            String parcelId,
            User sender,
            String parcelName,
            String address,
            String phone,
            double weight,
            double charge
    ) {

        boolean result =
                ParcelFile.saveParcel(
                        parcelName,
                        address,
                        phone,
                        parcelId,
                        weight,
                        sender.getUser_email(),
                        sender.getUser_id(),
                        String.valueOf(
                                Parcel.ParcelStatus.PENDING
                        ),
                        charge,
                        null
                );

        if (result) {

            System.out.println(
                    "PASS: "
                            + parcelId
                            + " created for "
                            + sender.getUser_name()
            );

        } else {

            System.out.println(
                    "FAIL: "
                            + parcelId
            );
        }
    }


    // =========================================================
    // FIND RIDER
    // =========================================================

    static User findRider() {

        try {

            ArrayList<User> users =
                    AdminFile.getAllUsers();

            for (User user : users) {

                if (user.getUser_role()
                        != null
                        && user.getUser_role()
                        .equalsIgnoreCase("RIDER")) {

                    return user;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Rider search failed: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // FIND ADMIN
    // =========================================================

    static User findAdmin() {

        try {

            ArrayList<User> users =
                    AdminFile.getAllUsers();

            for (User user : users) {

                if (user.getUser_role()
                        != null
                        && user.getUser_role()
                        .equalsIgnoreCase("ADMIN")) {

                    return user;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Admin search failed: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // PRINT USER
    // =========================================================

    static void printUser(User user) {

        if (user == null) {

            System.out.println(
                    "User = null"
            );

            return;
        }

        System.out.println(
                "ID: "
                        + user.getUser_id()
                        + " | Name: "
                        + user.getUser_name()
                        + " | Email: "
                        + user.getUser_email()
                        + " | Role: "
                        + user.getUser_role()
        );
    }


    // =========================================================
    // PRINT PARCEL
    // =========================================================

    static void printParcel(Parcel parcel) {

        System.out.println(
                "------------------------------------------"
        );

        System.out.println(
                "Parcel ID: "
                        + parcel.getParcelID()
        );

        System.out.println(
                "Name: "
                        + parcel.getParcelName()
        );

        System.out.println(
                "Receiver Address: "
                        + parcel.getReciverAddress()
        );

        System.out.println(
                "Receiver Phone: "
                        + parcel.getReciverPhone()
        );

        System.out.println(
                "Weight: "
                        + parcel.getWeight()
        );

        System.out.println(
                "Sender Email: "
                        + parcel.getSenderEmail()
        );

        System.out.println(
                "Sender ID: "
                        + parcel.getSenderId()
        );

        System.out.println(
                "Status: "
                        + parcel.getParcelStatus()
        );

        System.out.println(
                "Delivery Charge: "
                        + parcel.getDeliveryCharge()
        );

        System.out.println(
                "Rider ID: "
                        + parcel.getRiderId()
        );

        System.out.println(
                "------------------------------------------"
        );
    }
}