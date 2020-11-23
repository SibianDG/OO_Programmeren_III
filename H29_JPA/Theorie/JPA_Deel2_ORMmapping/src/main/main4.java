package main;

import domein.Campus;
import domein.Docent;
import domein.Werkruimte;
import util.JPAUntil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class main4 {
    public static void main(String[] args) {
        System.out.println("Main4");
        Docent a = new Docent(123, "Jan", "Baard", new BigDecimal(8000));
        Docent b = new Docent(456, "Piet", "Baard", new BigDecimal(10000));
        Docent c = new Docent(789, "Joris", "ZonderBaard", new BigDecimal(12000));
        Campus gent = new Campus("Gent");
        Campus aalst = new Campus("Aalst");
        Werkruimte r1 = new Werkruimte("SCH123", "zolder", 12, 6);
        Werkruimte r2 = new Werkruimte("SCH555", "kelder", 4, 4);
        Werkruimte r3 = new Werkruimte("AA222A", "dak", 10, 2);
        EntityManager em = JPAUntil.getEntityManagerFactory().createEntityManager();

        a.addCampus(gent);
        b.addCampus(gent);
        c.addCampus(gent);

        a.addCampus(aalst);
        c.addCampus(aalst);

        a.setWerkruimte(r1);
        b.setWerkruimte(r1);
        c.setWerkruimte(r3);

        //problemen? -> XML-> drop-and-create

        em.getTransaction().begin();
        em.persist(a);
        em.persist(b);
        em.persist(c);
        Stream.of(gent, aalst, r1, r2, r3).forEach(em::persist);
        em.getTransaction().commit();

        em.close();
        JPAUntil.getEntityManagerFactory().close();

    }
}
