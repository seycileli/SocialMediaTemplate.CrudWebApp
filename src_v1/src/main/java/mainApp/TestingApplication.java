package com.projectname.main;

import com.projectname.dao.UserProfileDao;
import com.projectname.entities.UserProfile;
import com.projectname.services.UserServices;

import java.util.Date;
import java.util.List;

public class Testing {

    static UserServices userServices = new UserServices();
    static UserProfileDao userProfileDao = new UserProfileDao();
    static UserProfile userProfile = new UserProfile();
    static Date date = new Date();
    public static void main(String[] args) {

        handlerCreateUser();
//        handlerShowAllUsers();
//        handlerEditUser(1);

    }

    public static void handlerCreateUser() {

//        this.userId = userId;
//        this.fName = fName;
//        this.lName = lName;
//        this.username = username;
//        this.bio = bio;
//        this.joinDate = joinDate;
//        this.email = email;
//        this.password = password;

        userServices.createUser(new UserProfile(1, "firstname", "lastname", "username",
                "bio", date, "email", "password"));
    }

    public static void handlerShowAllUsers() {
        List<UserProfile> userProfileList;
        userProfileList = userServices.showAllUsers();
    }

    public static void handlerEditUser(int currentUserId) {

//        UserProfile currentUser = userServices.findUser(1);
//
//        if (currentUser != null) {
//            UserProfile userToUpdate = new UserProfile(1, "", "",
//                    "", "");
//            userServices.editUser(userToUpdate);
//        } else {
//            System.out.println("Error. Read the errors");
//        }

    }


}
