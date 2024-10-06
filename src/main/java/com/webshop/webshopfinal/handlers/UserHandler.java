package com.webshop.webshopfinal.handlers;

import com.webshop.webshopfinal.dao.UserDAO;
import com.webshop.webshopfinal.model.User;
import com.webshop.webshopfinal.controller.UserInfo;

public class UserHandler {
    public static UserInfo loginUser(String username, String password) {
        UserDAO user = User.login(username, password);
        if (user == null) {
            return null;
        }
        return new UserInfo(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public static UserInfo getUser(String username) {
        UserDAO user = User.getUser(username);
        if (user == null) {
            return null;
        }
        return new UserInfo(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getRole());
    }
}
