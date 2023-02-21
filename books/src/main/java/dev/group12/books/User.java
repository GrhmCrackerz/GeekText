package dev.group12.books;

import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String homeAddress;

    // Constructors, getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public User() {
        this.id = UUID.randomUUID().toString();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}



