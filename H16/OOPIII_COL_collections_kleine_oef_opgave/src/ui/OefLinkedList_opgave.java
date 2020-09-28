import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OefLinkedList_opgave {
    public OefLinkedList_opgave() {

        Integer getallen[] = {4, 3, 9, 7};


//Cre�er een 'arrayList' en vul op met de array "getallen" (1 instructie)
//-----------------------------------------------------------------------------------
        List<Integer> arrayList = new ArrayList<>(Arrays.asList(getallen));

//Cre�er de linkedList "lijstInteger" en vul op met de inhoud van de arrayList ( 1 instructie )
//--------------------------------------------------------------------------------
        List<Integer> lijstInteger = new LinkedList<>(arrayList);

        weergevenLijst("oplossing: 4 3 9 7", lijstInteger);

        metEenVerhogen(lijstInteger);
        weergevenLijst("oplossing: 5 4 10 8", lijstInteger);

        lijstInteger = metEenVerhogen_java8(lijstInteger);
        weergevenLijst("oplossing: 6 5 11 9", lijstInteger);

        String woorden[] = {"aaa", "bbb", "ccc"};

//Cre�er de LinkedList "lijstString" en vul op met de array "woorden" (1 instructie)
//-----------------------------------------------------------------------
        List<String> lijstString = new LinkedList<>(Arrays.asList(woorden));

        weergevenLijst("oplossing: aaa bbb ccc", lijstString);

        weergevenLijstOmgekeerdeVolgorde("oplossing: ccc bbb aaa", lijstString);

    }

    public void metEenVerhogen(List<Integer> lijst) //------------
    {
        //Alle elementen van de lijst wijzigen:
        //alle elementen worden met 1 verhoogd
        //JAVA 7:
        //for (int i = 0; i < lijst.size(); i++) {
        //    lijst.set(i, lijst.get(i)+1);
        //}
        //JAVA 8:
        //FIXME
        lijst = lijst.stream().map(i -> i+1).collect(Collectors.toList());
    }

    public List<Integer> metEenVerhogen_java8(List<Integer> lijst)
    {
        //De elementen van lijst worden met één verhoogd en worden
        //als nieuwe List<Integer> teruggegeven.
        //JAVA 8:
        return lijst.stream().map(i -> i+1).collect(Collectors.toList());
    }

    //generieke methode:
    public <T> void weergevenLijst(String oplossing, List<T> list) //---                                       -------------
    // Alle elementen van de Collection<Integer> weergeven
    //---------------------------------------------------
    {
        System.out.printf("%s%n%s", oplossing, "           ");
        list.forEach(el -> System.out.printf("%s ",el));
        System.out.println();
    }

    //generieke methode:
    public void weergevenLijstOmgekeerdeVolgorde(String oplossing, List<String> lijst) {
        // Alle elementen van de List<Integer> in omgekeerde volgorde weergeven
        //--------------------------------------------------------------------------------------
        System.out.printf("%s%n%s", oplossing, "           ");
        for (int i = lijst.size() - 1; i >= 0 ; i--) {
            System.out.printf("%s ", lijst.get(i));
        }

        System.out.println();


        System.out.println(
                "java 8 lijst omgekeerde volgorde: ");
        System.out.printf("%s%n%s", oplossing, "           ");
        //TODO: JAVA8:

        System.out.println();
    }

    public static void main(String[] args) {
        new OefLinkedList_opgave();
    }
}
