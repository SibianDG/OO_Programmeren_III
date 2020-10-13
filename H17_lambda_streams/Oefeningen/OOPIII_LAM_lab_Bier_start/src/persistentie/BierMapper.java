package persistentie;

import domein.Bier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class BierMapper {

    public List<Bier> inlezenBieren(String naamBestand) {
        List<Bier> li = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(naamBestand))){
            stream.forEach(regel -> {
                Scanner scanner = new Scanner(regel);
                li.add(new Bier(scanner.next(), scanner.next(), scanner.nextDouble(), scanner.nextDouble(), scanner.next()));
            });
        } catch (IOException ieo){
            ieo.printStackTrace();
        }
        return li;
    }

}
