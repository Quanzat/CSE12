import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Name: Quan Tran 
 * ID: A16191839               
 * Email: qutran@ucsd.edu              
 * Sources used: Lecture      
 * 
 * The purpose of this file is to test the methods written in MyBST.java. These 
 * tests consider test cases that did not get covered in the public tester.
 */

/**
 * This class is a custom tester class that test methods written in other
 * classes to check its functionalities.
 */
public class CustomTester {
    MyBST<Integer, Integer> completeTree;
    MyBST<Integer, Integer> emptyTree;

    /**
     * The setup method creates a complete tree with height 3 The tree has
     * following structure and will be used as testing purpose 6 / \ 3 9 / | / \
     * 1 4 7 10
     * 
     *
     */

    @SuppressWarnings("unchecked") @Before
    public void setup() {

        // create complete tree
        MyBST.MyBSTNode<Integer, Integer> root = new MyBST.MyBSTNode(6, 1,
                null);
        MyBST.MyBSTNode<Integer, Integer> three = new MyBST.MyBSTNode(3, 1,
                root);
        MyBST.MyBSTNode<Integer, Integer> nine = new MyBST.MyBSTNode(9, 1,
                root);
        MyBST.MyBSTNode<Integer, Integer> one = new MyBST.MyBSTNode(1, 2,
                three);
        MyBST.MyBSTNode<Integer, Integer> four = new MyBST.MyBSTNode(4, 30,
                three);
        MyBST.MyBSTNode<Integer, Integer> seven = new MyBST.MyBSTNode(7, 50,
                nine);
        MyBST.MyBSTNode<Integer, Integer> ten = new MyBST.MyBSTNode(10, 70,
                nine);

        this.completeTree = new MyBST<>();
        this.completeTree.root = root;
        root.setLeft(three);
        root.setRight(nine);
        three.setLeft(one);
        three.setRight(four);
        nine.setLeft(seven);
        nine.setRight(ten);
        this.completeTree.size = 7;

        // create empty tree
        this.emptyTree = new MyBST();
        this.emptyTree.root = null;
        this.emptyTree.size = 0;
    }

    // ====== MyBSTNode class ======

    // Test predecessor() on a non-leaf node that return null
    @Test
    public void testNodePredecessorNonLeafNode() {
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root;
        assertSame(null, root.getLeft().getLeft().predecessor());

    }

    // Test predecessor() on a non-leaf node
    @Test
    public void testNodePredecessorLeafNode() {
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root;
        assertSame(root.getRight().getLeft(), root.getRight().predecessor());
    }

    // ====== MyBST class ======

    // Test insert method on existing key
    @Test
    public void testInsert() {
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root;
        assertEquals((Integer) 50, completeTree.insert(7, 60));
        assertEquals((Integer) 7, root.getRight().getLeft().getKey());
        assertEquals((Integer) 60, root.getRight().getLeft().getValue());
    }

    // Test insert method with a null key
    @Test
    public void testInsertNull() {
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root;
        try {
            completeTree.insert(null, 3);

        } catch (NullPointerException e) {
            // Test Passed
        }
    }

    // Test insert method on an empty tree
    @Test
    public void testInsertEmpty() {
        emptyTree.insert(1, 3);
        assertEquals((Integer) 1, emptyTree.root.getKey());
    }

    // Test search method on a null key
    @Test
    public void testSearchNull() {
        assertEquals(null, completeTree.search(null));
    }

    // Test remove method when key is null
    @Test
    public void testRemove() {
        assertNull(completeTree.remove(null));
    }

    // Test remove method when key is root with full tree
    @Test
    public void testRemoveRoot() {
        completeTree.remove(6);

        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> returnSolution = new ArrayList<>();
        returnSolution.add(root.getLeft().getLeft());
        returnSolution.add(root.getLeft());
        returnSolution.add(root.getLeft().getRight());
        returnSolution.add(root);
        returnSolution.add(root.getRight());
        returnSolution.add(root.getRight().getRight());
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> actualSolution = completeTree
                .inorder();

        for (int i = 0; i < returnSolution.size(); i++) {
            assertSame(returnSolution.get(i), actualSolution.get(i));
        }
    }

    // Test remove method when key is root with one node tree
    @Test
    public void testRemoveOneNodeTree() {
        emptyTree.insert(1, 1);
        assertEquals(1, emptyTree.size);

        emptyTree.remove(1);
        assertEquals(0, emptyTree.size);
        assertNull(emptyTree.root);

    }

    // Test remove method when key is not in tree
    @Test
    public void testRemoveNotInTree() {
        assertNull(completeTree.remove(26));
    }
}
