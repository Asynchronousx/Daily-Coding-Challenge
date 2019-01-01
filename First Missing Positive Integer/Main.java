import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {

    static Integer minPositiveMissing(List<Integer> a) {

        //Sorting the array in ascending for the greedy approach,
        //and declaring an iterator to analyze the list.
        Collections.sort(a);
        Iterator<Integer> it = a.iterator();

        //At the beginning, the value is INFINITY to let us change it
        //with the first occurrence of a smaller number.
        Integer minMissingInt = Integer.MAX_VALUE;

        //while the array is not depleted (time complexity: O(N))
        while(it.hasNext()) {
            //fetch the current number
            Integer currentNum = it.next();
            //if the current element is lesser than the missingInt (at the beginning infinity)
            //then change it to the current. This will assume that, when the array is sorted,
            //the left-most number + 1 (+ 1 because the current one is PRESENT into the array
            //and not missing) will be locally (greedy choice) because we'll know if the
            //number exists into the next iteration. If the number will be replaced than it means
            //that the left-most number + 1 exists and then MAYBE the actual-number + 1 is the missing
            //one.
            if(currentNum <= minMissingInt && currentNum >= 0) {
                minMissingInt = currentNum+1;
            }
        }

        //Return the missing positive number
        return minMissingInt;

    }

    public static void main(String[] args) {

        List<Integer> array = new ArrayList<>();
        array.add(3);
        array.add(4);
        array.add(-1);
        array.add(1);

        System.out.println(minPositiveMissing(array));
    }
}
