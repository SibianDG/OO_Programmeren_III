package main;

import domein.Docent;
import util.JPAUntil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class main1 {
    public static void main(String[] args) {
        System.out.println("Main1 - aanmaak van 3 docent objecten en persisteren");
        Docent a = new Docent(123, "Jan", "Baard", new BigDecimal(8000));
        Docent b = new Docent(456, "Piet", "Baard", new BigDecimal(10000));
        Docent c = new Docent(789, "Joris", "ZonderBaard", new BigDecimal(12000));
        EntityManager em = JPAUntil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.persist(b);
        em.persist(c);
        em.getTransaction().commit();

        em.close();
        JPAUntil.getEntityManagerFactory().close();

    }
}
