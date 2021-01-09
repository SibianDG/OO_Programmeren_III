package main;

import domein.Campus;
import domein.Docent;
import util.JPAUntil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main5 {
    public static void main(String[] args) {
        System.out.println("Main5");
        List<Docent> docentList;
        List<Campus> campuses;

        EntityManager em = JPAUntil.getEntityManagerFactory().createEntityManager();

        //Query queryDocent = em.createNamedQuery("Docent.findAll"); //geeft raw list terug
        TypedQuery<Docent> queryDocent = em.createNamedQuery("Docent.findAll", Docent.class);
        docentList = queryDocent.getResultList();

        TypedQuery<Campus> queryCampus = em.createNamedQuery("Campus.findAll", Campus.class);
        campuses = queryCampus.getResultList();

        System.out.println(campuses);
        System.out.println(docentList);


        //bkijken wat lazy-loading is fzo? 24/11 40'
        Docent d1  = docentList.get(0);
        Campus dummy1 = d1.getCampussen().iterator().next();
        System.out.printf("%s %s\n", d1, d1.getCampussen());
        Docent d2  = docentList.get(1);
        System.out.printf("%s%s\n", d2, d2.getCampussen());
        Docent d3  = docentList.get(2);
        System.out.printf("%s%s\n", d3, d3.getCampussen());
        Campus c1 = campuses.get(0);
        System.out.printf("%s %s\n", c1, c1.getDocenten());
        Campus c2 = campuses.get(1);
        Docent dummy2 = c2.getDocenten().iterator().next();
        System.out.printf("%s %s\n", c2, c2.getDocenten());

        em.close();
        JPAUntil.getEntityManagerFactory().close();
    }
}
