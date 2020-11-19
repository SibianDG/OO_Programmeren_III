package main;

import domein.Docent;
import util.JPAUntil;

import javax.persistence.EntityManager;
import javax.print.Doc;
import java.math.BigDecimal;

public class main2 {
    public static void main(String[] args) {
        System.out.println("Main2 - aanpassing wedde docent 2");
        EntityManager em = JPAUntil.getEntityManagerFactory().createEntityManager();

        Long docentNr = 2L;

        Docent x = em.find(Docent.class, docentNr);

        em.getTransaction().begin();
        if (x != null) {
            x.opslag(new BigDecimal(300));
        } else {
            System.out.printf("Docent %d bestaat niet", docentNr);
        }
        em.getTransaction().commit();

        em.close();
        JPAUntil.getEntityManagerFactory().close();

    }
}
