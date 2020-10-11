package cui;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MapEnFlatMap
{

    public static void main(String[] args)
    {

        List<String> myList = Stream.of("a", "b","c","d")
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(myList);

        List<List<String>> list = Arrays.asList(
                Arrays.asList("a","c"),
                Arrays.asList("b","d"));
        System.out.println(list);

        System.out.println(list
                .stream()
                .flatMap(List::stream)//.flatMap(Collection::stream)
                .collect(Collectors.toList()));

        
        System.out.println(list
                .stream()
                .flatMap(lijst -> lijst.stream())
                .map(String::toUpperCase)
                .collect(Collectors.toList()));
    }
}
