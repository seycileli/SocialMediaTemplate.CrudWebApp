package com.casestudyproject.dao;

import com.casestudyproject.entities.UserProfile;

import java.util.List;

public interface UserProfileDaoI {

    public UserProfile getUserProfile(UserProfile userid);
    public int insertNewUserProfile(UserProfile userid);
    public int updateUserProfile(UserProfile userid);
    public int deleteUserProfile(int userProfile);
    public UserProfile getUserByEmail(UserProfile email);
    public boolean isUserValid(String email, String userPassword);

}
