package AbstractClasses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseEntity {
    private String id;
    private String createdAt;


    public BaseEntity(String id) {
        this.id = id;
        this.createdAt = generateTime();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    protected static String generateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public abstract String getDetails();
    public abstract boolean validate();
}