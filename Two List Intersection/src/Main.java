import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Note: LinkedList could hold <String>, <Float>, <Object>
        //as well, because the implementation is parametrized.
        LinkedList<Integer> firstList = new LinkedList<>();
        LinkedList<Integer> secondList = new LinkedList<>();

        //Adding element to first list
        firstList.add(3);
        firstList.add(7);
        firstList.add(8);
        firstList.add(10);
        firstList.add(2);
        //List is: [ 3, 7, 8, 10, 2 ]

        //Adding element to second list
        secondList.add(99);
        secondList.add(1);
        secondList.add(8);
        secondList.add(10);
        secondList.add(54);

        //List is: [ 99, 1, 8, 10, 54]

        //Intersection is: [ 8, 10, 54 ]

        //Print first list
        System.out.print("First list is: ");
        firstList.print();

        //Print second list
        System.out.print("Second list is: ");
        secondList.print();

        //Print and format intersections
        System.out.print("Intersection is: ");
        System.out.print("[ ");
        (firstList.getIntersectionWith(secondList)).forEach((n) -> System.out.print(n.getInfo() + " "));
        System.out.print("] ");

    }
}
