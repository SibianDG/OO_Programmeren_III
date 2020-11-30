package domein;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vervoermiddel implements TebetalenTaks, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autonummering
    private long id; //long standaard voor id
    private String nummerplaat; //nummerplaat van auto kan veranderen, dus niet PK
    @OneToMany(mappedBy = "vervoermiddel", cascade = CascadeType.ALL)
    private List<Onderhoudsbeurt> onderhoudsbeurten = new ArrayList<>();
    protected Vervoermiddel() {}
    public Vervoermiddel(String nummerplaat) {
        this.nummerplaat = nummerplaat;
    }

    public String getNummerplaat() {
        return nummerplaat;
    }

    public void setNummerplaat(String nummerplaat) {
        this.nummerplaat = nummerplaat;
    }
    
    public List<Onderhoudsbeurt> getOnderhoudsbeurten() {
        return Collections.unmodifiableList(onderhoudsbeurten);
    }

    public Onderhoudsbeurt geefOnderhoudsbeurt(LocalDate date){
        return onderhoudsbeurten.stream()
                                .filter(o -> o.bevatDatum(date))
                                .findAny()
                                .orElse(null);
    }
    
    public void addOnderhoudsbeurt(Onderhoudsbeurt ob){
        onderhoudsbeurten.add(ob);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vervoermiddel that = (Vervoermiddel) o;
        return Objects.equals(nummerplaat, that.nummerplaat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nummerplaat);
    }

    @Override
    public String toString() {
        return String.format("Vervoermiddel{nummerplaat=%s}%n", nummerplaat);
    }
}
