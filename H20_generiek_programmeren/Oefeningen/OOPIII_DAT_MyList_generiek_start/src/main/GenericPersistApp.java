package main;

import domein.DomeinController;

public class GenericPersistApp {

    public static void main(String arg[]) {
        new DomeinController().persisteerBierGegevensAlsObject("C:\\Users\\sibia\\OneDrive - Hogeschool Gent\\2e bach\\OO_Programmeren_III\\H20_generiek_programmeren\\Oefeningen\\OOPIII_DAT_MyList_generiek_start\\bieren.txt",
                "C:\\Users\\sibia\\OneDrive - Hogeschool Gent\\2e bach\\OO_Programmeren_III\\H20_generiek_programmeren\\Oefeningen\\OOPIII_DAT_MyList_generiek_start\\bierenListObj.dat");
    }

}