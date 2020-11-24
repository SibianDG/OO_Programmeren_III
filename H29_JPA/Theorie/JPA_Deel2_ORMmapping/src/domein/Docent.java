package domein;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity //je moet klasse als entiteit aanduiden
@NamedQueries({
        @NamedQuery(name = "Docent.findAll",
                query = "SELECT d FROM Docent d"),
        @NamedQuery(name = "Docent.docentenInTweeCampussen",
                query = "SELECT d FROM Docent d WHERE :campusA MEMBER OF d.campussen AND :campusB MEMBER OF d.campussen"),
})
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
    @ManyToMany(fetch = FetchType.EAGER) //DEFAULT bij x to many is LAZY
    private Set<Campus> campussen = new HashSet<>();
    @ManyToOne
    private Werkruimte werkruimte;

    protected Docent() { //Nodig voor JPA

    }

    public Docent(int docentNr, String voornaam, String familienaam, BigDecimal wedde) {
        this.docentNr = docentNr;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wedde = wedde;
    }

    public Set<Campus> getCampussen() {
        return Collections.unmodifiableSet(campussen);
    }

    public void addCampus(Campus c){
        campussen.add(c);
    }

    public void removeCampus(Campus c){
        campussen.remove(c);
    }

    public Werkruimte getWerkruimte() {
        return werkruimte;
    }

    public void setWerkruimte(Werkruimte werkruimte) {
        this.werkruimte = werkruimte;
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
                ", werkruimte=" + werkruimte +

                '}';
    }


}
