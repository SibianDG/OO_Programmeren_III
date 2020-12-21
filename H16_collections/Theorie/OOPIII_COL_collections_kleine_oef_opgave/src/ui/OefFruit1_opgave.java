package ui;

import java.util.*;

public class OefFruit1_opgave {
    public static void main(String[] args) {
        String[] arX = {"appel", "peer", "citroen", "kiwi"},
                arY = {"banaan", "mango", "citroen", "kiwi", "zespri"};

        /*
	Behandel arX en arY als Collections en maak gebruik van de bulk
	peraties om volgende output te leveren:
		In y zit extra [banaan, mango, zespri]
		In x zit extra [appel, peer]
		x en y hebben gemeenschappelijk [citroen, kiwi]
		https://stackoverflow.com/questions/29284061/find-the-uncommon-common-all-elements-from-two-different-array-list-objects-in
         */
        Collection<String> liX = new ArrayList<>(Arrays.asList(arX)),
                    liY = new ArrayList<>(Arrays.asList(arY));

        Collection<String> res = new ArrayList<>(liY);
        //res.removeAll(liX);
        //res.removeAll(liY);
        liX.retainAll(liY);

        System.out.println("X: " + liX);
        System.out.println("Y: " + liY);


    }
}
