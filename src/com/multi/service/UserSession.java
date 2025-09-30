package com.multi.service;

import com.multi.model.dto.tmddk.User;

public class UserSession {
    private static User currentUser;

    public static void setUser(User user) { currentUser = user; }

    public static User getUser() { return currentUser; }

    public static void clear() { currentUser = null; }
}