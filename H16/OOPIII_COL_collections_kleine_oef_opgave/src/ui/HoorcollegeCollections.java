import java.util.*;

public class HoorcollegeCollections {
    public static void main(String[] args) {
        new HoorcollegeCollections().run();
    }

    private void run() {
        System.out.println("C I");
        String[] ar = {"Jan", "Piet", "Adriaan", "Corneel"};
        List<String> li;
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
}
