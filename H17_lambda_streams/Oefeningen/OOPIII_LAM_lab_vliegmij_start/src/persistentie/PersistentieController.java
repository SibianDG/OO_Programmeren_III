package persistentie;

import domein.Vliegmaatschappij;

import java.util.List;

public class PersistentieController {
    private VliegmaatschappijMapper vliegmaatschappijMapper;

    public List<Vliegmaatschappij> leesTekstBestand(String naamBestand) {
        if (vliegmaatschappijMapper == null) {
            vliegmaatschappijMapper = new VliegmaatschappijMapper();
        }
        return vliegmaatschappijMapper.leesTekstBestand(naamBestand);
    }
}
