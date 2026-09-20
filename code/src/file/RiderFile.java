package file;

import model.Rider;

import java.io.*;
import java.util.ArrayList;

public class RiderFile {

    private static final String RIDER_FILE =
            "code/src/database/rider_db.txt";


    public boolean createNewRider(Rider rider) {

        try {
            File file = new File(RIDER_FILE);

            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);

            writer.write(
                    rider.getRider_id() + "|" +
                            rider.getRider_name() + "|" +
                            rider.getRider_phone() + "|" +
                            rider.getRider_address() + "|" +
                            rider.getRider_status() +
                            "\n"
            );

            writer.close();

            return true;

        } catch (IOException e) {

            System.out.println("Error saving rider: " + e.getMessage());

            return false;
        }
    }


    public Rider getRider(String rider_id) {

        try {

            File file = new File(RIDER_FILE);

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

                if (data.length < 5) {
                    continue;
                }

                if (data[0].equals(rider_id)) {

                    reader.close();

                    Rider rider = new Rider(
                            data[0],
                            data[1],
                            data[2],
                            data[3]
                    );

                    rider.setRider_status(data[4]);

                    return rider;
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error finding rider: " + e.getMessage());
        }

        return null;
    }


    public ArrayList<Rider> getAllRiders() {

        ArrayList<Rider> riders = new ArrayList<>();

        try {

            File file = new File(RIDER_FILE);

            if (!file.exists()) {
                return riders;
            }

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length < 5) {
                    continue;
                }

                Rider rider = new Rider(
                        data[0],
                        data[1],
                        data[2],
                        data[3]
                );

                rider.setRider_status(data[4]);

                riders.add(rider);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error reading riders: " + e.getMessage());
        }

        return riders;
    }


    public boolean updateRider(Rider rider) {

        if (rider == null) {
            return false;
        }

        ArrayList<Rider> riders = getAllRiders();

        boolean found = false;

        for (Rider r : riders) {

            if (r.getRider_id().equals(rider.getRider_id())) {

                r.setRider_name(rider.getRider_name());
                r.setRider_phone(rider.getRider_phone());
                r.setRider_address(rider.getRider_address());
                r.setRider_status(rider.getRider_status());

                found = true;
                break;
            }
        }

        if (!found) {
            return false;
        }

        try {

            FileWriter writer =
                    new FileWriter(RIDER_FILE, false);

            for (Rider r : riders) {

                writer.write(
                        r.getRider_id() + "|" +
                                r.getRider_name() + "|" +
                                r.getRider_phone() + "|" +
                                r.getRider_address() + "|" +
                                r.getRider_status() +
                                "\n"
                );
            }

            writer.close();

            return true;

        } catch (IOException e) {

            System.out.println("Error updating rider: " + e.getMessage());

            return false;
        }
    }


    public boolean removeRider(String rider_id) {

        ArrayList<Rider> riders = getAllRiders();

        boolean found = false;

        for (int i = 0; i < riders.size(); i++) {

            if (riders.get(i).getRider_id().equals(rider_id)) {

                riders.remove(i);

                found = true;

                break;
            }
        }

        if (!found) {
            return false;
        }

        try {

            FileWriter writer =
                    new FileWriter(RIDER_FILE, false);

            for (Rider rider : riders) {

                writer.write(
                        rider.getRider_id() + "|" +
                                rider.getRider_name() + "|" +
                                rider.getRider_phone() + "|" +
                                rider.getRider_address() + "|" +
                                rider.getRider_status() +
                                "\n"
                );
            }

            writer.close();

            return true;

        } catch (IOException e) {

            System.out.println("Error removing rider: " + e.getMessage());

            return false;
        }
    }
}