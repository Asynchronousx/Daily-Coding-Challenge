import java.util.*;

public class Main {

    static boolean isSumOfKPresent(List<Integer> array, int k) {

        //The method to find if two element summed up generate K, will be
        //approached sorting the array and starting from the first
        //and the last index.
        //the sum of a[0] and a[N] can assume 3 valor: > k, == k and < k.
        //If the sum is greater, we need to decrease the N index to N-1, because
        //sorting the array guaranteed us that the element at N-1 is always lesser.
        //If the sum is lesser that means that we need to increase the value to N+1,
        //because sorting the array guaranteed us that the element at N+1 is always bigger.
        //If the sum is equal, than return true.

        //sort the array
        Collections.sort(array);

        //to store the sum
        int result = 0;

        //while i<j (index of i and j are not equal between them)
        for(int i=0, j=(array.size()-1); i<j;) {
            //assign the result
            result = array.get(i) + array.get(j);

            //result is bigger
            if(result > k) {
                //reduce the bigger index (j)
                j--;
            }
            //result is lesser
            else if(result < k) {
                //increase the smaller index (i)
                i++;
            }
            //equal
            else {
                return true;
            }
        }

        //if here, sum not present
        return false;

    }


    public static void main(String[] args) {

        //creating the array list
        List<Integer> array = new ArrayList<>();

        //defining the sum key
        int k = 10;

        //adding element
        array.add(10);
        array.add(15);
        array.add(3);
        array.add(7);

        System.out.println("There are two number that summed forms " + k + "? " + isSumOfKPresent(array,k));

    }
}
