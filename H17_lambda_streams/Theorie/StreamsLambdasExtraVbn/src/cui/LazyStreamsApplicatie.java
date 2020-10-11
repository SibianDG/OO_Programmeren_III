/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**Voorbeeld overgenomen uit "Functional Programming in Java"
 * van Venkat Subramaniam.
 */

public class LazyStreamsApplicatie
{
    private static int getLength(String name)
    {
        System.out.println("ophalen lengte van " + name);
        return name.length();
    }
    
    private static String changeToUpper(String name)
    {
        System.out.println("Omzetten naar hoofdletters : " + name);
        return name.toUpperCase();
    }
    
    public static void main(String[] args)
    {
        List<String> names = Arrays.asList("Brad" , "Kate" , "Kimmy", "Jack" , "Joey");
        
        Stream <String> namenMet3letters = 
                names.stream()
                     .filter(name -> getLength(name)==3)
                     .map(name -> changeToUpper(name));
              
        System.out.println("Stream gecreëerd, gefilterd, gemapt,...");
        System.out.println("nu klaar om findFirst op te roepen...");
        
//        Dit geeft problemen bij niet aanwezig zijn van een element 
//        dat voldoet aan de filter
        String eersteNaamMet3letters = 
                namenMet3letters.findFirst()
                                .get();
        System.out.println(eersteNaamMet3letters);
        
//        Beter:
         eersteNaamMet3letters = 
                namenMet3letters.findFirst()
                                .orElse("Geen naam met 3 letters");
        System.out.println(eersteNaamMet3letters);
    }
}
