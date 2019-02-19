public class Main {
    public static void main(String[] args) {
        LRU lru = new LRU(4);

        //Here, key represent the value of the page (i.e: page number), while
        //the value represent the time instant in which that specific key page was
        //referred.
        lru.set(1,0);
        lru.set(3,1);
        lru.set(4,2);
        lru.set(5,3);
        lru.set(1,4);
        lru.set(7,5);
        lru.set(1,6);
        lru.set(3,7);
        lru.set(4,8);
        lru.set(9,9);

        Node<Integer> pageEntry = lru.get(4);

        if(pageEntry != null) {
            System.out.println("Page " + pageEntry.key + " was referenced last time at instant " + pageEntry.value + ".");
        } else {
            System.out.println("No page found.");
        }

    }
}
