package com.webshop.webshopfinal.controller;

public class UserInfo {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String role;

    public UserInfo(int id, String firstName, String lastName, String username, String email, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
