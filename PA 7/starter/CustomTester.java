
/**
 * Name: Quan Tran 
 * ID: A16191839               
 * Email: qutran@ucsd.edu              
 * Sources used: Lecture      
 * 
 * This file contains all the custom tests that was not considered in the public
 * test cases. It mainly tests the methods written in MyMinHeap.java.
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class tests various methods to manipulate the class called MyMinHeap.
 */
public class CustomTester {
    /**
     * Test the constructor when list is null and when list contain null object.
     */
    @Test
    public void testMyMinHeapConstructor() {
        ArrayList<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(new Integer[] { 6, null, 9, 4, 5, }));

        ArrayList<Integer> testList2 = new ArrayList<Integer>(Arrays.asList());

        try {
            MyMinHeap<Integer> heap = new MyMinHeap<>(testList);
            MyMinHeap<Integer> heap2 = new MyMinHeap<>(testList2);
            heap.data = testList;
            heap2.data = testList2;
        } catch (NullPointerException e) {
            // Test passed!
        }
    }

    /**
     * Test the getMinChildIdx method when both child are equals; right child is
     * smaller; left child is smaller; leaf child; and no child exist
     */
    @Test
    public void testGetMinChildIdx() {
        ArrayList<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(new Integer[] { 1, 3, 3, 5, 4, 6, 7, 8, 9, 6 }));
        MyMinHeap<Integer> heap = new MyMinHeap<>();

        heap.data = testList;

        // Both child are equal
        assertEquals(1, heap.getMinChildIdx(0));

        // Right node is smaller
        assertEquals(4, heap.getMinChildIdx(1));

        // Left node is smaller
        assertEquals(7, heap.getMinChildIdx(3));

        // Leaf node
        assertEquals(9, heap.getMinChildIdx(4));

        // No child node
        assertEquals(-1, heap.getMinChildIdx(5));

    }

    /**
     * Test the percolateUp method when index is the last node of the tree
     */
    @Test
    public void testPercolateUp() {
        ArrayList<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 }));
        MyMinHeap<Integer> heap = new MyMinHeap<Integer>();
        heap.data = testList;
        heap.percolateUp(8);

        Integer[] expected = { 1, 9, 7, 8, 5, 4, 3, 2, 6 };
        for (int i = 0; i < 9; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the percolateDown method when index is the last node of the tree
     */
    @Test
    public void testPercolateDown() {
        ArrayList<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 }));
        MyMinHeap<Integer> heap = new MyMinHeap<Integer>();
        heap.data = testList;
        heap.percolateDown(8);

        Integer[] expected = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        for (int i = 0; i < 9; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method when index need to be percolate up and when
     * index need to be percolate down.
     */
    @Test
    public void testDeleteIndex() {
        // Testing up
        ArrayList<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 }));
        MyMinHeap<Integer> heap = new MyMinHeap<Integer>();
        heap.data = testList;
        assertEquals(Integer.valueOf(6), heap.deleteIndex(3));

        Integer[] expected = { 1, 9, 7, 8, 5, 4, 3, 2 };

        for (int i = 0; i < 8; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }

        // Testing down
        ArrayList<Integer> testList2 = new ArrayList<Integer>(
                Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
        MyMinHeap<Integer> heap2 = new MyMinHeap<Integer>();
        heap2.data = testList2;
        assertEquals(Integer.valueOf(2), heap2.deleteIndex(1));

        Integer[] expected2 = { 1, 4, 3, 8, 5, 6, 7, 9 };

        for (int i = 0; i < 8; i++) {
            assertEquals(expected2[i], heap2.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method when deleting the last node.
     */
    @Test
    public void testDeleteIndex2() {
        ArrayList<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 }));
        MyMinHeap<Integer> heap = new MyMinHeap<Integer>();
        heap.data = testList;
        assertEquals(Integer.valueOf(1), heap.deleteIndex(8));

        Integer[] expected = { 9, 8, 7, 6, 5, 4, 3, 2 };

        for (int i = 0; i < 7; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the insert method when inserting an larger element than any element
     * currently in the list.
     */
    @Test
    public void testInsert() {
        ArrayList<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 }));
        MyMinHeap<Integer> heap = new MyMinHeap<Integer>();
        heap.data = testList;
        heap.insert(10);

        Integer[] expected = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 10 };

        for (int i = 0; i < 9; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the insert method when inserting null.
     */
    @Test
    public void testInsert2() {
        ArrayList<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 }));
        MyMinHeap<Integer> heap = new MyMinHeap<Integer>();
        heap.data = testList;

        try {
            heap.insert(null);
        } catch (Exception e) {
            // Test Paseed!
        }

        Integer[] expected = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        for (int i = 0; i < 9; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }
    }

    /**
     * Test remove when list is empty.
     */
    @Test
    public void testRemove() {
        ArrayList<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(new Integer[] {}));
        MyMinHeap<Integer> heap = new MyMinHeap<Integer>();
        heap.data = testList;
        assertEquals(null, heap.remove());
    }

    /**
     * Test getMin when list is empty.
     */
    @Test
    public void testGetMin() {
        ArrayList<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(new Integer[] {}));
        MyMinHeap<Integer> heap = new MyMinHeap<Integer>();
        heap.data = testList;
        assertEquals(null, heap.getMin());
    }
}