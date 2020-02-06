package com.critix.dao;

import com.critix.entities.UserProfile;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserProfileDao implements UserProfileInterface {

//    will insert persistence.xml persistence unit name below
    private final String persistenceString = "mydatabase";

    @Override
    public int insertUser(UserProfile user) {
        int result = 0;

        try {
//            EntityManagerFactory emf =
//                    Persistence.createEntityManagerFactory(persistenceString);
//            EntityManager em = emf.createEntityManager();
            EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("mydatabase");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            em.persist(user);
            em.getTransaction().commit();

            em.close();
            emf.close();
            result = 1;
        } catch (PersistenceException pe) {
            result = 0;
            pe.printStackTrace();

        } finally {
            System.out.println("Hmm, something went wrong...");

            if (result == 1) {
                System.out.println("Success! Now you can add more code if you like");
            }
        }

        return 0;
    }

    @Override
    public int deleteUserById(int userId) {
        int result = 0;

        try {
//            EntityManagerFactory emf =
//                    Persistence.createEntityManagerFactory(persistenceString);
//            EntityManager em = emf.createEntityManager();

            EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("mydatabase");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            UserProfile findUser = em.find(UserProfile.class, userId);
            em.remove(findUser);

            em.close();
            emf.close();
            result = 1;
        } catch (PersistenceException pe) {
            result = 0;
            pe.printStackTrace();
        } finally {
            System.out.println("Hmm, something went wrong...");

            if (result == 1) {
                System.out.println("Success! We can add more steps now");
            }
        }
        return result;
    }

    @Override
    public int updateUser(UserProfile user) {
        int result = 0;

        try {
            EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory(persistenceString);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin(); //begin transaction

            UserProfile findUser = em.find(UserProfile.class, user.getUserId());
            findUser.getUserId(); findUser.getfName(); findUser.getlName();
            findUser.getEmail(); findUser.getPassword();

            em.getTransaction().commit(); //save
            em.close();
            emf.close();
            result = 1;
        } catch(PersistenceException pe) {
            result = 0;
            pe.printStackTrace();
        } finally {
            System.out.println("Hmm, something went wrong...");

            if (result == 1) {
                System.out.println("Success. We can add more code now");
            }
        }

        return result;
    }

    @Override
    public UserProfile getUserById(int userId) {
        int result = 0;
        UserProfile userFound = null;

        try {
            EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory(persistenceString);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            userFound = em.find(UserProfile.class, userId);

            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (PersistenceException pe) {
            result = 0;
            pe.printStackTrace();
        } finally {
            System.out.println("Hmm, something went wrong...");

            if (result == 1) {
                System.out.println("Success! We can add more code if you like");
            }
        }

        return userFound;
    }

    @Override
    public List<UserProfile> getAllUsers() {
        List<UserProfile> userList = new ArrayList<>();

        try {
            EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory(persistenceString);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Query query = em.createQuery("select x from UserProfile  x");
            userList = query.getResultList();

            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch(PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            System.out.println("Hmm, something went wrong...");
        }
        return userList;
    }

}
