import java.util.LinkedHashMap;
import java.util.Map;

public class LRU {
    private int cacheSize;
    private Map<Integer, Integer> pageMap;

    public LRU(int cacheSize) {
        this.cacheSize = cacheSize;
        this.pageMap = new LinkedHashMap<>(cacheSize);
    }

    public void set(Integer key, Integer value) {
        //If a page is being referenced again
        if(pageMap.containsKey(key)) {
            //Put the page into the MRU section (most-right)
            pageMap.remove(key);
            pageMap.put(key, value);
        } else {
            //else, check the cache size:
            //case when the cache is not full yet
            if (pageMap.size() < cacheSize) {
                pageMap.put(key, value);
            }
            //else, there is a page fault: remove the LRU page and insert the new one.
            else {
                Map.Entry<Integer, Integer> entry = pageMap.entrySet().iterator().next();
                pageMap.remove(entry.getKey());
                pageMap.put(key, value);
            }
        }

        displayMap();

    }

    //get a specific page/instant
    public Node<Integer> get(Integer key) {
        //if the map contains the key
        if (pageMap.containsKey(key)) {
            //return a new pagenode
            return new Node<>(key, pageMap.get(key));
        } else {
            //else null
            return null;
        }
    }

    public void displayMap() {
        System.out.println(this.pageMap);
    }

}

//Class useful to represent a page node, composed by the page itself and the last time instant referred.
class Node<T> {
    public T key;
    public T value;

    public Node(T key, T value) {
        this.key = key;
        this.value = value;
    }
}
