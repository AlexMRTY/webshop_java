package com.webshop.webshopfinal.model;

import com.webshop.webshopfinal.dao.UserDAO;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String role;

    protected User(int id, String first_name, String last_name, String username, String password, String email, String role) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * Login User
     * @param username
     * @param password
     * @return
     */
    static public UserDAO login(String username, String password) {
        return UserDAO.login(username, password);
    }

    /**
     * Get User
     * @param username
     * @return
     */
    static public UserDAO getUser(String username) {
        return UserDAO.getUser(username);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }


}
