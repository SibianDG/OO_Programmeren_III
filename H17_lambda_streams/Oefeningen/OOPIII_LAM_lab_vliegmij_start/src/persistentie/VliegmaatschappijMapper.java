package persistentie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import domein.Vliegmaatschappij;

public class VliegmaatschappijMapper
{
    public List<Vliegmaatschappij> leesTekstBestand (String naamBestand)
    {
    	List<Vliegmaatschappij> airlines=new ArrayList<>();
    	try (Stream<String> stream = Files.lines(Paths.get(naamBestand))){
            stream.forEach(regel -> airlines.add(new Vliegmaatschappij(regel.split(","))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return airlines;
    }
}

