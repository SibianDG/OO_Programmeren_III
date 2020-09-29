package ui;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HoorcollegeCollections {
    public static void main(String[] args) {
        new HoorcollegeCollections().run2();
    }

    private void run() {
        System.out.println("C I");
        String[] ar = {"Jan", "Piet", "Adriaan", "Corneel"};

        System.out.println(Arrays.toString(ar));
        //Arrays.sort(ar);
        List<String> li;
        li = new ArrayList<>(Arrays.asList(ar)); //Collections alleen met collections te initialiseren,
        li = Arrays.asList(ar); //Kan toch ook?! => Verschil: asList() levert IMUTTABLE collectie => Niet wijzigbaar
        //VERSIE 3: VIA IMMUTABLE
        li = List.of("Jan", "Piet", "Joris", "Cornelis"); //Immutable versie
        li = new ArrayList<>(List.of("Jan", "Piet", "Joris", "Cornelis")); //echte mutable versie

        //List<String> li;
        li = new ArrayList<>();
        for (String el: ar){
            li.add(el);
        }
        System.out.println(li);

        li = new ArrayList<>(Arrays.asList(ar));
        System.out.println(li.get(1));
        li.set(1, li.get(1).toUpperCase());
        System.out.println(li);

        // V1: NIET DOEN!
        for (int i = 0; i < li.size(); i++) {
            System.out.println(li.get(i));
        }

        //V2: beter, maar niet oke
        Iterator<String> it = li.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

        //V3: goed, maar kan beter
        for (String s : li) {
            System.out.println(s);
        }

        //V4: Beste: Passieve Iterator
        li.forEach(el -> System.out.println(el));
        li.forEach(System.out::println);

        //Alle Pxx vervangen door hoofdletter

        //Werkt niet! Local
        for (String el: li){
            if (el.startsWith("P"))
                el = el.toUpperCase();
        }

        // explicit
        ListIterator<String> it2 = li.listIterator();
        while (it2.hasNext()){
            String el = it2.next();
            if (el.startsWith("P"))
                it2.set(el.toUpperCase());
        }
        System.out.println(li);

        li.removeIf(el -> el.startsWith("J"));
    }

    private void run2(){
        System.out.println("Intro mappen");
        List<Persoon> li = Arrays.asList(new Persoon("Jan", 20)
                , new Persoon("Piet", 30), new Persoon("Joris", 20)
                , new Persoon("Cornelis", 15)
        );
        Map<String, Persoon> map;
        map = li.stream().collect(Collectors.toMap(Persoon::getNaam, Function.identity()));
        System.out.println(map);
        System.out.println(map.get("Piet").getLeeftijd());
        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.entrySet());
    }
}
