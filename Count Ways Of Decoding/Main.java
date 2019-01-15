import java.util.ArrayList;
import java.util.List;

public class Main {

    static int countDecodeWays(String s) {

        //declaring variables
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        //A list to memorize the occurrence found: the size is s.size + 1 because in the
        //Last element of the list will be stored the solution to the problem.
        List<Integer> waysFound = new ArrayList<>(s.length()+1);

        //We're using a dynamic programming approach: so we need to have the base case
        //when the string is empty or contains just one element or the first element is 0.
        if(s.length() <= 1 || s.charAt(0) == '0') {
            return s.charAt(0) == '0'? 0: s.length() == 1? 1: 0;
        }

        //If here, the string contains more than 1 element: so it's safe to assume that the second element
        //(because the first could not be 0 or the string is nullified) could be 0.
        //That means that the first element (s[0]) can be already decoded into ONE way. so check the second out:
        waysFound.add(1);
        waysFound.add((Integer.parseInt(Character.toString(s.charAt(1))) > 0? 1: 0));

        //e.g: 102 -> in this case, waysFound[0] is 1, waysFound[1] is 0 because '10' can be translated only as
        //'10', 'j'. Entering into the for, means that the index should start at 2 to analyze the third element,
        //because the first two are already analyzed. that means that, with a DP approach, that say that the solution
        //to the problem is stored into the n-1 and n-2 subproblems (in this case '0' and '1') we can build the solution
        //on the found optimality: at i=2 we're analyzing the substring at s[i-2, i-1] ie '10'. if '10'.toint() is lesser
        //than alphabet.size, this means that we have waysFound[i-2, i-1] ways to decode the string. Translated, is:
        //waysFound[i] += (waysFound[i-2] + waysFound[i-1]) -> waysFound[i] = 1. We run the for TO the length of the string
        //(<=) and not only lesser because into the last element of waysFound we're storing the solution. So, at i=3,
        //now we have s[i-2] and s[i-1] equal to: '0' and '2'. Concatenated, this string is '02', and '02'.toint is 2.
        //So 2 < alphabet size, and we can add a new found solution to the ways found array: waysFound[i] = (waysFound[i-2] + waysFound[i-1])
        //that translated is: waysFound[3] = waysFound[1] + waysFound[2] -> waysFound[3] = 0 + 1 -> 1.
        //And that's true because 102 can be decoded only as 'jb'.

        for(int i=2; i<=s.length(); i++) {
            if(s.charAt(i-2) != '0') {
                if(Integer.parseInt(Character.toString(s.charAt(i-2)) + Character.toString(s.charAt(i-1))) < alphabet.length) {
                   waysFound.add(waysFound.get(i-2) + waysFound.get(i-1));
                } else {
                   waysFound.add(waysFound.get(i-1));
                }
            }
        }

        //return ways
        return waysFound.get(waysFound.size() < s.length()+1? s.length()-1: s.length());

    }

    public static void main(String[] args) {
        String s = "12321";
        System.out.println("There are " + countDecodeWays(s) + " ways to decode " + s + ".");
    }
}
