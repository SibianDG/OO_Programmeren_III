package domein;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import persistentie.PersistentieController;
import persistentie.VliegmaatschappijMapper;

public class VliegmaatschappijRepository 
{
	private final List<Vliegmaatschappij> maatschappijen;
    //private final VliegmaatschappijMapper vm;
	private final PersistentieController pc;
    
    public VliegmaatschappijRepository() 
    {
		//vm = new VliegmaatschappijMapper();
		pc = new PersistentieController();
        maatschappijen = pc.leesTekstBestand("airlines.txt");
		System.out.println(maatschappijen);
	}

	public List<Vliegmaatschappij> getMaatschappijen() {
		return maatschappijen;
	}

	public List<Vliegmaatschappij> geefAlleAirlinesMetMinstensAantalPartners(int aantal) {
		return maatschappijen.stream().filter(m -> m.getPartners().size() >= aantal).collect(Collectors.toList());
	}

	public List<Vliegmaatschappij> geefAirlinesAlfabetischGesorteerd() {
		//return maatschappijen.stream().sorted((m1, m2) -> m1.getNaam().compareTo(m2.getNaam())).collect(Collectors.toList());
		return maatschappijen.stream().sorted(Comparator.comparing(Vliegmaatschappij::getNaam)).collect(Collectors.toList());
	}

	public List<Vliegmaatschappij> geefAirlinesGesorteerdVolgensAantalPartners() {
		//return maatschappijen.stream().sorted((m1, m2) -> m1.getPartners().size() - m2.getPartners().size()).collect(Collectors.toList());
		return maatschappijen.stream().sorted(Comparator.comparingInt(m -> m.getPartners().size())).collect(Collectors.toList());
	}

	public Map<Vliegmaatschappij,Integer> geefAirlinesAantalKeerPartner() 
	{
		//todo
		return null;
	}
	
	public String geefEersteAirlineStartendMet(String woord)
	{
		return maatschappijen.stream().filter(m -> m.getNaam().startsWith(woord)).findFirst().get().getNaam();
	}
	
	public Vliegmaatschappij geefEenAirlineMetPartner(String partner)
	{
		//todo
		//return maatschappijen.stream().anyMatch(m -> m.getPartners().stream().anyMatch(p -> p.equals(partner)));
		return null;
	}
}
