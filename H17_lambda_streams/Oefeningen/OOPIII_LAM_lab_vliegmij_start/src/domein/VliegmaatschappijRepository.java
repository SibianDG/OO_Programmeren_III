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
		return maatschappijen.stream()
				.filter(m -> m.getPartners().size() >= aantal)
				.collect(Collectors.toList());
	}

	public List<Vliegmaatschappij> geefAirlinesAlfabetischGesorteerd() {
		//return maatschappijen.stream().sorted((m1, m2) -> m1.getNaam().compareTo(m2.getNaam())).collect(Collectors.toList());
		return maatschappijen.stream()
				.sorted(Comparator.comparing(Vliegmaatschappij::getNaam))
				.collect(Collectors.toList());
	}

	public List<Vliegmaatschappij> geefAirlinesGesorteerdVolgensAantalPartners() {
		//return maatschappijen.stream().sorted((m1, m2) -> m1.getPartners().size() - m2.getPartners().size()).collect(Collectors.toList());
		return maatschappijen.stream()
				.sorted(Comparator.comparingInt(m -> m.getPartners().size()))
				.collect(Collectors.toList());
	}

	public Map<Vliegmaatschappij,Integer> geefAirlinesAantalKeerPartner() 
	{
		//TODO: MOEILIJKE!
		return maatschappijen.stream()
				.collect(Collectors.toMap(m -> m, mat -> (int) maatschappijen.stream()
																.filter(m2 -> m2.getPartners().contains(mat.getNaam()))
																.count()
				));
	}
	
	public String geefEersteAirlineStartendMet(String woord)
	{
		//TODO: keer kijken
		return maatschappijen.stream()
				.map(Vliegmaatschappij::getNaam)
				.filter(naam -> naam.startsWith(woord))
				.findAny()
				.orElse("Geen gevonden");
	}
	
	public Vliegmaatschappij geefEenAirlineMetPartner(String partner)
	{
		//TODO: moeilijke
		return maatschappijen.stream()
							.filter(m -> m.getPartners().contains(partner))
							.findAny()
							.orElse(null);
	}
}
