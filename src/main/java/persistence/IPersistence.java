package persistence;

import javax.persistence.EntityManager;

public interface IPersistence {
    
     EntityManager createEntityManager();
     
     void close();
}
