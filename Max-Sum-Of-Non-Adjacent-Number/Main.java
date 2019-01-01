import java.util.*;

public class Main {

    static int findMaxNonAdiacent(ArrayList<Integer> Array) {

        //Max sum with last number is the maximum sum we found without considering Array[current index].
        int maxSumWithLastNumber = 0;
        //Max sum without last number is the maximum sum we found BEFORE the max sum with last number.
        int maxSumWithoutLastNumber = 0;

        for(int i=0; i<Array.size(); i++) {

            //choose if the last sum is still the max,
            // or we found a greater number: (current Array[i] + last sum without last number)
            int total = Math.max(maxSumWithLastNumber, (Array.get(i) + maxSumWithoutLastNumber));

            //update the old sum: is now the new sum
            maxSumWithoutLastNumber = maxSumWithLastNumber;
            //new sum is the result of the max operation.
            maxSumWithLastNumber = total;

        }

        //return the max non adjacent sum.
        return maxSumWithLastNumber;

    }

    public static void main(String[] args) {

        //Creating a random number generator and an array
        Random rnGen = new Random();
        ArrayList<Integer> myArray = new ArrayList<>(10);

        //fill the array with 10 casual number
        for(int i=0; i<10; i++) {
            myArray.add(rnGen.nextInt(15));
        }

        /* test case
        myArray.add(5);
        myArray.add(1);
        myArray.add(1);
        myArray.add(5);
        */

        //Print Phase
        System.out.println("Array created: " + myArray);
        System.out.println("Max non-adjacent sum: " + findMaxNonAdiacent(myArray));

    }
}
