package file;

import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserFile {
    ArrayList<String> usersList = new ArrayList<>();

    private static final String USER_FILE =
            "code/src/database/users_db.txt";

    public boolean createNewUser(User user) {

        String name = user.getUser_name();
        String email = user.getUser_email();
        String pass = user.getUserPassword();
        String role = user.getUser_role();
        String userid = user.getUser_id();

        try {

            File file = new File(USER_FILE);

            if (!file.exists()) {
                file.createNewFile();
            }

            String newUser = name+" "+email+" "+pass+" "+role+" "+userid+"#\n";
            FileWriter fwtr = new FileWriter(USER_FILE, true);
            fwtr.write(newUser);
            fwtr.close();
            return true;

        } catch (IOException e) {

            System.out.println(e.getMessage());
            return false;
        }
    }


    public boolean userRegisteredCheck(String email, String password){

        File fl = new File(USER_FILE);
        boolean isExists = false;
        try {
            Scanner scn = new Scanner(fl);
            while(scn.hasNextLine()){
                String data = scn.nextLine();
                if(data.contains(email) && data.contains(password)){
                    isExists = true;
                    break;
                }
            }
            scn.close();
            if(isExists){

                return true;
            }
            return false;
        } catch (IOException e){
            System.out.println("Something wrong happend!");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static String getCurrentUser(String email) {
        File fl = new File(USER_FILE);
        try {
            Scanner scn = new Scanner(fl);
            while (scn.hasNextLine()) {
                String data = scn.nextLine();
                if (data.contains(email)) {
                    scn.close();
                    return data;
                }
            }
            scn.close();
        } catch (IOException e) {
            return null;
        }
        return null;
    }



}