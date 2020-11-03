import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics_UpperEnLowerBound {

    public static void main(String[] args) {
        new Generics_UpperEnLowerBound().run();
    }

    private void run() {
        System.out.println("Upper en Lower Bound");
        //Upperbound nodig bij LEZEN/ACCESS tot objecten
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println(sumOfList(list));
        //Lowerbround nodig SCHRIJVEN
        System.out.println(list);
        addNumbers(list);
        System.out.println(list);
    }

    private void addNumbers(List<? super Integer> list) {
        list.add(4);
        list.add(5);
    }

    public double sumOfList(List<? extends Number> list){
        double s = 0;
        for (Number n: list){
            s += n.doubleValue();
        }
        return s;
    }
}
