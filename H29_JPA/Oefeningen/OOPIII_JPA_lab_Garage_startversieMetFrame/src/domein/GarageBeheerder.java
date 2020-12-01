package domein;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GarageBeheerder {

    public final String PERSISTENCE_UNIT_NAME = "OOPIII_JPA_GaragePU";
    private EntityManager em;
    private EntityManagerFactory emf;
    private Map<String, Vervoermiddel> vervoerMap = new HashMap<>();

    public GarageBeheerder() {
        initializePersistentie();
    }

    private void initializePersistentie() {
        openPersistentie();
        GarageData od = new GarageData(this);
        od.populeerData();
    }

    private void openPersistentie() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    public void closePersistentie() {
        em.close();
        emf.close();
    }

    public List<Auto> geefAutosZonderOnderhoudsbeurt() {
        return vervoerMap.values()  //map geen Collection, dus geen stream, values oplossing
                .stream() // Stream<Vervoermiddel>
                .filter(v -> v instanceof Auto)
                .filter(v -> v.getOnderhoudsbeurten().isEmpty())
                .map(v -> (Auto) v)   // Stream<Auto>
                .collect(Collectors.toList());
    }

    public List<Auto> geefAutosZonderOnderhoudsbeurtJPA() {
        return em.createNamedQuery("Auto.alleAutoZonderOnderhoud", Auto.class)
                .getResultList();
    }

    public List<Auto> geefAutosMetOnderhoudsbeurt() {
        return vervoerMap.values()  //map geen Collection, dus geen stream, values oplossing
                .stream() // Stream<Vervoermiddel>
                .filter(v -> v instanceof Auto)
                .filter(v -> !v.getOnderhoudsbeurten().isEmpty())
                .map(v -> (Auto) v)   // Stream<Auto>
                .collect(Collectors.toList());
    }

    public List<Auto> geefAutosMetOnderhoudsbeurtJPA() {
        return em.createNamedQuery("Auto.alleAutoMetOnderhoud", Auto.class)
                .getResultList();
    }

    public List<Onderhoudsbeurt> geefOnderhoudsbeurtenOpDatum(LocalDate dat) {
        return vervoerMap.values().stream()  //Stream<Vervoermiddel>
                        .map(v -> v.geefOnderhoudsbeurt(dat))  //Stream<Onderhoudsbeurt>
                        .filter(o -> o != null)
                        .collect(Collectors.toList());
    }

    public List<Onderhoudsbeurt> geefOnderhoudsbeurtenOpDatumJPA(LocalDate dat) {
        return em.createNamedQuery("Onderhoudsbeurt.opDatum",
                Onderhoudsbeurt.class).setParameter("dat", dat)
                .getResultList();
    }

    public void addVervoermiddel(Vervoermiddel v) {
        vervoerMap.put(v.getNummerplaat(), v);
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
    }

    public void addOnderhoudsbeurt(String nrplaat, LocalDate begin, LocalDate einde) {
        Vervoermiddel v = vervoerMap.get(nrplaat);
        Onderhoudsbeurt o = new Onderhoudsbeurt(begin, einde, v);
        em.getTransaction().begin();
        v.addOnderhoudsbeurt(o); //bidrectionele associatie
        em.persist(o);
        em.getTransaction().commit();
    }
}
