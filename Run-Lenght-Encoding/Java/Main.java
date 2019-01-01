public class Main {

    public static String Encode(String s) {

        //initial index of the subsequence
        int initialSequenceIndex = 0;
        //empty string
        String encoded = "";

        //for the size of the string
        for(int i=0; i<s.length(); i++) {
            //if the char at the start of the sequence differs from the actual char
            if(s.charAt(initialSequenceIndex) != s.charAt(i)) {
                //add to the string the total number of the occurrence had (given by subtracting i to initialSequenceIndex
                //and add the character located at the previous index
                encoded += (Integer.toString((i-initialSequenceIndex)) + s.charAt(i-1));
                //new subsequence starts at i
                initialSequenceIndex = i;
            }
        }


        //before returning the encoded string, add the last occurrence, finding his counter
        //at the length of the string minus the last initial sequence found, and the letter
        return encoded += Integer.toString(s.length() - initialSequenceIndex) + s.charAt(s.length()-1);

    }

    public static String Decode(String s) {

        String decoded = "";
        //we use multiplier to know if there are more than 1-digit occurrence (i.e: 45A -> 45 times A,
        //we need to take track of 4 and 5.
        String multiplier = "";

        //for the length of the string
        for(int i=0; i<s.length(); i++) {
            //is the current char is a number
            if(Character.isDigit(s.charAt(i))) {
                //add the number to the multiplier parsing the int
                multiplier += Character.toString(s.charAt(i));

                //if the next char isn't a number (the occurrence number stops here)
                if(!Character.isDigit(s.charAt(i+1))) {
                    //for the lenght of the multiplier
                    for(int j=0; j<Integer.parseInt(multiplier); j++) {
                        //add the letter (i+1 because current i is the times we got that letter)
                        //to the decoded string multiplier time
                        decoded += Character.toString(s.charAt(i+1));
                    }
                    //reset multiplier for new use
                    multiplier = "";
                }
            }
        }

        //return the decoded string
        return decoded;

    }

    public static void main(String[] args) {

        //Passing a random string
        String s = "AAAABBBCCCDAVA";
        System.out.println(Encode(s));
        System.out.println(Decode(Encode(s)));

    }
}
