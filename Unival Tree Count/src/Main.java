public class Main {
    public static void main(String[] args) {
        UnivalTree<Integer> univalTree = new UnivalTree<>();
        univalTree.initRoot(0);
        univalTree.insertLeft(1);
        univalTree.insertLeft(1);
        univalTree.insertLeft(1);
        univalTree.insertRight(0);
        univalTree.insertRight(0);
        univalTree.insertRight(1);
        univalTree.insertRight(1);
        univalTree.insertRight(1);

        /* Tree is:
              0
             / \
        uni 1   0
               / \
          uni 0   1 uni
                 / \
            uni 1   1 uni

        The unival subtrees are: 5.
        */

        System.out.println("Number of Unival Tree: " + univalTree.countUnival());

    }
}
