package main;

import domein.Campus;
import domein.Docent;
import domein.Werkruimte;
import util.JPAUntil;

import javax.persistence.EntityManager;
import java.util.List;

public class Main6 {
    public static void main(String[] args) {
        System.out.println("Main6");

        EntityManager em = JPAUntil.getEntityManagerFactory().createEntityManager();

        Werkruimte werkruimte = em.find(Werkruimte.class, "SCH555");

        Campus campusGent = em.createNamedQuery("Campus.findByName", Campus.class)
                .setParameter("naam", "Gent")
                .getSingleResult();

        Campus campusAalst = em.createNamedQuery("Campus.findByName", Campus.class)
                .setParameter("naam", "Aalst")
                .getSingleResult();

        if (campusGent != null && campusAalst != null && werkruimte != null){
            List<Docent> docentList = em.createNamedQuery("Docent.docentenInTweeCampussen", Docent.class)
                    .setParameter("campusA", campusGent)
                    .setParameter("campusB", campusAalst)
                    .getResultList();
            docentList.forEach(d -> d.setWerkruimte(werkruimte));
            em.getTransaction().commit(); // TODO! niet vergeten

            //Alternatief (maar niet zo efficiënt omdat je niet alle betrokken objecten al in de context hebt
            campusGent.getDocenten().stream()
                    .filter(docent -> campusAalst.getDocenten().contains(docent))
                    .forEach(docent -> docent.setWerkruimte(werkruimte));
            em.getTransaction().commit();

        } else {
            System.out.println("Job niet mogelijk...");
        }

        em.close();
        JPAUntil.getEntityManagerFactory().close();
    }
}
