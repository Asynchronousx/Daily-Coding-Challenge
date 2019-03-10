public class Main {
    static Integer seriesMultiplier = 9;
    static Integer firstPerfectNumber = 19;

    public static Integer findNthPerfectNumber(Integer n) {
        return firstPerfectNumber + (9 * (n-1) + (n % 10 == 0? (seriesMultiplier*(n/10)) : 0));
    }

    public static void main(String[] args) {
        Integer whatNumberYouWouldKnow = 10;
        
        //Number printed is 109
        System.out.println("The " + whatNumberYouWouldKnow + "N-th Perfect Number is: " + findNthPerfectNumber(whatNumberYouWouldKnow));
    }

}
