package ui;

import java.util.*;
import java.util.stream.Collectors;

public class KleineOefeningenList_Opgave {

    private static final String COLORS[] = {"red", "white", "blue"};

    //declareer list en list2 van type List. De collections zullen Strings bijhouden.
    //------------------------------------------------------------------------------
    private List<String> list, list2;
    public KleineOefeningenList_Opgave() {

        // cre�er arrayList list en vul op met { "red", "white", "blue" }  (= 1 instructie)
        //----------------------------------------------------
        list = new ArrayList<>(Arrays.asList(COLORS));
        weergevenLijst("oplossing: red white blue ", list);

        // Cre�er een tweede lege arrayList (list2)
        // -------------------------------------------
        list2 = new ArrayList<>();

        // alle elementen van list kopi�ren naar een tweede arrayList list2 (= 1
        // instructie)
        // ---------------------------------------------------------------------------------
        list2.addAll(list);

        naarHoofdletterOmzetten(list);
        weergevenLijst("oplossing: alle elementen van list in hoofdletters", list);

        list = naarKleineLettersOmzetten(list);
        weergevenLijst("oplossing: alle elementen van list in kleine letters", list);

    } // end constructor

    public <T> void weergevenLijst(String oplossing, List<T> list) {
        //Geef alle elementen van de List weer (laat een spatie tussen elk element).
        //Gebruik printf*/
        //--------------------------------------------------------------------------
        System.out.printf("%s%n%s", oplossing, "           ");
        //for (T el: list){
        //    System.out.println("%s ", el);
        //}
        for(T elem: list) {
            System.out.printf("%s ", elem);
        }
       list.forEach(el -> System.out.printf("%s ", el));
       System.out.println("\n");
    }
    public void naarHoofdletterOmzetten(List<String> list) {
        //Alle strings van list worden omgezet naar hoofdletters
        //-------------------------------------------------------------
        //JAVA 7: //JAVA 8:

        list.replaceAll(el -> el.toUpperCase());

        //werkt niet! Local
        list = list.stream().map(el -> el.toUpperCase()).collect(Collectors.toList());

    }

    public List<String> naarKleineLettersOmzetten(List<String> list)
    {
       ////De strings van list worden omgezet naar kleine letters en worden
        //als nieuwe List<String> teruggegeven.
       //-------------------------------------------------------------
       //JAVA 8:

        return list.
                stream().
                        map(el -> el.toLowerCase())
                            .collect(Collectors.toList());
    }

    public static void main(String args[]) {
        new KleineOefeningenList_Opgave();
    }

} // end class KleineOefeningenList_Opgave
