package testen;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domein.Bier;
import domein.MyListIterable;

public class TestLeesBestand {

    private final Object[][] resultaat = {{"WestVleteren_Blond", 5.0},
    {"Tripel_Kanunnik", 8.2}, {"Black_Albert", 13.0},
    {"Rochefort_10", 11.0},
    {"Alpaïde", 9.5}, {"Cantillon_Geuze", 5.0},
    {"Moinette_Blonde", 8.5}, {"Wilderen_Goud", 6.0},
    {"Tripel_Karmeliet", 8.4}, {"Westmalle_Tripel", 9.5},};

    private MyListIterable<Bier> leesBestand() {
        
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(new File("C:\\Users\\sibia\\OneDrive - Hogeschool Gent\\2e bach\\OO_Programmeren_III\\H20_generiek_programmeren\\Oefeningen\\OOPIII_DAT_MyList_generiek_start\\bierenListObj.dat").toPath()))) {
            return (MyListIterable<Bier>) in.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            return null;
        }
    }

    @Test
    public void leesTest() {
        MyListIterable<Bier> myList = leesBestand();

        assert myList != null;
        Assertions.assertFalse(myList.isEmpty());

        for (int rijIndex = 0; rijIndex < resultaat.length; rijIndex++) {
            Bier bier = myList.removeFromFront();
            Assertions.assertEquals((String) resultaat[rijIndex][0], bier.getBierNaam());
            Assertions.assertEquals((Double) resultaat[rijIndex][1], bier.getAlcohol(), 0.0);
        }

        Assertions.assertTrue(myList.isEmpty());
    }
}

