import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    @SafeVarargs
    public static <T extends Comparable<T>> CustomList<T> mergeList(CustomList<T>... lists) {
        CustomList<T> mergedList = new MyList<>();

        for(CustomList<T> list : lists) {
            mergedList.append(list);
        }

        mergedList.sort();
        return mergedList;
    }

    public static void main(String[] args) {
        CustomList<Integer> l1 = new MyList<>();
        CustomList<Integer> l2 = new MyList<>();
        CustomList<Integer> l3 = new MyList<>();
        CustomList<Integer> l4 = new MyList<>();

        //Adding element to the lists
        l1.add(4);
        l1.add(2);
        l1.add(5);
        l1.add(1);

        l2.add(4);
        l2.add(9);
        l2.add(1);
        l2.add(3);
        l2.add(4);

        l3.add(1);
        l3.add(12);
        l3.add(3);

        l4.add(8);
        l4.add(19);
        l4.add(1);
        l4.add(43);
        l4.add(88);
        l4.add(2);

        //Sorting the list
        l1.sort();
        l2.sort();
        l3.sort();
        l4.sort();

        //printing the list
        l1.print();
        l2.print();
        l3.print();
        l4.print();

        //Retrieving the merged list:
        CustomList<Integer> mergedList = mergeList(l1, l2, l3, l4);
        System.out.println("Custom merged list: ");
        mergedList.print();


    }


}
