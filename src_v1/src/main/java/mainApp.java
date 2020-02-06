package com.projectname.main;

import com.projectname.dao.UserProfileDao;
import com.projectname.entities.UserProfile;
import com.projectname.services.UserServices;

import java.util.Date;
import java.util.List;

public class MainApplication {

    static UserServices userServices = new UserServices();
    static UserProfileDao userProfileDao = new UserProfileDao();
    static UserProfile userProfile = new UserProfile();
    static Date date = new Date();

    public static void main(String[] args) {

        handlerCreateUser();
        handlerShowAllUsers();
        handlerEditUser(1);

    }

    public static void handlerCreateUser() {

        userServices.createUser(new UserProfile(1, "fName", "lName", "username",
        "bio", date, "email", "password"));
    }

    public static void handlerShowAllUsers() {
        List<UserProfile> userProfileList;
        userProfileList = userServices.showAllUsers();
        System.out.println(userProfileList);
    }

    public static void handlerEditUser(int currentUserId) {

        UserProfile currentUser = userServices.findUser(currentUserId);

        if (currentUser != null) {
            UserProfile userToUpdate = new UserProfile(currentUserId, "fName", "lName",
                    "username", "bio", date, "email", "password");
            userServices.editUser(userToUpdate);
        } else {
            System.out.println("Error. Read the errors");
        }
    }

}
