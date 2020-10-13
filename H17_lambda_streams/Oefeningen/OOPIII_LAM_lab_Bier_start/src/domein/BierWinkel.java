package domein;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import persistentie.PersistentieController;

public class BierWinkel {

    private final List<Bier> bieren;
    private PersistentieController pc = new PersistentieController();

    public BierWinkel() {
        bieren = pc.inlezenBieren("C:\\Users\\sibia\\OneDrive - Hogeschool Gent\\2e bach\\OO_Programmeren_III\\H17_lambda_streams\\Oefeningen\\OOPIII_LAM_lab_Bier_start\\bieren.txt");
    }

    public List<Bier> getBieren() {
        return bieren;
    }

    public long geefAantalBierenMetMinAlcoholPercentage(double percentage) {
        return bieren.stream()
                .filter(b -> b.getAlcoholgehalte() >= percentage)
                .count()
                ;
    }

    public List<Bier> geefAlleBierenMetMinAlcoholPercentage(double percentage) {
        return bieren.stream()
                .filter(b -> b.getAlcoholgehalte() >= percentage)
                .collect(Collectors.toList())
                ;
    }

    public List<String> geefNamenBieren() {
        return bieren.stream()   //Stream <Bier>
                .map(Bier::getNaam)  //Stream<String>
                .collect(Collectors.toList()); //List<String>
    }

    //Bier met hoogst aantal graden
    public Bier geefBierMetHoogsteAlcoholPercentage() {
        return bieren.stream()
                .max(Comparator.comparing(Bier::getAlcoholgehalte))//Optional<Bier>
                .get(); //ALS NIET ZEKER --> .orElse(null)
    }

    //Bier met laagst aantal graden
    public Bier geefBierMetLaagsteAlcoholPercentage() {
        return bieren.stream()
                .min(Comparator.comparing(Bier::getAlcoholgehalte))//Optional<Bier>
                .get(); //ALS NIET ZEKER --> .orElse(null)
    }
    //EXTRA oef --> hoogste alcoholpercentage afgeven

    public double geefHoogsteAlcohol(){
        return bieren.stream() //Stream<Bier>
                //.map(Bier::getAlcoholgehalte) //Stream<Double>
                .mapToDouble(Bier::getAlcoholgehalte) //DoubleStream is Numeriek!
                .max() //OptionalDouble
                .getAsDouble();
    }

    /*Zorg ervoor dat het resultaat gesorteerd wordt op alcoholgehalte van hoog naar laag,
     en bij gelijk aantal graden op naam (alfabetisch).
     */
    public List<Bier> geefGeordendOpAlcoholGehalteEnNaam() {
        return bieren.stream()
                .sorted(Comparator.comparing(Bier::getAlcoholgehalte).reversed()
                        .thenComparing(Bier::getNaam))
                .collect(Collectors.toList());
    }

    //Alle brouwerijen
    public List<String> geefAlleNamenBrouwerijen() {
        return bieren.stream().map(Bier::getBrouwerij).distinct().collect(Collectors.toList());
    }

    //Alle brouwerijen die het woord "van" bevatten
    public List<String> geefAlleNamenBrouwerijenMetWoord(String woord) {
        return bieren.stream().map(Bier::getBrouwerij)
                .filter(naam -> naam.contains("van"))
                .distinct().collect(Collectors.toList());
    }

    public Map<String, List<Bier>> opzettenOverzichtBierenPerSoort() {
        return bieren.stream()
                //.collect(Collectors.groupingBy(Bier::getSoort)); //default Hashmap key = category , val = List<obj>
                .collect(Collectors.groupingBy(Bier::getSoort, TreeMap::new, Collectors.toList()));
    }

    public Map<String, Long> opzettenAantalBierenPerSoort() {
        return bieren.stream()
                .collect(Collectors.groupingBy(Bier::getSoort, TreeMap::new, Collectors.counting()));
    }
    
    
}
