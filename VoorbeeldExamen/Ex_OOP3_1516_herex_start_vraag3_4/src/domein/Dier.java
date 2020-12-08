package domein;

import java.io.Serializable;
import javax.persistence.*;
//@Entity
//@NamedQuery(name = "Dier.metAlsSoortNaam", query = "SELECT d FROM Dier d WHERE d.soort.naam = :soortNaam ORDER BY d.gewicht ASC")
public class Dier implements Serializable {
    private static final long serialVersionUID = 1L;
    //@Id
    private int nummer;
    private String naam;
    private double gewicht;
    //@ManyToOne
    private Soort soort;

    protected Dier() {
    }

    public Dier(int nummer, String naam, double gewicht, Soort soort) {
        this.nummer = nummer;
        this.naam = naam;
        this.gewicht = gewicht;
        this.soort = soort;
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

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public Soort getSoort() {
        return soort;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.nummer;
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
        final Dier other = (Dier) obj;
        return this.nummer == other.nummer;
    }

    @Override
    public String toString() {
        return naam;
    }
}
