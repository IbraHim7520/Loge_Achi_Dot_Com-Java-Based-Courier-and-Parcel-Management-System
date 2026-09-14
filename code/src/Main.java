import model.User;

public class Main {
    public static void main(String[] args){

        User newUser = new User("Ishtiak Shanto", "ishtiak@example.com", "password123", "customer", "CUST001");
        
        String msge = newUser.register(newUser);
        System.out.println(msge);

    }
}
