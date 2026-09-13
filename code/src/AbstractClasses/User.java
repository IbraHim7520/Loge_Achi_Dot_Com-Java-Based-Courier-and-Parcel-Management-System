package AbstractClasses;

public abstract class User extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;

    public User(String id, String name, String email, String password, String role, String phoneNumber) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {

        this.email = email.toLowerCase();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11) {
            throw new IllegalArgumentException("Phone number must be 11 digits long and contain only numbers");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean validate() {
        if (this.email.contains("@") || !this.email.contains(".com")) {
            throw new IllegalArgumentException("Invalid Email Address");
        }
        if (this.password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        if (this.phoneNumber.length() != 11) {
            throw new IllegalArgumentException("Phone number must be 11 digits long and contain only numbers");
        }
        return true;
    }

}