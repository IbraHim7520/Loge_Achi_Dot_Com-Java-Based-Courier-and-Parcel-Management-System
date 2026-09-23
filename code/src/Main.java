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
       // parcel.sendOneParcel("A Big Box", "Dhaka 1206", "01983829482",5.00);
//        ArrayList<String> arrUser1 = parcel.getMyAllParcels();
//
//        if(arrUser1.size() > 0){
//            System.out.println("USER 1 PARCELS:");
//            for (String line : arrUser1) {
//                System.out.println(line);
//            }
//        }else {
//            System.out.println("No Parcel Found!");
//        }

        String trackResult = parcel.trackParcel("P024045971393600");
        System.out.println(trackResult);

        boolean res = parcel.cancelParcel("P024045971393600");
        if(res){
            System.out.println("After Update");
            String re = parcel.trackParcel("P024045971393600");
            System.out.println(re);
        }

    }
}

//P024045971393600