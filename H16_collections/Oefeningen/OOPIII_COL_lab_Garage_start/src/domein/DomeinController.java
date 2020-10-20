package domein;

public class DomeinController {

    private final Garage garage;

    public DomeinController() {
        garage= new Garage("C:\\Users\\sibia\\OneDrive - Hogeschool Gent\\2e bach\\OO_Programmeren_III\\H16_collections\\Oefeningen\\OOPIII_COL_lab_Garage_start\\autoObjecten.dat"
                , "C:\\Users\\sibia\\OneDrive - Hogeschool Gent\\2e bach\\OO_Programmeren_III\\H16_collections\\Oefeningen\\OOPIII_COL_lab_Garage_start\\onderhoudObjecten.dat");
    } 

    public String autoMap_ToString() {
        return garage.autoMap_ToString();
    }

    public String autoOnderhoudMap_ToString() {
        return garage.autoOnderhoudMap_ToString(); 
    }

    public String overzicht_ToString() {
        return garage.overzicht_ToString();
    }
    
}
