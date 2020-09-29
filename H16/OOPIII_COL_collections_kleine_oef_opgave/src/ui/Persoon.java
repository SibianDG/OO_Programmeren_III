package ui;

public class Persoon {

    private String naam;
    private int leeftijd;

    public Persoon(String naam, int leeftijd) {
        setNaam(naam);
        setLeeftijd(leeftijd);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    @Override
    public String toString(){
        return String.format("%s is %d jaar oud.", naam, leeftijd);
    }
}
