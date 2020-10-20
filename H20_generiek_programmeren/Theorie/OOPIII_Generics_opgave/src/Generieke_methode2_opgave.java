import java.util.*;
//Gegeven:

abstract class Dier {
}

interface Huisdier {
}

class Hond extends Dier implements Huisdier {
}

class Kat extends Dier implements Huisdier {
}

//Gevraagd:
//schrijf een klasse die een set van Kat, Hond of Huisdier kan bevatten
//class VerzamelingHuisdier<   >
//------

/*
{
	//attribuut "huisdieren" = set van Kat, Hond of Huisdier
	//--------------------------------------------------------------------------

    //getHuisdieren
	//-------------------

    //methode add: een dier toevoegen in de set
	//------------------------------------------------------------

}

 */

class Tools {
//methode bevatHuisdier met twee argumenten: een huisdier en een set van Kat, Hond of Huisdier.
//Geeft true terug indien het huisdier in de set voorkomt, anders false.

//schrijf de static methode bevatHuisdier d.m.v. wildcards
//------------------------------------------------------------------------------
//schrijf de static methode bevatHuisdier2 d.m.v. generieke methode
//---------------------------------------------------------------------------------------------
}

public class Generieke_methode2_opgave {

    public static void main(String args[]) {
        Kat kat = new Kat();
        Hond hond = new Hond();
        Huisdier huisdier = new Hond();

        VerzamelingHuisdier<Kat> katten = new VerzamelingHuisdier<>();
        katten.add(kat);
        VerzamelingHuisdier<Hond> honden = new VerzamelingHuisdier<>();

        VerzamelingHuisdier<Huisdier> huisdieren = new VerzamelingHuisdier<>();
        huisdieren.add(huisdier);

        boolean komtVoor = Tools.bevatHuisdier(kat, katten.getHuisdieren());
        System.out.println("correct = true;  " + komtVoor);
        komtVoor = Tools.bevatHuisdier(hond, honden.getHuisdieren());
        System.out.println("correct = false;  " + komtVoor);
        komtVoor = Tools.bevatHuisdier(huisdier, huisdieren.getHuisdieren());
        System.out.println("correct = true;  " + komtVoor);
        //compileerfout: komtVoor = Tools.bevatHuisdier(kat, new HashSet<Dier>());

        komtVoor = Tools.bevatHuisdier2(kat, katten.getHuisdieren());
        System.out.println("correct = true;  " + komtVoor);
        komtVoor = Tools.bevatHuisdier2(hond, honden.getHuisdieren());
        System.out.println("correct = false;  " + komtVoor);
        komtVoor = Tools.bevatHuisdier2(huisdier, huisdieren.getHuisdieren());
        System.out.println("correct = true;  " + komtVoor);
    }

}
