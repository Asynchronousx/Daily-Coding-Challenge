public class UnivalTree<T extends Comparable<T>> {
    @Attributes
    private NodeInfo<T> root;

    @Constructors
    public UnivalTree(T value) { this.root = new NodeInfo<T>(value); }
    public UnivalTree() { this.root = null; }

    @AccessModifiers
    public NodeInfo getRoot() { return this.root; }
    public void setRoot(NodeInfo root) { this.root = root; }

    @HelperMethod
    public void insertRight(T value) {
        if(getRoot().getRight() == null) {
            getRoot().setRight(new NodeInfo<T>(value));
        }
        else {
            recursiveRight(getRoot().getRight(), new NodeInfo<T>(value));
        }
    }

    @HelperMethod
    public void insertLeft(T value) {
        if(getRoot().getLeft() == null) {
            getRoot().setLeft(new NodeInfo<T>(value));
        }
        else {
            recursiveLeft(getRoot().getLeft(), new NodeInfo<T>(value));
        }
    }

    @Methods(value = "Node manipulation")
    public void initRoot(T value) {
        if(getRoot() == null) {
            setRoot(new NodeInfo<T>(value));
        } else {
            System.out.println("Root already initialized.");
        }
    }

    private void recursiveRight(NodeInfo<T> root, NodeInfo<T> nodeToInsert) {
        if(root.getLeft() == null) {
            root.setLeft(nodeToInsert);
        }
        else if (root.getRight() == null) {
            root.setRight(nodeToInsert);
        }
        else {
            recursiveRight(root.getRight(), nodeToInsert);
        }
    }

    private void recursiveLeft(NodeInfo<T> root, NodeInfo<T> nodeToInsert) {
        if(root.getLeft() == null) {
            root.setLeft(nodeToInsert);
        }
        else if (root.getRight() == null) {
            root.setRight(nodeToInsert);
        }
        else {
            recursiveLeft(root.getLeft(), nodeToInsert);
        }
    }

    public void printTree() {
        //In-order printing.
        System.out.print("In order print: [ ");
        inOrderPrint(this.getRoot());
        System.out.println("]");
    }

    private void inOrderPrint(NodeInfo root) {

        //if the node is null, nothing to see here
        if(root == null) {
            return;
        }

        //traverse
        inOrderPrint(root.getLeft());
        System.out.print(root.getValue() + " ");
        inOrderPrint(root.getRight());
    }

    @Methods(value = "Unival Count")
    public int countUnival() {
        return countRecursive(this.getRoot());
    }

    private int countRecursive(NodeInfo root) {
        //if root is null, return 0 
        if(root == null) {
            return 0;
        }
        //node is leaf? then is an unival single subtree.
        else if(root.getLeft() == null && root.getRight() == null) {
            return 1;
        }
        else {
            int c = 0;
            //if the root value is equal to both children, then it's an unival.
            if(root.getValue().compareTo(root.getRight().getValue()) == 0 &&
               root.getValue().compareTo(root.getLeft().getValue()) == 0) {
                c = 1;
            }
            
            //return the found unival count + the recursion in the left and right subtree.
            return c + countRecursive(root.getLeft()) + countRecursive(root.getRight());
        }
    }

}

class NodeInfo<T extends Comparable<T>> {
    @Attributes
    T value;
    NodeInfo left;
    NodeInfo right;

    @Constructors
    public NodeInfo(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    @AccessModifiers(value = "get")
    public T getValue() { return this.value; }
    public NodeInfo getLeft() { return left; }
    public NodeInfo getRight() { return right; }

    @AccessModifiers(value = "set")
    public void setValue(T value) { this.value = value; }
    public void setLeft(NodeInfo left) { this.left = left; }
    public void setRight(NodeInfo right) { this.right = right; }

}
