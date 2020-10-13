package domein;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DomeinController
{
    private BierWinkel bierWinkel;

    public DomeinController()
    {
        bierWinkel = new BierWinkel();
    }
  
    public long geefAantalBierenMetMinAlcoholPercentage(double percentage)
    {
    	return bierWinkel.geefAantalBierenMetMinAlcoholPercentage(percentage);
    }
    
    public List<String> geefLijstAlleBierenMetMinAlcoholPercentage(double percentage)
    {
    	return bierWinkel.geefAlleBierenMetMinAlcoholPercentage(percentage) //List<Bier>
                .stream()
                .map(Bier::toString)
                .collect(Collectors.toList());
    }
    
    public List<String> geefAlleBieren()
    {
        return bierWinkel.getBieren().stream().map(Bier::toString).collect(Collectors.toList());
    }
    
    public String geefNamenBieren()
    {
        return bierWinkel.geefNamenBieren().stream().collect(Collectors.joining("\n"));
    }
    
    public String geefBierMetHoogsteAlcoholPercentage()
    {
        return bierWinkel.geefBierMetHoogsteAlcoholPercentage().toString();
    }
    
    public String geefBierMetLaagsteAlcoholPercentage()
    {
        return bierWinkel.geefBierMetLaagsteAlcoholPercentage().toString();

    }
    
    public List<String> geefGeordendOpAlcoholGehalteEnNaam()
    {
       return bierWinkel.geefGeordendOpAlcoholGehalteEnNaam().stream().map(Bier::toString).collect(Collectors.toList());
    }
    
    public String geefAlleNamenBrouwerijen()
    {
        return bierWinkel.geefAlleNamenBrouwerijen().stream().collect(Collectors.joining("\n"));
    }
    
    public String geefAlleNamenBrouwerijenMetWoord(String woord)
    {
        return bierWinkel.geefAlleNamenBrouwerijenMetWoord(woord).stream().collect(Collectors.joining("\n"));
    }

    public String opzettenAantalBierenPerSoort()
    {   //naar BierWinkel --> map<String, Long>
        return overzichtToString(bierWinkel.opzettenAantalBierenPerSoort());
    }

    public String opzettenOverzichtBierenPerSoort()
    {  // naar BierWinkel --> map<String, List<Bier>>
        return overzichtToString(bierWinkel.opzettenOverzichtBierenPerSoort());
    }


    private <K, V> String overzichtToString(Map<K, V> map)
    {  //hulp voor map --> String
         return map.entrySet()
                 .stream()
                 .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
                 .collect(Collectors.joining());
    }


}
