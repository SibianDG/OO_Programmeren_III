package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUntil {
    private final static EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("school2db");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    private JPAUntil (){

    }

}
