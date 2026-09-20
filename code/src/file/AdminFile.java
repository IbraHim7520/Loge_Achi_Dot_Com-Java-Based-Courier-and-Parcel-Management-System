package file;

import model.Parcel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminFile {

    private static final String USER_FILE = "code/src/database/users_db.txt";
    private static final String PARCEL_FILE = "code/src/database/parcel_db.txt";
    private static final String RIDER_FILE = "code/src/database/rider_db.txt";


    public ArrayList<Parcel> getAllParcelsList() {

        ArrayList<Parcel> parcelList = new ArrayList<>();

        try {

            File myf = new File(PARCEL_FILE);

            if (!myf.exists()) {
                return parcelList;
            }

            Scanner sc = new Scanner(myf);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length == 9) {

                    String parcel_id = parts[0];
                    String sender_id = parts[1];
                    String receiver_name = parts[2];
                    String receiver_phone = parts[3];
                    String receiver_address = parts[4];

                    double weight =Double.parseDouble(parts[5]);
                    double delivery_charge =Double.parseDouble(parts[6]);
                    String parcel_status = parts[7];
                    String rider_id = parts[8];

                    Parcel parcel = new Parcel(
                            sender_id,
                            receiver_name,
                            receiver_phone,
                            receiver_address,
                            weight
                    );

                    parcel.setParcel_id(parcel_id);
                    parcel.setDelivery_charge(delivery_charge);
                    parcel.setParcel_status(parcel_status);

                    if (rider_id.equals("null")) {
                        parcel.setRider_id(null);
                    } else {
                        parcel.setRider_id(rider_id);
                    }

                    parcelList.add(parcel);
                }
            }

            sc.close();

        } catch (IOException e) {

            System.out.println(
                    "Error reading parcel file: "
                            + e.getMessage()
            );
        }

        return parcelList;
    }
}