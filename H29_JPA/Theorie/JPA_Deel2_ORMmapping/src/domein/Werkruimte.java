package domein;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Werkruimte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String lokaalCode;
    private String naam;
    private int aantalStoelen;
    private int aantalComputers;

    public Werkruimte(String lokaalCode, String naam, int aantalStoelen, int aantalComputers) {
        this.lokaalCode = lokaalCode;
        this.naam = naam;
        this.aantalStoelen = aantalStoelen;
        this.aantalComputers = aantalComputers;
    }

    protected Werkruimte() {
    }

    public String getLokaalCode() {
        return lokaalCode;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getAantalStoelen() {
        return aantalStoelen;
    }

    public void setAantalStoelen(int aantalStoelen) {
        this.aantalStoelen = aantalStoelen;
    }

    public int getAantalComputers() {
        return aantalComputers;
    }

    public void setAantalComputers(int aantalComputers) {
        this.aantalComputers = aantalComputers;
    }

    @Override
    public String toString() {
        return "Werkruimte{" +
                "lokaalCode='" + lokaalCode + '\'' +
                ", naam='" + naam + '\'' +
                ", aantalStoelen=" + aantalStoelen +
                ", aantalComputers=" + aantalComputers +
                '}';
    }


}
