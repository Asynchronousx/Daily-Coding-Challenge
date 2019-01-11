public class Main {

    static int countDecodeWays(String s) {

        //declaring variables
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        
        //how many starts at 1: there is always at least one way to decode
        int howMany = 1;


        //for the lenght of the string increase i by two (we're analyzing couple of number)
        for(int i=0; i<s.length(); i+=2) {
            //if the current char is 0 or we've exceeded the lenght, we can't decode anything
            if(s.charAt(i) != '0' && i+1 < s.length()) {
                //translate the concatenated string to a number, and check if is less than the alphabets size
                if(Integer.parseInt(Character.toString(s.charAt(i)) + Character.toString(s.charAt(i+1))) < alphabet.length) {
                    //if the next char is 0, there is one way to decode: 10 or 20. so don't add anything.
                    //else, the couple (i, i+1) is a valid number and it can be translated as: i, i+i or (i + i+1).
                    howMany += s.charAt(i+1) == '0'? 0: 2;
                }
            }
        }

        //return ways
        return howMany;

    }

    public static void main(String[] args) {
        String s = "1234";
        System.out.print("To decode " + s + " we got " + countDecodeWays(s) + " ways.");
    }
}
