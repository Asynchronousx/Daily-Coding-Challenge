import java.util.Comparator;


interface CustomList<T extends Comparable<T>> {
    void add(T o);
    void append(CustomList<T> list);
    void sort();
    void print();
    Node<T> getHead();
    Node<T> getTail();
    T getFirst();
    T getLast();
}

public class MyList<T extends Comparable<T>> implements CustomList<T> {
    private Node<T> head;
    private Node<T> tail;

    //Get&Set
    public T getFirst() {
        return head.getValue();
    }

    public T getLast() {
        return tail.getValue();
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    //Functions
    @Override
    public void add(T o) {
        Node<T> newNode = new Node<>();

        if(head == null) {
            head = newNode;
            head.setValue(o);
            tail = head;
        } else {
            Node<T> tmp = head;
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            newNode.setValue(o);
            tmp.setNext(newNode);
            this.tail = tmp.getNext();
        }
    }

    @Override
    public void print() {
        Node<T> tmp = head;
        System.out.print("[");
        while(tmp != null) {
            if(tmp.getNext() != null) {
                System.out.print(tmp.getValue() + ", ");
            }
            else {
                System.out.print(tmp.getValue() + "");
            }
            tmp = tmp.getNext();

        }
        System.out.println("]");
    }

    @Override
    public void append(CustomList<T> list) {
        if(head == null) {
            this.head = list.getHead();
            this.tail = list.getTail();
        } else {
            this.tail.setNext(list.getHead());
            this.tail = list.getTail();
        }
    }

    @Override
    public void sort() {
        //Current is the next node
        Node<T> cur = this.getHead().getNext();
        //Left node is the node before current
        Node<T> left = this.getHead();
        //Min value is the node containing the minimum value (we assume that is always the heas)
        Node<T> minValue = this.getHead();
        //LastIndexValue is the last current value found: initially set as cur
        T lastIndexValue = cur.getValue();

        //Example: [4]->[2]->[1]->[5]
        //In this case, cur is [2], left is [4], minValue is [4] (if is really the min value it will not be swapped,
        //otherwise it will) and the last index value is cur: in the next iteration will be the last value before the left.

        //While there is something to analyze
        while(cur != null) {
            //if the left values is bigger than the right one
            if (left.getValue().compareTo(cur.getValue()) > 0) {
                //swap it
                swap(left, cur);
                //if the left value is lesser than the last value found (that means that we need to order it into the list)
                //proceed to found the right order, otherwise don't bother enter the cycle
                if(left.getValue().compareTo(lastIndexValue) < 0) {
                    //tmp is the min value node
                    Node<T> tmp = minValue;
                    //while tmp does not correspond to the current node
                    while (tmp != cur) {
                        //if tmp node's value is greater than the one in the left
                        if (tmp.getValue().compareTo(left.getValue()) > 0) {
                            //swap it
                            swap(tmp, left);
                        }
                        //else left tmp is not greater but we know that we need to perform at least a swap
                        //to order the list: go down into the list and memorize the last index values as the current
                        else {
                            lastIndexValue = tmp.getValue();
                            tmp = tmp.getNext();
                        }
                    }
                }
            }

            //advance cur and left to the right
            left = cur;
            cur = cur.getNext();

        }
    }

    public void swap(Node<T> x, Node<T> y) {
        T tmp = x.getValue();
        x.setValue(y.getValue());
        y.setValue(tmp);
    }

}

class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> next;

    //Get&Set
    public T getValue() {
        return value;
    }


    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

}

