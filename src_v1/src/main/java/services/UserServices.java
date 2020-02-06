package com.projectname.services;

import com.projectname.dao.UserProfileDao;
import com.projectname.entities.UserProfile;

import java.util.List;

public class UserServices {
    static UserProfileDao userProfileDao = new UserProfileDao();

    public void createUser(UserProfile user) {
        int result = 0;
        userProfileDao.insertUser(user);

        if (result == 1) {
            System.out.println("Success");
        } else {
            System.out.println("Error");
        }
    }

    public void removeUser(int userId) {
        int result = 0;
        result = userProfileDao.deleteUserById(userId);

        if (result == 1) {
            System.out.println("Success");
        } else {
            System.out.println("Error");
        }
    }

    public void editUser(UserProfile user) {
        int result = 0;
        result = userProfileDao.updateUser(user);

        if (result == 1) {
            System.out.println("Success");
        } else {
            System.out.println("Error");
        }
    }

    public UserProfile findUser(int userId) {

        return userProfileDao.getUserById(userId);
    }

    public UserProfile showUserById(int userId) {
        UserProfile showUser = userProfileDao.getUserById(userId);

        if (showUser != null) {
            printUser(showUser);
        }
        return showUser;
    }

    public List<UserProfile> showAllUsers() {
        List<UserProfile> userProfileList;

        userProfileList = userProfileDao.getAllUsers();

        if (userProfileList != null) {
            printAllUsers(userProfileList);
        } else {
            System.out.println("Error");
        }
        return null;
    }

    public void printUser(UserProfile userProfile) {
        System.out.println(userProfile.toString());
    }

    public void printAllUsers(List<UserProfile> userList) {
        for (UserProfile i: userList) {
            System.out.println(i.toString());
        }
    }
}
