//insert package here

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityDao {

    /**
     * By doing this, we're creating a class of a reusable method
     * that we can call when need be.
     *
     * This will allow us to simplify our application
     * and make it more efficient, which we can
     * call the Entity Manager whenever we need to read or write
     * to the database.
     *
     * @param persistenceString
     * @return
     */
    public static EntityManager getEntityManager(String persistenceString) {

        return Persistence.createEntityManagerFactory(persistenceString)
                        .createEntityManager();
    }

}
