package domein;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import persistentie.PersistentieController;

public class Zoo {//DUMMY

    private List<Verzorger> verzorgers;
    private List<Dier> dieren;
    private List<Gebouw> gebouwen;

    public Zoo() {
        this.dieren = new PersistentieController().geefDieren();
        this.verzorgers = new PersistentieController().geefVerzorgers();
        this.gebouwen = new PersistentieController().geefGebouwen();
    }

    /*
     * Geeft alle dieren terug die behoren tot de diersoort met de opgegeven naam. De lijst van
     * dieren moet gesorteerd zijn op gewicht (laag naar hoog).
     */
    public List<Dier> geefDierenVanSoortMetNaam(String soortNaam) {
//--
        //return Arrays.asList(new Dier(1, soortNaam, 50.0, new Soort(soortNaam)));


//--
        return dieren.stream()
                .filter(dier -> dier.getSoort().getNaam().equalsIgnoreCase(soortNaam))
                .sorted(Comparator.comparing(Dier::getGewicht))
                .collect(Collectors.toList());
        //TODO
        //return null;
    }

    /*
     * Geeft het gemiddelde gewicht terug van alle dieren die verblijven in het gebouw met de
     * opgegeven naam. Geeft 0 terug indien er geen gebouw is met deze naam.
     */
    public double geefGemiddeldeGewichtVanDierenInGebouwMetNaam(String gebouwNaam) {
//--
//        if (gebouwNaam.equals("Reptielen")) {
//            return 75.0;
//        } else {
//            return 5.0;
//        }
        //Optional<Gebouw> gebouw = gebouwen.stream()
        //                        .filter(g -> g.getNaam().equalsIgnoreCase(gebouwNaam))
        //                        .findAny();
        //gebouw.isPresent()
        Gebouw gebouw = gebouwen.stream()
                                .filter(g -> g.getNaam().equalsIgnoreCase(gebouwNaam))
                                .findAny().orElse(null);
        if (gebouw != null){
            return gebouw.getDieren().stream()     //Stream<Dier>
                    //.map(Dier::getGewicht)  //Stream<Double>
                    .mapToDouble(Dier::getGewicht) //DoubleStream
                    .average().getAsDouble();
        }
        return 0;
//--
        //TODO
        //return 0;
    }

    /*
     * Geeft de namen van de dieren terug die verzorgd worden door de verzorger met het opgegeven
     * nummer. Geeft een lege lijst terug indien er geen verzorger is met dit nummer.
     */
    //--
    public List<String> geefNamenVanDierenVanVerzorgerMetNummer(int verzorgerNummer) {

//--
        //if (verzorgerNummer == 1) {
        //    return Arrays.asList("Kroky", "Happy");
        //} else {
        //    return Arrays.asList("Alvin", "Floppy", "Fluffie");
        //}
        Verzorger verzorger = verzorgers.stream()
                                        .filter(v -> v.getNummer() == verzorgerNummer)
                                        .findAny()
                                        .orElse(null);
        if (verzorger != null) {
            return verzorger.getDieren().stream()   //Stream<Dier>
                                        .map(Dier::getNaam) //Stram<String>
                                        .collect(Collectors.toList());
        }
        return new ArrayList<>();

//-- 
    }

//    /*
//     * Geeft een lijst terug van verzorgers die een of meerdere dieren verzorgen die verblijven in
//     * het gebouw met de opgegeven naam. Geeft een lege lijst terug indien er geen gebouw is met
//     * deze naam.
    //    * NU DUMMY UITWERKING voor test gebruik in deze toepassing 
//  *  NIET VERDER UITWERKEN
//     */
    public List<Verzorger> verzorgersInGebouwMetNaam(String gebouwNaam) {
        //if (gebouwNaam.equalsIgnoreCase("Reptielen")) {
        //    return verzorgers;
        //} else {
        //    return new ArrayList<>();
        //}
        Gebouw gebouw = gebouwen.stream()
                                .filter(g -> g.getNaam().equalsIgnoreCase(gebouwNaam))
                                .findAny()
                                .orElse(null);
        if (gebouw != null){
            return verzorgers.stream()
                            .filter(v -> new ArrayList(v.getDieren()).removeAll(gebouw.getDieren()))
                            .collect(Collectors.toList());

        }
        return null;

    }

    /**
     * De methode maakOverzichtVolgensSoort geeft een overzicht (Map) terug van
     * alle dieren per Soort.
     */
    //TODO
    // TODO METHODE ....maakOverzichtVolgensSoort() ... hier uitschrijven
    //
    public Map<Soort, List<Dier>> maakOverzichtVolgensSoort(){
        return dieren.stream()
                    .collect(Collectors.groupingBy(Dier::getSoort, TreeMap::new, Collectors.toList()));
    }
}
