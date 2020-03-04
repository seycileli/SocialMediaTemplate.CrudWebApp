package com.casestudyproject.services;

import com.casestudyproject.dao.UserPostDao;
import com.casestudyproject.entities.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServices {
    static UserPostDao postDao = new UserPostDao();

    public static UserPost insertUserPost(UserPost userPost) {
        return postDao.insertUserPost(userPost);
    }

    public int updateUserPost(UserPost userPost) {
        return postDao.updateUserPost(userPost);
    }

    public int deleteUserPost(UserPost userPost) {
        return postDao.deleteUserPost(userPost);
    }

    public List<UserPost> userPosts() {
        return postDao.userPosts();
    }

    public List<UserPost> postsFromUserById(int userid) {
        return postDao.postsFromUserById(userid);
    }

    public List<UserPost> postsFromUsersByEmail(String email) {
        return postDao.postsFromUsersByEmail(email);
    }

    public List<UserPost> searchForUserPost(String search) {
        return postDao.searchForUserPost(search);
    }



}
