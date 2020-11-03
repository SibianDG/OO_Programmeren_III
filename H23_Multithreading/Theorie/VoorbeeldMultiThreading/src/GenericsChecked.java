import java.util.*;

public class GenericsChecked {

    public static void main(String[] args) {
        new GenericsChecked().run();
    }

    private void run() {
        System.out.println("runtime type checking");
        List<Brok> brokList = new ArrayList<>(Arrays.asList(new Brok("Jan"), new Brok("Piet")));
        System.out.println(brokList);
        List rawList = brokList;
        System.out.println(rawList);
        //brokList.add("vreemde eend"); // KAN NIET => OK IS COMPILE TIME CHECK
        rawList.add("Vreemde eend");
        System.out.println(rawList); // GEEN RUNTIME CRASH met vreemde eend! Waarom? -> doet hier enkel toString op elke object
        //brokList.forEach(System.out::println); // geeft wel crash op vreemde eend
        List rawListChecked = Collections.checkedList(brokList, Brok.class); // NU RUNTIME CHECK OP TYPE
        rawListChecked.add("Vreemde eend"); //GEEFT RUNTIME CRASH   BIJ TOEVOEGING, dus voor gebruik
                // dus geen overwachte crash tijdens gebruik

        List listThreadSafe = Collections.synchronizedList(brokList); // Deze thread is Thread save? -> safe? (dus overhead toegevoegd)

    }
}
