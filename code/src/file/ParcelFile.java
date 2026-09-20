package file;

import model.Parcel;

import java.io.*;
import java.util.ArrayList;

public class ParcelFile {

    private static final String PARCEL_FILE =
            "code/src/database/parcel_db.txt";


    // Save New Parcel
    public boolean createNewParcel(Parcel parcel) {

        try {

            File file = new File(PARCEL_FILE);

            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            String parcelId = generateParcelId();

            parcel.setParcel_id(parcelId);

            FileWriter writer = new FileWriter(file, true);

            writer.write(
                    parcel.getParcel_id() + "|" +
                            parcel.getSender_id() + "|" +
                            parcel.getReceiver_name() + "|" +
                            parcel.getReceiver_phone() + "|" +
                            parcel.getReceiver_address() + "|" +
                            parcel.getWeight() + "|" +
                            parcel.getDelivery_charge() + "|" +
                            parcel.getParcel_status() + "|" +
                            parcel.getRider_id() +
                            "\n"
            );

            writer.close();

            return true;

        } catch (IOException e) {

            System.out.println("Error saving parcel: " + e.getMessage());

            return false;
        }
    }


    // Get all parcels of a specific user
    public ArrayList<Parcel> getMyParcels(String sender_id) {

        ArrayList<Parcel> parcels = new ArrayList<>();

        try {

            File file = new File(PARCEL_FILE);

            if (!file.exists()) {
                return parcels;
            }

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length < 9) {
                    continue;
                }

                if (data[1].equals(sender_id)) {

                    Parcel parcel = createParcelFromData(data);

                    parcels.add(parcel);
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "Error reading parcels: " + e.getMessage()
            );
        }

        return parcels;
    }


    // Search Parcel by ID
    public Parcel searchParcelById(String parcel_id) {

        try {

            File file = new File(PARCEL_FILE);

            if (!file.exists()) {
                return null;
            }

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length < 9) {
                    continue;
                }

                if (data[0].equals(parcel_id)) {

                    reader.close();

                    return createParcelFromData(data);
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "Error searching parcel: " + e.getMessage()
            );
        }

        return null;
    }


    // Get all parcels assigned to a specific rider
    public ArrayList<Parcel> getAssignedParcels(String rider_id) {

        ArrayList<Parcel> parcels = new ArrayList<>();

        try {

            File file = new File(PARCEL_FILE);

            if (!file.exists()) {
                return parcels;
            }

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length < 9) {
                    continue;
                }

                if (data[8].equals(rider_id)) {

                    Parcel parcel = createParcelFromData(data);

                    parcels.add(parcel);
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "Error reading assigned parcels: " + e.getMessage()
            );
        }

        return parcels;
    }


    // Update existing parcel
    public boolean updateParcel(Parcel parcel) {

        if (parcel == null) {
            return false;
        }

        ArrayList<Parcel> parcels = new ArrayList<>();

        try {

            File file = new File(PARCEL_FILE);

            if (!file.exists()) {
                return false;
            }

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length < 9) {
                    continue;
                }

                Parcel currentParcel = createParcelFromData(data);

                if (currentParcel.getParcel_id()
                        .equals(parcel.getParcel_id())) {

                    parcels.add(parcel);
                    found = true;

                } else {

                    parcels.add(currentParcel);
                }
            }

            reader.close();

            if (!found) {
                return false;
            }

            FileWriter writer =
                    new FileWriter(file, false);

            for (Parcel p : parcels) {

                writer.write(
                        p.getParcel_id() + "|" +
                                p.getSender_id() + "|" +
                                p.getReceiver_name() + "|" +
                                p.getReceiver_phone() + "|" +
                                p.getReceiver_address() + "|" +
                                p.getWeight() + "|" +
                                p.getDelivery_charge() + "|" +
                                p.getParcel_status() + "|" +
                                p.getRider_id() +
                                "\n"
                );
            }

            writer.close();

            return true;

        } catch (IOException e) {

            System.out.println(
                    "Error updating parcel: " + e.getMessage()
            );

            return false;
        }
    }


    // Convert one text line into Parcel object
    private Parcel createParcelFromData(String[] data) {

        Parcel parcel = new Parcel(
                data[1],
                data[2],
                data[3],
                data[4],
                Double.parseDouble(data[5])
        );

        parcel.setParcel_id(data[0]);

        parcel.setDelivery_charge(
                Double.parseDouble(data[6])
        );

        parcel.setParcel_status(data[7]);

        if (data[8].equals("null")) {
            parcel.setRider_id(null);
        } else {
            parcel.setRider_id(data[8]);
        }

        return parcel;
    }


    // Generate Parcel ID
    private String generateParcelId() {

        int maxId = 100;

        try {

            File file = new File(PARCEL_FILE);

            if (!file.exists()) {
                return "PARCEL101";
            }

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length == 0) {
                    continue;
                }

                String id = data[0];

                if (id.startsWith("PARCEL")) {

                    try {

                        int number =
                                Integer.parseInt(id.substring(6));

                        if (number > maxId) {
                            maxId = number;
                        }

                    } catch (NumberFormatException ignored) {
                    }
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "Error generating parcel ID: " + e.getMessage()
            );
        }

        return "PARCEL" + (maxId + 1);
    }
}