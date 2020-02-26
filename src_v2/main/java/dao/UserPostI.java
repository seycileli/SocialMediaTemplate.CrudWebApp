package com.casestudyproject.dao;

import com.casestudyproject.entities.UserPost;

import java.util.List;

public interface UserPostI {

    public UserPost insertUserPost(UserPost userPost);
    public int updateUserPost(UserPost userPost);
    public int deleteUserPost(UserPost userPost);
    public List<UserPost> userPosts();
    public List<UserPost> postsFromUserById(int userid);
    public List<UserPost> postsFromUsersByEmail(String email);
    public List<UserPost> searchForUserPost(String search);

}
