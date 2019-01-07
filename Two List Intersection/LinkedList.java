import java.util.HashMap;
import java.util.Map;

public class IntersectionList {
    public static void main(String[] args) {
        //Note: LinkedList could hold <String>, <Float>, <Object>
        //as well, because the implementation is parametrized.
        LinkedList<Integer> firstList = new LinkedList<>();
        LinkedList<Integer> secondList = new LinkedList<>();

        //Adding element to first list
        firstList.add(1);
        firstList.add(5);
        firstList.add(2);
        firstList.add(7);
        firstList.add(4);
        //List is: [ 1, 5, 2, 7, 4 ]

        //Adding element to second list
        secondList.add(1);
        secondList.add(3);
        secondList.add(2);
        secondList.add(9);
        secondList.add(7);
        secondList.add(10);
        //List is: [ 1, 3, 2, 9, 7, 10 ]

        //Intersection is: [ 1, 2, 7 ]

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
