package com.critix.dao;

import com.critix.entities.UserProfile;

import java.util.List;

public interface UserProfileInterface {

    public int insertUser(UserProfile user);
    public int deleteUserById(int userId);
    public int updateUser(UserProfile user);
    public UserProfile getUserById(int userId);
    public List<UserProfile> getAllUsers();
}
