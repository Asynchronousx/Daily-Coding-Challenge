import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedList<T> {
    @Attributes
    public Integer size = 0;
    private NodeInfo root = null;
    //We're maintaining an hash table which will hold, at every inserted node,
    //a value mapped to it's string representation to help us find the intersection
    //of two lists.
    private Map<String, T> hashSet = new HashMap<>();

    @Constructors
    public LinkedList() {}
    public LinkedList(NodeInfo<T> root) {
        this.root = root;
        this.size = root != null? 1: 0;
    }

    @AccessModifiers("Get")
    public NodeInfo<T> getRoot() { return root; }
    public Map<String, T> getHashSet() { return hashSet; }

    @AccessModifiers("Set")
    public void setRoot(NodeInfo<T> root) { this.root = root; }

    @Methods
    public void add(T value) {

        //Defining new node
        NodeInfo<T> newNode = new NodeInfo<>(value);

        //Base case: List's empty
        if(getRoot() == null) {
            //Set new root, add the hash value to the map and increase node count size
            setRoot(newNode);
            getHashSet().put(value.toString(), value);
            this.size++;
        } else { //Not empty
            //Referencing the root to traverse list
            NodeInfo<T> refNode = getRoot();
            while(refNode.getNext() != null) {
                refNode = refNode.getNext();
            }

            //Spot found: add the node, the hash value and increment size
            refNode.setNext(newNode);
            getHashSet().put(value.toString(), value);
            this.size++;
        }
    }

    public List<NodeInfo<T>> getIntersectionWith(LinkedList<T> secondList) {
        //Declaring an empty node list
        List<NodeInfo<T>> intersectionNodes = new ArrayList<>();

        //checking who's list is bigger
        Integer biggerSize = Integer.max(this.size, secondList.size);

        //fetching the root of the list that is gonna be iterated
        NodeInfo<T> refNode = biggerSize.equals(this.size)? this.getRoot(): secondList.getRoot();

        //while there is something to iterate
        while(refNode != null) {
            //check on who we're iterating: this (the first list) or the second list.
            //If iterating on this, we should check if the other list contains the value
            //currently analized. Otherwise, if iterating on secondList we should check this.
            //Once choosed which one, fetch the hashSet with the getter function,
            //and check if it contains the key referred to the node currently analyzed:
            //The key is represented by the String value of the number (eg: 1 -> "1"),
            //so it's easy to know with the method containsKey, where the key passed is the
            //value T toString. If the other list contains the current list value mapped in it's
            //Hash table, so it's an intersection: add it to the array of nodes to return, then
            //assign the next to iterate trough the list.
            if(((biggerSize.equals(this.size)? secondList: this)
                    .getHashSet()).containsKey((refNode.getInfo()).toString())) {
                intersectionNodes.add(refNode);
            }
            refNode = refNode.getNext();
        }

        //return all the nodes intersected
        return intersectionNodes;

    }

    public void print() {
        NodeInfo<T> refNode = this.getRoot();
        System.out.print("[");
        while(refNode != null) {
            System.out.print(refNode.getNext() == null?
                    " " + refNode.getInfo():
                    " " + refNode.getInfo() + ",");
            refNode = refNode.getNext();
        } System.out.println(" ]");
    }


}

class NodeInfo<T> {
    @Attributes
    private T info;
    private NodeInfo next;

    @Constructors
    public NodeInfo(T info) {
        this.info = info;
        this.next = null;
    }

    @AccessModifiers("Get")
    public T getInfo() { return info; }
    public NodeInfo<T> getNext() { return next; }

    @AccessModifiers("Set")
    public void setNext(NodeInfo next) { this.next = next; }
    public void setInfo(T info) { this.info = info; }

}


