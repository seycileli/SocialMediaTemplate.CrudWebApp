package com.casestudyproject.services;

import com.casestudyproject.dao.UserProfileDao;
import com.casestudyproject.entities.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    static UserProfileDao dao = new UserProfileDao();
    static int result = 1;

    @Autowired
    UserServices userServices;

    public static UserProfile getUserProfile(UserProfile userProfile) {
        UserProfile profile = dao.getUserProfile(userProfile);

        return profile;
    }

    public static int createUser(UserProfile userProfile) {
        result = dao.insertNewUserProfile(userProfile);

        return result;
    }

    public static int updateUser(UserProfile userProfile) {
        result = dao.updateUserProfile(userProfile);

        return result;
    }

    public static int deleteUser(int userid) {
        result = dao.deleteUserProfile(userid);

        return result;
    }

    public static UserProfile getUserByEmail(UserProfile email) {
        return dao.getUserByEmail(email);
    }

    public static Boolean isUserValid(String email, String password) {
        return dao.isUserValid(email, password);
    }

}
