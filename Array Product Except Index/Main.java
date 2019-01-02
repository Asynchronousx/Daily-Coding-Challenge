import java.util.ArrayList;
import java.util.List;

public class Main {

    static int recursiveProduct(List<Integer> a, int currIndex, int lastProduct) {
        //base case: index is out of range
        if(currIndex == a.size()) {
            //return 1
            return 1;
        }
        else {
            //The problem is solved by using mirroring-recursion: we're starting from the last
            //index to solve the problem, because at the last index we'll know for sure the product
            //of the last N-1 number. The problem doesn't want to multiply it for i itself, so at the last
            //recursion call the variable lastProduct hold the optimal value for the last index.
            //We then return the element at current index multiplied by the result that the recursion
            //gave us: in the case of the last iteration is 1*a[i] (because when the size limit is reached, 
            //result returns 1), then going back and return result*a[N-1], result*A[N-2]... [N-N].
            int result = recursiveProduct(a, currIndex+1, lastProduct*a.get(currIndex));
            int tmp = a.get(currIndex);
            a.set(currIndex, result*lastProduct);
            return result*tmp;
        }
    }

    public static void main(String[] args) {

        //creating a list of integer and passing it to the function
        List<Integer> array = new ArrayList<>(5);
        array.add(0, new Integer(1));
        array.add(1, new Integer(2));
        array.add(2, new Integer(3));
        array.add(3, new Integer(4));
        array.add(4, new Integer(5));
        recursiveProduct(array, 0, 1);
        System.out.println(array);
    }
}
