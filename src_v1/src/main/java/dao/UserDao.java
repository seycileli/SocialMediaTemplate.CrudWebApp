//insert package here

import com.projectname.entities.UserProfile;

import javax.persistence.*;

public class UserDao {

    /**
     * This class will contain methods that will work with the User only
     * <p>
     * This class will be specifically dedicated to the UserProfile
     * This is separate from UserPost because we want to keep our code
     * Organized and so that we know which class and methods to call
     */

    public static UserProfile userProfile(UserProfile userId) {

        EntityManager em = EntityDao.getEntityManager("mydatabase");
        UserProfile userProfile = em.find(UserProfile.class, userId);

        em.close();
        return userProfile;
    }

    public static void insertUser(UserProfile userProfile) {
        /**
         * Create a new user
         */

        EntityManager em = EntityDao.getEntityManager("mydatabase");
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(userProfile);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public static void uploadAvatar(int size) {

        /**
         * https://www.gotoquiz.com/web-coding/programming/
         * java-programming/create-thumbnails-and-avatars-in-java/
         *
         * look into the above website to learn how to create thumbnail avatars
         * for UserProfiles
         */
    }

    public static void updateUserProfile(UserProfile user) {

        /**
         * This will update the Users data
         *
         * The merge entity will update an existing entity by updating the entity's
         * properties
         *
         * in case of error, we will catch it with Exception and printStackTrace
         * Then we will rollback/ which means we will undo the changes
         * that were made during transaction
         */

        EntityManager em = EntityDao.getEntityManager("mydatabase");
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(user);
            transaction.commit();

        } catch(Exception e) {
            e.printStackTrace();
            transaction.rollback();

        } finally {
            em.close();
        }
    }

    public static void deleteUserProfile(UserProfile userProfile) {
        EntityManager em = EntityDao.getEntityManager("mydatabase");
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(userProfile);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public static UserProfile getUserByEmail(String email) {

        /**
         * This method is for when a user can search and get/ retrieve another user
         * info via their email
         */

        int result = 0;
        EntityManager em = EntityDao.getEntityManager("mydatabase");
        EntityTransaction transaction = em.getTransaction();
        em.getTransaction().begin();

        try {
            String queryString = "SELECT u FROM UserProfile u "
                    + "WHERE u.email =:email";

            TypedQuery<UserProfile> typedQuery =
                    (TypedQuery<UserProfile>) em.createQuery(queryString);
            //UserProfile.class should be added to the end but error shows up. Come back and fix this
            typedQuery.setParameter("email", email);
            UserProfile userProfile = null;


            System.out.println("Getting User info");
            userProfile = typedQuery.getSingleResult();
            System.out.println(userProfile.getUsername());
            return userProfile;
        } catch (NoResultException e) {
            result = 0;
            System.out.println(e);
            transaction.rollback();
        } finally {
            System.out.println("Hmm, something seems off...");

            if (result == 1) {
                System.out.println("Success!");
            }
            em.close();
        }
        return null;

        /**
         * Come back to this method,
         * TypedQuery is wrong, I have to find a way to add
         * UserProfile.class to the end of em.createQuery, but I keep receiving an error
         *
         * The return statement should also be
         * return userProfile;
         *
         * it should be RETURNING the userProfile that we were searching from via their email
         *
         * But I keep receiving error, so I'll have to come back and fix this
         */

    }

    public static boolean isUserValid(String email, String userPassword) {

        EntityManager em = EntityDao.getEntityManager("mydatabase");

        String sqlQuery = "SELECT count(c.userId) FROM UserProfile c "
                + "WHERE c.email = :email and c.password = :password";

        TypedQuery<UserProfile> typedQuery =
                (TypedQuery<UserProfile>) em.createQuery(sqlQuery); //UserProfile.class should be after sqlQuery

        boolean result = false;
        typedQuery.setParameter("Email: ", email);
        typedQuery.setParameter("Password: ", userPassword);

        try {
            UserProfile userProfile = typedQuery.getSingleResult();

            if (userProfile != null) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return result;
    }
}
