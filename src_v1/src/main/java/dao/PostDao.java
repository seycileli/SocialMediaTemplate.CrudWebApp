package com.critix.dao;

import com.critix.entities.UserPost;
import com.critix.entities.UserProfile;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    public static void insertUserPost(UserPost userPost) {
        EntityManager em = EntityDao.getEntityManager("Critix");
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(userPost);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback(); //in case of error, undo
        } finally {
            em.close();
        }
    }

    public static void updateUserPost(UserPost userPost) {
        EntityManager em = EntityDao.getEntityManager("Critix");
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(userPost);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public static void deleteUserPost(UserPost userPost) {
        EntityManager em = EntityDao.getEntityManager("Critix");
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(em.merge(userPost));
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public static List<UserPost> userPosts() {
        EntityManager em = EntityDao.getEntityManager("Critix");
        String queryString = "SELECT c FROM UserPost c"; //not needed but creating so that I don't forget how to
        List<UserPost> userPosts = null; //will be used to return UserPosts later

        try {
            TypedQuery<UserPost> typedQuery = (TypedQuery<UserPost>) em.createQuery(queryString);
            //the above should be - createQuery(queryString, UserPost.class) - come back to this

            userPosts = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return userPosts;
    }

    public static List<UserPost> postsFromUserById(int userId) {

        /**
         * Posts from Users by ID
         */

        EntityManager em = EntityDao.getEntityManager("Critix");
        List<UserPost> userPosts = null;
        String queryString = "SELECT c FROM UserPost c WHERE c.userProfile.userId =:userId";

        try {
            TypedQuery<UserPost> typedQuery = (TypedQuery<UserPost>) em.createQuery(queryString);
            typedQuery.setParameter("User ID: ", userId);
            userPosts = typedQuery.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return userPosts;
    }

    public static List<UserPost> postsFromUsersByEmail(String email) {
        /**
         * Posts from Users by Email,
         * Can be used to in the future to email users based upon who they're following
         * and receiving their tweets, will implement that later
         */

        EntityManager em = EntityDao.getEntityManager("Critix");
        List<UserPost> userPosts = null;
        String queryString = "SELECT c FROM UserPost c "
                + "WHERE c.userProfile.email =: email";

        try {
            TypedQuery<UserPost> typedQuery = (TypedQuery<UserPost>) em.createQuery(queryString);
            typedQuery.setParameter("Email: ", email);
            userPosts = typedQuery.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return userPosts;
    }

    public static List<UserPost> searchForUserPost(String search) {
        EntityManager em = EntityDao.getEntityManager("Critix");
        List<UserPost> searchPosts = null;
        String queryString = "SELECT c FROM UserPost c "
                + "WHERE c.postMessage LIKE:search";

        try {
            TypedQuery<UserPost> typedQuery = (TypedQuery<UserPost>) em.createQuery(queryString);
            typedQuery.setParameter("Search", "%" + search + "%");
            searchPosts = typedQuery.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return searchPosts;
    }

}
