package domein;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity //je moet klasse als entiteit aanduiden
@Table(name = "docenten")
public class Docent implements Serializable {  //best serialable best voor JPA
    private static final long serialVersionUID = 1L;
    @Id // er moet prim key zijn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "PERSOONEELSNR")
    private int docentNr;
    private String voornaam;
    private String familienaam;
    private BigDecimal wedde;

    protected Docent() { //Nodig voor JPA

    }

    public Docent(int docentNr, String voornaam, String familienaam, BigDecimal wedde) {
        this.docentNr = docentNr;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wedde = wedde;
    }

    public int getDocentNr() {
        return docentNr;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public BigDecimal getWedde() {
        return wedde;
    }

    public void opslag(BigDecimal bedrag){
        wedde = wedde.add(bedrag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Docent docent = (Docent) o;
        return docentNr == docent.docentNr;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + docentNr;
        return result;
        //return Objects.hash(docentNr);
    }

    @Override
    public String toString() {
        return "Docent{" +
                "docentNr=" + docentNr +
                ", voornaam='" + voornaam + '\'' +
                ", familienaam='" + familienaam + '\'' +
                ", wedde=" + wedde +
                '}';
    }


}
