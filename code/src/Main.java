import model.User;

public class Main {

    public static void main(String[] args) {

        // =========================
        // USER REGISTRATION TEST
        // =========================

        User u1 = new User(
                "IBRAHIM2",
                "ibrahim723@gmail.com",
                "12345678"
        );

        String registrationResult = u1.register(u1);

        System.out.println(registrationResult);


        // =========================
        // USER LOGIN TEST
        // =========================

        User u2 = new User(
                "ibrahim723@gmail.com",
                "12345678"
        );

        String loginResult = u2.login(u2);
    }
}