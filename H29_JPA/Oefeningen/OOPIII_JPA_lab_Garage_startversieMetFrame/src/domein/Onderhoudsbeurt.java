package domein;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Onderhoudsbeurt.opDatum",
                query = "SELECT o FROM Onderhoudsbeurt o WHERE :dat BETWEEN o.begindatum AND o.einddatum")
})
public class Onderhoudsbeurt implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate begindatum;
   
    private LocalDate einddatum;
    @ManyToOne
    private Vervoermiddel vervoermiddel;

    protected Onderhoudsbeurt() {
    }

    public Onderhoudsbeurt(LocalDate begindatum, LocalDate einddatum, Vervoermiddel vervoermiddel) {
        this.begindatum = begindatum;
        this.einddatum = einddatum;
        this.vervoermiddel = vervoermiddel;
    }

    public boolean bevatDatum(LocalDate date){
        return !(date.isBefore(begindatum) || date.isAfter(einddatum));
    }

    public LocalDate getBegindatum() {
        return begindatum;
    }

    public void setBegindatum(LocalDate begindatum) {
        this.begindatum = begindatum;
    }

    public LocalDate getEinddatum() {
        return einddatum;
    }

    public void setEinddatum(LocalDate einddatum) {
        this.einddatum = einddatum;
    }

    public Vervoermiddel getVervoermiddel() {
        return vervoermiddel;
    }

    public void setVervoermiddel(Vervoermiddel vervoermiddel) {
        this.vervoermiddel = vervoermiddel;
    }

}
