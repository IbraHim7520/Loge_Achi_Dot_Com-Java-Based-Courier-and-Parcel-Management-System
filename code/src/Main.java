import file.RiderFile;
import model.Rider;
import model.User;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // =========================
        // USER LOGIN TEST
        // =========================

        User u1 = new User("karim@example.com", "karim123");

        String loginMessage = u1.login(u1);

        System.out.println("Login Result:");
        System.out.println(loginMessage);

        System.out.println("Current User ID: " + u1.USER_EXISTS);


        // =========================
        // CREATE RIDER TEST
        // =========================

        Rider rider1 = new Rider(
                "RIDER101",
                "Rahim",
                "01711111111",
                "Dhaka"
        );

        RiderFile riderFile = new RiderFile();

        boolean riderCreated = riderFile.createNewRider(rider1);

        System.out.println("\nRider Create Result:");
        System.out.println(riderCreated);


        // =========================
        // GET RIDER TEST
        // =========================

        Rider foundRider = riderFile.getRider("RIDER101");

        System.out.println("\nGet Rider Result:");

        if (foundRider != null) {

            System.out.println("Rider ID: " + foundRider.getRider_id());
            System.out.println("Name: " + foundRider.getRider_name());
            System.out.println("Phone: " + foundRider.getRider_phone());
            System.out.println("Address: " + foundRider.getRider_address());
            System.out.println("Status: " + foundRider.getRider_status());

        } else {

            System.out.println("Rider not found.");
        }


        // =========================
        // GET ALL RIDERS TEST
        // =========================

        ArrayList<Rider> riders = riderFile.getAllRiders();

        System.out.println("\nAll Riders:");

        for (Rider rider : riders) {

            System.out.println("--------------------");
            System.out.println("ID: " + rider.getRider_id());
            System.out.println("Name: " + rider.getRider_name());
            System.out.println("Phone: " + rider.getRider_phone());
            System.out.println("Address: " + rider.getRider_address());
            System.out.println("Status: " + rider.getRider_status());
        }


        // =========================
        // UPDATE RIDER STATUS TEST
        // =========================

        boolean statusUpdated = rider1.updateRiderStatus("Busy");

        System.out.println("\nUpdate Rider Status Result:");
        System.out.println(statusUpdated);

        Rider updatedRider = riderFile.getRider("RIDER101");

        if (updatedRider != null) {
            System.out.println("Updated Status: "
                    + updatedRider.getRider_status());
        }



        // UPDATE RIDER INFORMATION TEST

        rider1.setRider_name("Rahim Ahmed");
        rider1.setRider_phone("01899999999");
        rider1.setRider_address("Mirpur");

        boolean riderUpdated = riderFile.updateRider(rider1);

        System.out.println("\nUpdate Rider Information Result:");
        System.out.println(riderUpdated);



        // CHECK UPDATED RIDER
        Rider finalRider = riderFile.getRider("RIDER101");

        System.out.println("\nUpdated Rider Information:");

        if (finalRider != null) {

            System.out.println("ID: " + finalRider.getRider_id());
            System.out.println("Name: " + finalRider.getRider_name());
            System.out.println("Phone: " + finalRider.getRider_phone());
            System.out.println("Address: " + finalRider.getRider_address());
            System.out.println("Status: " + finalRider.getRider_status());

        }



        // REMOVE RIDER TEST
        boolean riderRemoved = riderFile.removeRider("RIDER101");

        System.out.println("\nRemove Rider Result:");
        System.out.println(riderRemoved);



        // CHECK AFTER REMOVE
        Rider deletedRider = riderFile.getRider("RIDER101");

        System.out.println("\nCheck After Remove:");

        if (deletedRider == null) {
            System.out.println("Rider successfully removed.");
        } else {
            System.out.println("Rider still exists.");
        }
    }
}