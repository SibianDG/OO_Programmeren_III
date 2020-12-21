package ui;

import java.util.*;
import java.util.stream.Collectors;

class CollectionOperaties {
    
    //methode verwijderOpLetter
    //-------------------------

    public static boolean verwijderOpLetter(List<String> list, char c){
        return list.removeIf(el -> el.startsWith(String.valueOf(c)));
    }

    public static boolean verwijderSequence(List<String> list, String s){
        int first = list.indexOf(s);
        if (first < 0)
            return false;
        int last = list.lastIndexOf(s);
        list.subList(first, last+1).clear();
        return true;
    }

    public static boolean addOrdered(List<String> list, String elem) {
        int index =Collections.binarySearch(list, elem);
        if(index >=0) {
            list.add(index*-1-1, elem);
            return false;
        } else return true;
    }
}

public class OefFruit2_opgave {

    public static void main(String[] args) {
        String[][] kist = {{"appel", "peer", "citroen", "kiwi", "perzik"},
        {"banaan", "mango", "citroen", "kiwi", "zespri", "pruim"},
        {"peche", "lichi", "kriek", "kers", "papaya"}};

        List<String> list;
        //Versie 1 (maar kan beter)
        list = new ArrayList<>();
        for (String[] eenRij: kist){
            list.addAll(Arrays.asList(eenRij));
        }
        //Beter via stream
        //Arrays.asList(kist).stream();
        list = Arrays.stream(kist) //stream<String[]> => Willen stream<String>
            //.map(Arrays::stream); //stream <Stream <String> > willen we niet
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());
        String[] mand;

//Voeg de verschillende kisten samen in een ArrayList list.
//--------------------------------------------------------


        CollectionOperaties.verwijderOpLetter(list, 'p');
        System.out.println("na verwijder letter ('p') :  " + list + "\n");

        CollectionOperaties.verwijderSequence(list, "kiwi");
        System.out.println("na verwijder sequence (kiwi) : " + list + "\n");

        CollectionOperaties.addOrdered(list, "sapodilla");
        System.out.printf("Na toeveogen sapodilla: %s%n", list);

//Plaats het resultaat terug in een array mand en sorteer die oplopend.
//---------------------------------------------------------------------
        mand = list.toArray(new String[0]);
        Arrays.sort(mand);

//Geef de inhoud van de array "mand" terug
//----------------------------------------


    }
}
