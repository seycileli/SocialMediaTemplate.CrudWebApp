package com.casestudyproject.dao;

import com.casestudyproject.entities.UserPost;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserPostDao implements UserPostI {
    static int result = 0;
    static EntityManager em = DaoUtility.getEntityManager();

    @Autowired
    UserPostDao userPostDao;

    @Override
    public UserPost insertUserPost(UserPost userPost) {

        try {
            em.getTransaction().begin();
            em.persist(userPost);
            em.getTransaction().commit();
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback(); //in case of error, undo
            result = 0;
        } finally {
            em.close();
            DaoUtility.close();
        }

        return userPost;
    }

    @Override
    public int updateUserPost(UserPost userPost) {

        try {

            em.getTransaction().begin();
            em.merge(userPost);
            em.getTransaction().commit();
            result = 1;

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            result = 0;

        } finally {
            em.close();
            DaoUtility.close();
        }

        return result;
    }

    @Override
    public int deleteUserPost(UserPost userPost) {

        try {

            em.getTransaction().begin();
            em.remove(em.merge(userPost));
            em.getTransaction().commit();
            result = 1;

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();
            result = 0;

        } finally {

            em.close();
            DaoUtility.close();

        }

        return result;
    }

    @Override
    public List<UserPost> userPosts() {
        List<UserPost> userPosts = null;

        try {
            em.getTransaction().begin();
            TypedQuery<UserPost> typedQuery =
                    em.createQuery("SELECT db FROM UserPost db", UserPost.class);

            userPosts = typedQuery.getResultList();

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

        } finally {

            em.close();
            DaoUtility.close();
        }

        return userPosts;
    }

    @Override
    public List<UserPost> postsFromUserById(int userid) {

        /**
         * Posts from Users by ID
         */

        EntityManager em = DaoUtility.getEntityManager();
        List<UserPost> userPosts = null;

        try {

            TypedQuery<UserPost> tQ =
                    em.createQuery(
                            "SELECT db FROM UserPost db WHERE db.userProfile.userid=:userid",
                            UserPost.class);

            tQ.setParameter("userid", userid);
            userPosts = tQ.getResultList();

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

        } finally {

            em.close();
            DaoUtility.close();
        }

        return userPosts;

    }

    @Override
    public List<UserPost> postsFromUsersByEmail(String email) {
        /**
         * Posts from Users by Email,
         * Can be used to in the future to email users based upon who they're following
         * and receiving their tweets, will implement that later
         */

        EntityManager em = DaoUtility.getEntityManager();
        List<UserPost> userPosts = null;

        try {

            TypedQuery<UserPost> tQ =
                    em.createQuery("SELECT db FROM UserPost db WHERE db.userProfile.email=:email",
                            UserPost.class);

            tQ.setParameter("email", email);

            userPosts = tQ.getResultList();

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

        } finally {

            em.close();
            DaoUtility.close();
        }
        return userPosts;
    }

    @Override
    public List<UserPost> searchForUserPost(String search) {
        List<UserPost> searchPosts = null;

        try {

            TypedQuery<UserPost> tQ = em.createQuery(
                    "SELECT db FROM UserPost db WHERE db.postMessage LIKE:search",
                    UserPost.class);

            tQ.setParameter("search", "%" + search + "%");
            searchPosts = tQ.getResultList();

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

        } finally {

            em.close();
            DaoUtility.close();

        }

        return searchPosts;
    }
}
