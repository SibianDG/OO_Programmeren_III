package domein;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GarageController {

    private GarageBeheerder gb = new GarageBeheerder();

    public List<String> geefAutosZonderOnderhoudsbeurt() {
        return gb.geefAutosZonderOnderhoudsbeurtJPA().stream()
                .map(Auto::getNummerplaat)
                .collect(Collectors.toList());
    }

    public List<String> geefAutosMetOnderhoudsbeurt() {
        return gb.geefAutosMetOnderhoudsbeurtJPA().stream()
                .map(Auto::getNummerplaat)
                .collect(Collectors.toList());
    }

    public List<String> geefOnderhoudsbeurtenOpDatum(int jaar, int maand, int dag) {
        return gb.geefOnderhoudsbeurtenOpDatumJPA(LocalDate.of(jaar, maand, dag))
                .stream().map(Onderhoudsbeurt::getVervoermiddel)
                .map(Vervoermiddel::getNummerplaat)
                .collect(Collectors.toList());
    }

    public void close() {
        gb.closePersistentie();
    }

}
