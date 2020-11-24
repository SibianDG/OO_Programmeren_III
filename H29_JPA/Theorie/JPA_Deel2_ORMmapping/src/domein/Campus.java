package domein;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Campus.findAll", query = "SELECT c FROM Docent c"),
        @NamedQuery(name = "Campus.findByName", query = "SELECT c FROM Campus c WHERE c.campusNaam = :naam")

})
public class Campus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campusID;
    private String campusNaam;
    @ManyToMany(mappedBy = "campussen", fetch = FetchType.EAGER)
    private Set<Docent> docenten = new HashSet<>();

    public Campus(String campusNaam) {
        this.campusNaam = campusNaam;
    }

    protected Campus() {

    }

    public String getCampusNaam() {
        return campusNaam;
    }

    public Set<Docent> getDocenten() {
        return Collections.unmodifiableSet(docenten);
    }

    public void addDocent(Docent d){
        docenten.add(d);
    }

    public void removeDocent(Docent d) {
        docenten.remove(d);
    }

    @Override
    public String toString() {
        return "Campus{" +
                "campusNaam='" + campusNaam + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campus campus = (Campus) o;
        return Objects.equals(campusNaam, campus.campusNaam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campusNaam);
    }
}
