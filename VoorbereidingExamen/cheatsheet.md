# Cheatsheet
## Comparator

```java
boeken.sort(new Comparator<Boek>() {
    @Override
    public int compare(Boek o1, Boek o2) {
        int res = o1.getTitel().compareTo(o2.getTitel());
        if (res != 0)
            return res;
        return Long.compare(o2.getIsbn_nr(), o1.getIsbn_nr());
    }
});

// of binnenklasse zie H16_collections/Theorie/OOPIII_COL_collections_kleine_oef_opgave/src/ui/OefAlgoritme.java

boeken.sort(Comparator.comparing(Boek::getTitel).thenComparing(Comparator.comparing(Boek::getIsbn_nr).reversed()));
```

## Linked list

```java
// ITERATOR ACHTERUIT
ListIterator<T> iterator = lijst.listIterator(lijst.size());
while (iterator.hasPrevious()){
    System.out.printf("%s ", iterator.previous());
}
```

## Onderhoud samenvoegen als datum gelijk is

```java
Iterator<Onderhoud> iterator = lijstOnderhoud.iterator();
Onderhoud onderhoud = null;
Onderhoud onderhoudNext = null;

while (iterator.hasNext()){
    onderhoud = onderhoudNext;
    onderhoudNext = iterator.next();
    if (onderhoud != null && onderhoud.getNummerplaat().equals(onderhoudNext.getNummerplaat())){
        if (onderhoud.getEinddatum().plusDays(1).equals(onderhoudNext.getBegindatum()))
        {//samenvoegen:
            onderhoud.setEinddatum(onderhoudNext.getEinddatum());
            iterator.remove();
            onderhoudNext = onderhoud;
        }
    }
}
```

## Compairing Strings

```java
System.out.printf("strings greater than m sorted descending: %s%n",
 Arrays.stream(strings)
       .filter(s -> s.compareToIgnoreCase("m") > 0)
       .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
       .collect(Collectors.toList()));
```

## IntStream

```java
System.out.printf("Product via reduce method: %d%n", 
 IntStream.of(values)
          .reduce(1, (x, y) -> x * y));
```

## inlezen txt via Stream

```java
public List<Bier> inlezenBieren(String naamBestand) {
    List<Bier> li = new ArrayList<>();
    try (Stream<String> stream = Files.lines(Paths.get(naamBestand))){
        stream.forEach(regel -> {
            Scanner scanner = new Scanner(regel);
            li.add(new Bier(scanner.next(), scanner.next(), scanner.nextDouble(), scanner.nextDouble(), scanner.next()));
        });
    } catch (IOException ieo){
        ieo.printStackTrace();
    }
    return li;
}
```

## Map to String

```java
private <K, V> String overzichtToString(Map<K, V> map)
{  //hulp voor map --> String
    return map.entrySet()
            .stream()
            .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
            .collect(Collectors.joining());
}
```

## List -> map <String, List>

```java
public Map<String, List<Bier>> opzettenOverzichtBierenPerSoort() {
    return bieren.stream()
            //.collect(Collectors.groupingBy(Bier::getSoort)); //default Hashmap key = category , val = List<obj>
            .collect(Collectors.groupingBy(Bier::getSoort, TreeMap::new, Collectors.toList()));
}
```










## Predicate

```java
Predicate<Employee> fourToSixThousand = 
 e -> (e.getSalary() >= 4000 && e.getSalary() <= 6000);

// Display Employees with salaries in the range $4000-$6000
// sorted into ascending order by salary
list.stream().filter(fourToSixThousand)
```

## Function

```java
// Functions for getting first and last names from an Employee
Function<Employee, String> byFirstName = Employee::getFirstName;
Function<Employee, String> byLastName = Employee::getLastName;

// Comparator for comparing Employees by first name then last name
Comparator<Employee> lastThenFirst = 
 Comparator.comparing(byLastName).thenComparing(byFirstName);
```

