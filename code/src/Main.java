import model.Parcel;
import model.User;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // =========================
        // USER
        User u1 = new User("ibrahim723@gmail.com", "12345678");
        u1.login(u1);

        User u2 = new User("user4@gmail.com","12345678");
        u2.login(u2);
        // =========================

        Parcel parcel = new Parcel(u1);
        ArrayList<String> arrUser1 = parcel.getMyAllParcels();

        if(arrUser1.size() > 0){
            System.out.println("USER 1 PARCELS:");
            for (String line : arrUser1) {
                System.out.println(line);
            }
        }else {
            System.out.println("No Parcel Found!");
        }
    }
}