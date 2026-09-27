import custom_exception.InvalidAmountException;
import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import model.Admin;
import model.AdminStatistics;
import model.Parcel;
import model.Rider;
import model.User;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n========================================");
        System.out.println("       PARCEL MANAGEMENT SYSTEM");
        System.out.println("          FULL PROJECT TEST");
        System.out.println("========================================");


        // =========================================================
        // 1. CREATE USERS
        // =========================================================

        System.out.println("\n\n========== 1. CREATE USERS ==========");

        User admin1 = new User(
                "Admin One",
                "admin1@gmail.com",
                "12345678"
        );

        User admin2 = new User(
                "Admin Two",
                "admin2@gmail.com",
                "12345678"
        );

        User rider1 = new User(
                "Rider One",
                "rider1@gmail.com",
                "12345678"
        );

        User rider2 = new User(
                "Rider Two",
                "rider2@gmail.com",
                "12345678"
        );

        User user1 = new User(
                "Ibrahim",
                "ibrahim723@gmail.com",
                "12345678"
        );

        User user2 = new User(
                "User Two",
                "user2@gmail.com",
                "12345678"
        );

        User user3 = new User(
                "User Three",
                "user3@gmail.com",
                "12345678"
        );


        // =========================================================
        // 2. REGISTER 3 NORMAL USERS
        // =========================================================

        System.out.println("\n\n========== 2. USER REGISTRATION ==========");

        System.out.println(user1.register(user1));
        System.out.println(user2.register(user2));
        System.out.println(user3.register(user3));


        // =========================================================
        // 3. REGISTER ADMIN + RIDER
        // =========================================================
        // User.register() always creates USER role.
        // So Admin/Rider records are created manually here
        // for testing the role-based system.

        System.out.println("\n\n========== 3. ADMIN/RIDER SETUP ==========");

        admin1.setUser_role(String.valueOf(User.UserRole.ADMIN));
        admin1.setUser_id("ADMIN001");

        admin2.setUser_role(String.valueOf(User.UserRole.ADMIN));
        admin2.setUser_id("ADMIN002");

        rider1.setUser_role(String.valueOf(User.UserRole.RIDER));
        rider1.setUser_id("RIDER001");

        rider2.setUser_role(String.valueOf(User.UserRole.RIDER));
        rider2.setUser_id("RIDER002");

        System.out.println("Admin 1 created: " + admin1.getUser_id());
        System.out.println("Admin 2 created: " + admin2.getUser_id());
        System.out.println("Rider 1 created: " + rider1.getUser_id());
        System.out.println("Rider 2 created: " + rider2.getUser_id());


        // =========================================================
        // 4. LOGIN TEST
        // =========================================================

        System.out.println("\n\n========== 4. LOGIN TEST ==========");

        try {

            User loginUser1 = new User(
                    "ibrahim723@gmail.com",
                    "12345678"
            );

            System.out.println(
                    loginUser1.login(loginUser1)
            );

        } catch (NotFoundException e) {

            System.out.println(
                    "Login Exception: " + e.getMessage()
            );
        }


        // =========================================================
        // 5. CREATE ROLE OBJECTS
        // =========================================================

        System.out.println("\n\n========== 5. ROLE OBJECT CREATION ==========");

        try {

            Admin admin = new Admin(admin1);
            Rider riderA = new Rider(rider1);
            Rider riderB = new Rider(rider2);

            System.out.println("Admin object created successfully.");
            System.out.println("Rider 1 object created successfully.");
            System.out.println("Rider 2 object created successfully.");


            // =====================================================
            // 6. CREATE 10 PARCELS
            // =====================================================

            System.out.println("\n\n========== 6. CREATE 10 PARCELS ==========");

            Parcel p1 = new Parcel(user1);
            Parcel p2 = new Parcel(user1);
            Parcel p3 = new Parcel(user1);
            Parcel p4 = new Parcel(user1);
            Parcel p5 = new Parcel(user1);

            Parcel p6 = new Parcel(user2);
            Parcel p7 = new Parcel(user2);
            Parcel p8 = new Parcel(user2);
            Parcel p9 = new Parcel(user2);
            Parcel p10 = new Parcel(user2);


            // USER 1 - 5 PARCELS

            System.out.println(
                    "P1: " +
                            p1.sendOneParcel(
                                    "Laptop",
                                    "Dhaka 1206",
                                    "01711111111",
                                    2.0
                            )
            );

            System.out.println(
                    "P2: " +
                            p2.sendOneParcel(
                                    "Mobile Phone",
                                    "Mirpur 10",
                                    "01722222222",
                                    1.0
                            )
            );

            System.out.println(
                    "P3: " +
                            p3.sendOneParcel(
                                    "Books",
                                    "Dhanmondi",
                                    "01733333333",
                                    3.0
                            )
            );

            System.out.println(
                    "P4: " +
                            p4.sendOneParcel(
                                    "Clothes",
                                    "Uttara",
                                    "01744444444",
                                    4.0
                            )
            );

            System.out.println(
                    "P5: " +
                            p5.sendOneParcel(
                                    "Computer Parts",
                                    "Banani",
                                    "01755555555",
                                    5.0
                            )
            );


            // USER 2 - 5 PARCELS

            System.out.println(
                    "P6: " +
                            p6.sendOneParcel(
                                    "Monitor",
                                    "Mohakhali",
                                    "01811111111",
                                    6.0
                            )
            );

            System.out.println(
                    "P7: " +
                            p7.sendOneParcel(
                                    "Keyboard",
                                    "Bashundhara",
                                    "01822222222",
                                    1.5
                            )
            );

            System.out.println(
                    "P8: " +
                            p8.sendOneParcel(
                                    "Headphone",
                                    "Khilgaon",
                                    "01833333333",
                                    0.5
                            )
            );

            System.out.println(
                    "P9: " +
                            p9.sendOneParcel(
                                    "Camera",
                                    "Gulshan",
                                    "01844444444",
                                    2.5
                            )
            );

            System.out.println(
                    "P10: " +
                            p10.sendOneParcel(
                                    "Printer",
                                    "Wari",
                                    "01855555555",
                                    7.0
                            )
            );


            // =====================================================
            // 7. DISPLAY GENERATED PARCEL IDs
            // =====================================================

            System.out.println("\n\n========== GENERATED PARCEL IDs ==========");

            System.out.println("P1  = " + p1.getParcelID());
            System.out.println("P2  = " + p2.getParcelID());
            System.out.println("P3  = " + p3.getParcelID());
            System.out.println("P4  = " + p4.getParcelID());
            System.out.println("P5  = " + p5.getParcelID());
            System.out.println("P6  = " + p6.getParcelID());
            System.out.println("P7  = " + p7.getParcelID());
            System.out.println("P8  = " + p8.getParcelID());
            System.out.println("P9  = " + p9.getParcelID());
            System.out.println("P10 = " + p10.getParcelID());


            // =====================================================
            // 8. USER 1 VIEW ALL PARCELS
            // =====================================================

            System.out.println("\n\n========== 8. USER 1 PARCELS ==========");

            try {

                ArrayList<String> user1Parcels =
                        user1ParcelList(user1);

                for (String parcel : user1Parcels) {
                    System.out.println(parcel);
                }

            } catch (NotFoundException e) {

                System.out.println(
                        "User 1 Parcel Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 9. USER 2 VIEW ALL PARCELS
            // =====================================================

            System.out.println("\n\n========== 9. USER 2 PARCELS ==========");

            try {

                ArrayList<String> user2Parcels =
                        user2ParcelList(user2);

                for (String parcel : user2Parcels) {
                    System.out.println(parcel);
                }

            } catch (NotFoundException e) {

                System.out.println(
                        "User 2 Parcel Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 10. USER CANCEL PARCEL
            // =====================================================

            System.out.println("\n\n========== 10. USER CANCEL PARCEL ==========");

            try {

                boolean cancelResult =
                        user1ParcelCancel(
                                user1,
                                p2.getParcelID()
                        );

                System.out.println(
                        "Cancel P2 Result: " + cancelResult
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Cancel Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 11. USER TRACK PARCEL
            // =====================================================

            System.out.println("\n\n========== 11. TRACK PARCEL ==========");

            try {

                Parcel trackResult =
                        p1.trackParcel(p1.getParcelID());

                System.out.println(
                        "Tracking P1:"
                );

                displayParcel(trackResult);

            } catch (NotFoundException e) {

                System.out.println(
                        "Track Exception: " + e.getMessage()
                );

            } catch (UnauthorizedAccessException e) {

                System.out.println(
                        "Track Authorization Exception: "
                                + e.getMessage()
                );

            } catch (InvalidAmountException e) {

                System.out.println(
                        "Track Amount Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 12. RIDER VIEW PENDING PARCELS
            // =====================================================

            System.out.println("\n\n========== 12. RIDER PENDING PARCELS ==========");

            try {

                riderA.viewPendingParcels();

            } catch (NotFoundException e) {

                System.out.println(
                        "Pending Parcel Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 13. RIDER 1 ACCEPT P3
            // =====================================================

            System.out.println("\n\n========== 13. RIDER ACCEPT P3 ==========");

            try {

                riderA.acceptParcelsByRider(
                        p3.getParcelID(),
                        rider1.getUser_id()
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Accept Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 14. RIDER 1 ACCEPT P4
            // =====================================================

            System.out.println("\n\n========== 14. RIDER ACCEPT P4 ==========");

            try {

                riderA.acceptParcelsByRider(
                        p4.getParcelID(),
                        rider1.getUser_id()
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Accept Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 15. RIDER 2 ACCEPT P6
            // =====================================================

            System.out.println("\n\n========== 15. RIDER 2 ACCEPT P6 ==========");

            try {

                riderB.acceptParcelsByRider(
                        p6.getParcelID(),
                        rider2.getUser_id()
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Accept Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 16. RIDER 1 VIEW ASSIGNED PARCELS
            // =====================================================

            System.out.println(
                    "\n\n========== 16. RIDER 1 ASSIGNED PARCELS =========="
            );

            try {

                riderA.viewMyAssignedParcles();

            } catch (NotFoundException e) {

                System.out.println(
                        "Assigned Parcel Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 17. RIDER UPDATE P3 STATUS
            // =====================================================

            System.out.println(
                    "\n\n========== 17. RIDER UPDATE P3 =========="
            );

            try {

                riderA.updateParcelStatus(
                        p3.getParcelID(),
                        "ON_TRANSIT"
                );

                riderA.updateParcelStatus(
                        p3.getParcelID(),
                        "REACHED_DESTINATION"
                );

                riderA.updateParcelStatus(
                        p3.getParcelID(),
                        "DELIVERED"
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Status Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 18. RIDER UPDATE P4
            // =====================================================

            System.out.println(
                    "\n\n========== 18. RIDER UPDATE P4 =========="
            );

            try {

                riderA.updateParcelStatus(
                        p4.getParcelID(),
                        "ON_TRANSIT"
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Status Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 19. RIDER 2 UPDATE P6
            // =====================================================

            System.out.println(
                    "\n\n========== 19. RIDER 2 UPDATE P6 =========="
            );

            try {

                riderB.updateParcelStatus(
                        p6.getParcelID(),
                        "ON_TRANSIT"
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Status Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 20. ADMIN VIEW ALL USERS
            // =====================================================

            System.out.println(
                    "\n\n========== 20. ADMIN VIEW ALL USERS =========="
            );

            try {

                admin.viewAllUser();

            } catch (NotFoundException e) {

                System.out.println(
                        "Admin User Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 21. ADMIN VIEW ALL RIDERS
            // =====================================================

            System.out.println(
                    "\n\n========== 21. ADMIN VIEW ALL RIDERS =========="
            );

            try {

                ArrayList<User> riders =
                        file.AdminFile.getAllRiders();

                for (User rider : riders) {

                    System.out.println(
                            rider.getUser_id()
                                    + " | "
                                    + rider.getUser_name()
                                    + " | "
                                    + rider.getUser_email()
                    );
                }

            } catch (NotFoundException e) {

                System.out.println(
                        "Rider Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 22. ADMIN VIEW ALL PARCELS
            // =====================================================

            System.out.println(
                    "\n\n========== 22. ADMIN VIEW ALL PARCELS =========="
            );

            try {

                admin.viewAllParcel();

            } catch (Exception e) {

                System.out.println(
                        "Admin Parcel Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 23. ADMIN SEARCH USER
            // =====================================================

            System.out.println(
                    "\n\n========== 23. ADMIN SEARCH USER =========="
            );

            try {

                admin.searchUser(
                        user1.getUser_id()
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Search User Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 24. ADMIN SEARCH PARCEL
            // =====================================================

            System.out.println(
                    "\n\n========== 24. ADMIN SEARCH PARCEL =========="
            );

            try {

                admin.searchParcel(
                        p4.getParcelID()
                );

            } catch (Exception e) {

                System.out.println(
                        "Search Parcel Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 25. ADMIN UPDATE PARCEL STATUS
            // =====================================================

            System.out.println(
                    "\n\n========== 25. ADMIN UPDATE P5 =========="
            );

            try {

                admin.updateParcelStatus(
                        p5.getParcelID(),
                        "DELIVERED"
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Admin Status Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 26. ADMIN UPDATE USER
            // =====================================================

            System.out.println(
                    "\n\n========== 26. ADMIN UPDATE USER =========="
            );

            try {

                admin.updateUser(
                        user3.getUser_id(),
                        "User Three Updated",
                        "user3updated@gmail.com",
                        "123456789"
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Update User Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 27. ADMIN REGISTER NEW RIDER
            // =====================================================

            System.out.println(
                    "\n\n========== 27. ADMIN REGISTER NEW RIDER =========="
            );

            try {

                admin.registerNewRider(
                        "Rider Three",
                        "rider3@gmail.com",
                        "12345678"
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Register Rider Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 28. ADMIN DELETE PARCEL
            // =====================================================

            System.out.println(
                    "\n\n========== 28. ADMIN DELETE P7 =========="
            );

            try {

                admin.deleteParcel(
                        p7.getParcelID()
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Delete Parcel Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 29. USER DELETE CANCELED PARCEL
            // =====================================================

            System.out.println(
                    "\n\n========== 29. USER DELETE P2 =========="
            );

            try {

                boolean result =
                        user1ParcelDelete(
                                user1,
                                p2.getParcelID()
                        );

                System.out.println(
                        "User Delete P2 Result: "
                                + result
                );

            } catch (Exception e) {

                System.out.println(
                        "Delete Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 30. ADMIN DELETE USER
            // =====================================================

            System.out.println(
                    "\n\n========== 30. ADMIN DELETE USER =========="
            );

            try {

                admin.deleteUser(
                        user3.getUser_id()
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Delete User Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 31. ADMIN STATISTICS
            // =====================================================

            System.out.println(
                    "\n\n========== 31. ADMIN STATISTICS =========="
            );

            AdminStatistics statistics =
                    new AdminStatistics();

            statistics.displayStatistics();


            // =====================================================
            // 32. FINAL PARCEL STATUS CHECK
            // =====================================================

            System.out.println(
                    "\n\n========== 32. FINAL PARCEL STATUS =========="
            );

            try {

                System.out.println(
                        "P1: "
                                + p1.trackParcel(
                                p1.getParcelID()
                        )
                );

                System.out.println(
                        "P3: "
                                + p3.trackParcel(
                                p3.getParcelID()
                        )
                );

                System.out.println(
                        "P4: "
                                + p4.trackParcel(
                                p4.getParcelID()
                        )
                );

                System.out.println(
                        "P5: "
                                + p5.trackParcel(
                                p5.getParcelID()
                        )
                );

                System.out.println(
                        "P6: "
                                + p6.trackParcel(
                                p6.getParcelID()
                        )
                );

                System.out.println(
                        "P8: "
                                + p8.trackParcel(
                                p8.getParcelID()
                        )
                );

                System.out.println(
                        "P9: "
                                + p9.trackParcel(
                                p9.getParcelID()
                        )
                );

                System.out.println(
                        "P10: "
                                + p10.trackParcel(
                                p10.getParcelID()
                        )
                );

            } catch (NotFoundException e) {

                System.out.println(
                        "Final Track Exception: "
                                + e.getMessage()
                );

            } catch (UnauthorizedAccessException e) {

                System.out.println(
                        "Final Track Authorization Exception: "
                                + e.getMessage()
                );

            } catch (InvalidAmountException e) {

                System.out.println(
                        "Final Track Amount Exception: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 33. INVALID AMOUNT EXCEPTION TEST
            // =====================================================

            System.out.println(
                    "\n\n========== 33. INVALID AMOUNT EXCEPTION =========="
            );

            try {

                Parcel testParcel =
                        new Parcel(user1);

                testParcel.setWeight(-5);

            } catch (InvalidAmountException e) {

                System.out.println(
                        "InvalidAmountException WORKING: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 34. UNAUTHORIZED ACCESS EXCEPTION TEST
            // =====================================================

            System.out.println(
                    "\n\n========== 34. UNAUTHORIZED ACCESS TEST =========="
            );

            try {

                Admin wrongAdmin =
                        new Admin(user1);

            } catch (UnauthorizedAccessException e) {

                System.out.println(
                        "UnauthorizedAccessException WORKING: "
                                + e.getMessage()
                );
            }


            try {

                Rider wrongRider =
                        new Rider(user1);

            } catch (UnauthorizedAccessException e) {

                System.out.println(
                        "UnauthorizedAccessException WORKING: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // 35. NOT FOUND EXCEPTION TEST
            // =====================================================

            System.out.println(
                    "\n\n========== 35. NOT FOUND EXCEPTION TEST =========="
            );

            try {

                admin.searchUser("INVALID_USER_ID");

            } catch (NotFoundException e) {

                System.out.println(
                        "NotFoundException WORKING: "
                                + e.getMessage()
                );
            }


            try {

                admin.searchParcel("INVALID_PARCEL_ID");

            } catch (Exception e) {

                System.out.println(
                        "NotFoundException WORKING: "
                                + e.getMessage()
                );
            }


            // =====================================================
            // FINAL
            // =====================================================

            System.out.println(
                    "\n\n========================================"
            );

            System.out.println(
                    "       FULL TEST COMPLETED"
            );

            System.out.println(
                    "========================================"
            );


        } catch (UnauthorizedAccessException e) {

            System.out.println(
                    "\nROLE AUTHORIZATION ERROR: "
                            + e.getMessage()
            );

        } catch (InvalidAmountException e) {

            System.out.println(
                    "\nINVALID AMOUNT ERROR: "
                            + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "\nUNEXPECTED ERROR: "
                            + e.getMessage()
            );
        }
    }


    // =============================================================
    // HELPER METHODS
    // =============================================================

    private static ArrayList<String> user1ParcelList(
            User user
    ) throws NotFoundException, UnauthorizedAccessException {

        Parcel parcel = new Parcel(user);

        return parcel.getMyAllParcels();
    }


    private static ArrayList<String> user2ParcelList(
            User user
    ) throws NotFoundException, UnauthorizedAccessException {

        Parcel parcel = new Parcel(user);

        return parcel.getMyAllParcels();
    }


    private static boolean user1ParcelCancel(
            User user,
            String parcelId
    ) throws NotFoundException, UnauthorizedAccessException {

        Parcel parcel = new Parcel(user);

        return parcel.cancelParcel(parcelId);
    }


    private static boolean user1ParcelDelete(
            User user,
            String parcelId
    ) throws NotFoundException, UnauthorizedAccessException {

        Parcel parcel = new Parcel(user);

        return parcel.deleteMyParcel(parcelId);
    }


    private static void displayParcel(Parcel parcel) {

        System.out.println("Parcel ID: " + parcel.getParcelID());
        System.out.println("Parcel Name: " + parcel.getParcelName());
        System.out.println("Receiver Address: " + parcel.getReciverAddress());
        System.out.println("Receiver Phone: " + parcel.getReciverPhone());
        System.out.println("Weight: " + parcel.getWeight());
        System.out.println("Sender Email: " + parcel.getSenderEmail());
        System.out.println("Sender ID: " + parcel.getSenderId());
        System.out.println("Status: " + parcel.getParcelStatus());
        System.out.println("Delivery Charge: " + parcel.getDeliveryCharge());
        System.out.println("Rider ID: " + parcel.getRiderId());
    }
}