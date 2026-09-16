import model.User;

public class Main {
    public static void main(String[] args){

        User u1 = new User("karim@example.com", "karim123");
        System.out.println(u1.login(u1));

    }
}
