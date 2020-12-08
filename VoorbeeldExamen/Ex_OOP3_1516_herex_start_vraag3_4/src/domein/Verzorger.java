package domein;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@Entity
public class Verzorger implements Serializable {
    private static final long serialVersionUID = 1L;
    //@Id
    private int nummer;
    private String naam;
    //@ManyToMany
    private final List<Dier> dieren = new ArrayList<>();

    protected Verzorger() {
    }

    public Verzorger(int nummer, String naam) {
        this.nummer = nummer;
        this.naam = naam;
    }

    public int getNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<Dier> getDieren() {
        return Collections.unmodifiableList(dieren);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.nummer;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Verzorger other = (Verzorger) obj;
        return this.nummer == other.nummer;
    }

    @Override
    public String toString() {
        return naam;
    }

    public void addDier(Dier dier) {
        dieren.add(dier);
    }
}
