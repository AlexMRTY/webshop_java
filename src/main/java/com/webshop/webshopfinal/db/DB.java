package com.webshop.webshopfinal.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private static DB instance = null;
    private Connection con = null;

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    private DB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop?user=root&password=mrty");
        } catch (Exception e) {e.printStackTrace();}
    }

    public static Connection getConnection() {
        return getInstance().con;
    }

    public static Boolean testConnection() {
        try {
            getConnection();
            System.out.println("Connected to database");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
