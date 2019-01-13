public class Main {

    public static String longestKSubstring(String s, int k) {

        //Declaring strings to hold longest substring found and current string being analyzed
        String longest = "";
        String current = "";

        //Char count to keep track of occurrence
        int charCount = 0;

        //For the length of the string
        for(int i=0; i<s.length(); i++) {

            //The program will enter in the control cycle if two conditions are met:
            //1)The characters count found into the current string are lesser than the K passed:
            //  this means we have more room to insert another character to met the condition where
            //  the substring must have maximum K different character. Add s[i] (string passed to the function)
            //  to the current string and increase the counter of the found characters.
            //2)The character at s[i] (string passed to the function) is contained into the current
            //  analyzed string. That means that there is another occurrence of a character already met,
            //  and then we must add it to the current string without incrementing the character count:
            //  this because a character already present into the current strings met the condition of
            //  building the K character substring.

            if (charCount < k || (current.contains(Character.toString(s.charAt(i))))) {
                if(!current.contains(Character.toString(s.charAt(i)))) {
                    current += s.charAt(i);
                    charCount++;
                } else {
                    current += s.charAt(i);
                }
            } else {

                //Otherwise, the conditions above are not met, and we must consider the current string
                //as the new substring if two conditions happens: if the current length is bigger than the
                //longest length, and if the character count is lesser than k.
                //If met, assign to longest the current string.
                if(current.length() > longest.length() && charCount <= k) {
                    longest = current;
                }

                //Now, we need to find the window of last optimal character for our substring.
                //The current string contains for sure at least K-1 character for the next substring
                //of K lenght that will be analyzed. We then iterate from the lenght of the current string
                //to 0, searching which are the characters that met the condition of K-1 different character.
                //e.g: aabacb -> this string contains K different character: iterating starting from the end
                //of the string, we first found b then c then break because the optimalCount reached K-1 character.
                //The K-th character will be added later (s[i])
                String lastOptimal = "";
                int optimalCount = 0;

                for(int j=current.length()-1; j>=0; j--) {
                    if(optimalCount < k-1 || lastOptimal.contains(Character.toString(current.charAt(j)))) {
                        if(!lastOptimal.contains(Character.toString(current.charAt(j)))) {
                            lastOptimal += current.charAt(j);
                            optimalCount++;
                        } else {
                            lastOptimal += current.charAt(j);
                        }
                    } else {
                        break;
                    }
                }

                //last optimal is then equal to it's reverse
                lastOptimal = new StringBuilder(lastOptimal).reverse().toString();

                //Now current is equal to the last optimal string plus the character at s[i], because we're analyzing
                //a new substring composed of the last optimal substring plus the new character.
                current = lastOptimal + Character.toString(s.charAt(i));


                //char count is now equal to optimal count + 1, because we found optimal count character in the
                //for iteration to found the last optimal substring, + 1 because we added s[i].
                charCount = optimalCount + 1;

            }
        }

        //Last check: sometimes, the for will exit without analyzing the current string with the longest,
        //because the current substring was the optimal til the end. So we need to check that again to make
        //sure that the longest is still the longest, and of course, if the char count is lesser than K to respect
        //the constriction.
        if(current.length() > longest.length() && charCount <= k) {
            longest = current;
        }

        return longest;

    }

    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k = 2;

        System.out.println("Longest substring with " + k + " character of " + s + " is:");
        s = longestKSubstring(s, k);
        System.out.println(longestKSubstring(s, k) + " of size " + s.length() + ".");
    }
}
