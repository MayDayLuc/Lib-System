package model;

import model.enums.UserType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private String id;
    private String password;
    private String name;
    private UserType type;
    private String phone;
    private String email;

    public User() {
    }

    public User(String id, String name, UserType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public User(String id, String name, UserType type, String phone, String email) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.phone = phone;
        this.email = email;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(value = EnumType.STRING)
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean match(String key) {
        return id.contains(key)
                || name.contains(key)
                || type.name().contains(key)
                || phone.contains(key)
                || email.contains(key);
    }
}
