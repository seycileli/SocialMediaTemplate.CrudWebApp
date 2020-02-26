package com.casestudyproject.dao;

import com.casestudyproject.entities.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserProfileDao implements UserProfileDaoI {
    static EntityManager em = DaoUtility.getEntityManager();

    @Autowired
    UserProfileDao userProfileDao;

    @Override
    public UserProfile getUserProfile(UserProfile userid) {
        /**
         * This is the User's profile
         * We will retrieve the User info via their user id
         */

        UserProfile userProfile = null;

        userProfile = em.find(UserProfile.class, userid);

        em.close();
        DaoUtility.close();

        return userProfile;
    }

    @Override
    public int insertNewUserProfile(UserProfile userProfile) {
        /**
         * Create a new user
         *
         * Using EntityTransaction is same as the same thing as
         * em.getTransaction().begin();
         *
         * using transaction(dot) is less code
         *
         */

        int result = 0;

        try {

            em.getTransaction().begin();
            em.persist(userProfile);
            em.getTransaction().commit();
            System.out.println("Profile successfully created.");

            result = 1;

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();
            result = 0;

        } finally {

            em.close();
            DaoUtility.close();

        }
        return result; //1 = saved/ persisted, 0 unsuccessful
    }

    @Override
    public int updateUserProfile(UserProfile userProfile) {

        /**
         * This will update the Users data
         *
         * The merge entity will update an existing entity by updating the entity's
         * properties
         *
         * in case of error, we will catch it with Exception and
         * print its message - printStackTrace
         * Then we will rollback/ which means we will undo the changes
         * that were made during the transaction
         */

        int result = 0;

        try {

            em.getTransaction().begin();
            em.merge(userProfile);
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
    public int deleteUserProfile(int userid) {
        int result = 0;

        try {

            em.getTransaction().begin();
            UserProfile userProfile = em.find(UserProfile.class, userid);
            em.remove(userProfile);
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
    public UserProfile getUserByEmail(UserProfile email) {

        /**
         * This method is for when a user can SEARCH and get/ retrieve another user
         * info via their email
         *
         * db stands for database in typedQuery, you can name it anything
         *
         * Why typedQuery?
         *
         * Because it is used for the management
         * for persistence and object/relational mapping.
         *
         */

        em.getTransaction().begin();

        TypedQuery<UserProfile> tQ =
                em.createQuery("SELECT db FROM UserProfile db "
                        + "WHERE db.email=:email", UserProfile.class);

        tQ.setParameter("email", email);
        UserProfile userProfile = null;

        try {

            System.out.println("Retrieving User info");

            userProfile = tQ.getSingleResult();
            System.out.println(userProfile.getEmail());

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

        } finally {

            em.close();
            DaoUtility.close();
        }

        return userProfile;
    }

    @Override
    public boolean isUserValid(String email, String password) {
        TypedQuery<UserProfile> typedQuery =
                em.createQuery("SELECT count(db.userid) FROM UserProfile db "
                        + "WHERE db.email = :email and db.password=:password", UserProfile.class);

        boolean result = false;

        typedQuery.setParameter("email", email);
        typedQuery.setParameter("password", password);

        try {

            UserProfile userProfile = typedQuery.getSingleResult();

            if (userProfile != null) {
                result = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
            result = false;

        } finally {

            em.close();
            DaoUtility.close();

        }

        return result;
        //true or false will indicate if user exist or not in database
    }


}
