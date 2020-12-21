package ui;

import java.util.*;
import java.util.stream.Collectors;

class Boek {
	private String titel;
	private long isbn_nr;

	public Boek(String titel, long isbn_nr) {
		this.titel = titel;
		this.isbn_nr = isbn_nr;
	}

	public String getTitel() {
		return titel;
	}

	public long getIsbn_nr() {
		return isbn_nr;
	}

	@Override
	public String toString() {
		return String.format("%s %d", titel, isbn_nr);
	}
}

public class OefAlgoritme {

	public OefAlgoritme() {
		List<Boek> boeken = new ArrayList<>();
		boeken.add(new Boek("How To Program", 1130927384L));
		boeken.add(new Boek("How To Program", 9830927384L));
		boeken.add(new Boek("JAVA", 1140927384L));
		boeken.add(new Boek("C++", 2200000000L));
		boeken.add(new Boek("C++", 8800000000L));

		// Sorteer de arrayList op titel in STIJGENDE volgorde. Bij gelijke titels
		// sorteren op isbn_nr in DALENDE volgorde.
		// ---------------------------------------------------------------

		boeken.sort(new BoekComparator());
		boeken.sort(new Comparator<Boek>() {
			@Override
			public int compare(Boek o1, Boek o2) {
				int res = o1.getTitel().compareTo(o2.getTitel());
				if (res != 0)
					return res;
				return Long.compare(o2.getIsbn_nr(), o1.getIsbn_nr());
			}
		});
		boeken.sort((Boek b1, Boek b2) -> {
			int res = b1.getTitel().compareTo(b2.getTitel());
			if (res != 0)
				return res;
			return Long.compare(b2.getIsbn_nr(), b1.getIsbn_nr());
		});
		boeken.sort((b1, b2) -> {
			int res = b1.getTitel().compareTo(b2.getTitel());
			if (res != 0)
				return res;
			return Long.compare(b2.getIsbn_nr(), b1.getIsbn_nr());
		});
		//NU PUUR JAVA9?
		//Draait volledig om:
		//boeken.sort(Comparator.comparing(Boek::getTitel).thenComparing(Boek::getIsbn_nr).reversed());
		boeken.sort(Comparator.comparing(Boek::getTitel).thenComparing(Comparator.comparing(Boek::getIsbn_nr).reversed()));

		//Collections.sort();
		System.out.println("gesorteerd : ");
		toonLijst(boeken);

		// Zet de arrayList om in omgekeerde volgorde (= 1 instructie)
		// ---------------------------------------------------------
		// java7

		Collections.reverse(boeken);
		System.out.println("omgekeerde volgorde : ");
		toonLijst(boeken);

		Integer[] getallen = { 6, 5, 9, 3 };
		// Toon het grootste element van de array getallen
		// -----------------------------------------------
		// java7
		System.out.printf("max: %d%n ", Collections.max(Arrays.asList(getallen)));
		//java 8
		Arrays.stream(getallen).max(Integer::compare);
		System.out.printf("max %d%n", Arrays.stream(getallen).
				max(Integer::compare).
				//get()
				orElse(null)
		);


		// Toon het kleinste element van de array getallen
		// -----------------------------------------------
		// java7
		// EXTRA: Zoek boek met titel "Java"
		System.out.printf("%s%n", boeken.stream().
				filter(el -> el.getTitel().equals("JAVA"))
				.findAny() //Optional<Boek>
				.orElse(null)
		);

	}

	public <E> void toonLijst(Collection<E> collection) {
		// java7
		for (E element : collection) {
			System.out.printf("%s%n", element);
		}

		System.out.println("\n");
	}

	public static void main(String args[]) {
		new OefAlgoritme();
	}

	//EEN COMPARATOR (als innerklasse: afgeschermd buitenwenwereld en innerklasse kan aan alle klassen in ...)
	private class BoekComparator implements Comparator<Boek> {

		@Override
		public int compare(Boek o1, Boek o2) {
			int res = o1.getTitel().compareTo(o2.getTitel());
			if (res != 0)
				return res;
			return Long.compare(o2.getIsbn_nr(), o1.getIsbn_nr());
		}
	}

}// einde klasse OefAlgoritme_Opgave
