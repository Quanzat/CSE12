
/**
 * Name: Quan Tran and Marina Xu
 * ID: A16191839 and A16235362
 * Email: qutran@ucsd.edu, max003@ucsd.edu
 * Sources used: Lecture and Zybook

 * This file contain all the custom test to account for the edge cases
 * that the public test for MyLinkedList did not test. It mainly tests
 * the Iterator class and the method within it. 
 */

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.*;

/**
 * This class has many testes for each of the method created in
 * MylinkedList.java, specifically, the method within the protected class
 * MyListIterator.
 */
public class MyLinkedListCustomTester {
    private MyLinkedList emptyList, listLen4;
    private MyLinkedList.MyListIterator listEmptyListIter, listLen4Iter;

    /**
     * This sets up the test fixture. JUnit invokes this method before every
     * testXXX method. The @Before tag tells JUnit to run this method before
     * each test.
     */
    @Before
    public void setUp() throws Exception {
        emptyList = new MyLinkedList();
        listEmptyListIter = emptyList.new MyListIterator();
        listLen4 = new MyLinkedList();
        listLen4.add("one");
        listLen4.add("two");
        listLen4.add("three");
        listLen4.add("four");
        listLen4Iter = listLen4.new MyListIterator();
    }

    /**
     * Test the hasNext method when it is called on an empty list or at the head
     * or at the end
     */
    @Test
    public void testHasNext() {
        assertFalse("An empty list does not have a next node",
                listEmptyListIter.hasNext());
        listLen4Iter.left = listLen4.head;
        listLen4Iter.right = listLen4.head.getNext();
        listLen4Iter.idx = 0;
        assertTrue("Iterator at head does have next node",
                listLen4Iter.hasNext());
        listLen4Iter.left = listLen4.tail.getPrev();
        listLen4Iter.right = listLen4.tail;
        listLen4Iter.idx = 3;
        assertFalse("Iterator at tail does not have next node",
                listLen4Iter.hasNext());
    }

    /**
     * Test the next method when it is called on an empty list or when the
     * iterator is at the last index
     */
    @Test
    public void testNext() {
        boolean ifCaught = false;
        try {
            listEmptyListIter.next();
        } catch (NoSuchElementException e) {
            ifCaught = true;
        }
        assertEquals("check if exception was caught", true, ifCaught);
        listLen4Iter.left = listLen4.tail.getPrev();
        listLen4Iter.right = listLen4.tail;
        listLen4Iter.idx = 3;
        listLen4Iter.canRemoveOrSet = false;
        listLen4Iter.forward = true;
        try {
            listLen4Iter.next();
        } catch (NoSuchElementException e) {
            ifCaught = false;
        }
        assertEquals("check if exception was caught", false, ifCaught);

    }

    /**
     * Test the hasPrevious method when it is called on an empty list or when it
     * is called at the head or the end of the list
     */
    @Test
    public void testHasPrevious() {
        assertFalse("An empty list does not have a previous node",
                listEmptyListIter.hasPrevious());
        listLen4Iter.left = listLen4.tail.getPrev();
        listLen4Iter.right = listLen4.tail;
        listLen4Iter.idx = 3;
        assertTrue("Iterator at tail does have previous node",
                listLen4Iter.hasPrevious());
        listLen4Iter.left = listLen4.head;
        listLen4Iter.right = listLen4.head.getNext();
        listLen4Iter.idx = 0;
        assertFalse("Iterator at head does not have previous node",
                listLen4Iter.hasPrevious());

    }

    /**
     * Test the previous method when it is called on an empty list or at the
     * head
     */
    @Test
    public void testPrevious() {
        boolean ifCaught = false;
        try {
            listEmptyListIter.previous();
        } catch (NoSuchElementException e) {
            ifCaught = true;
        }
        assertEquals("check if exception was caught", true, ifCaught);
        listLen4Iter.left = listLen4.head;
        listLen4Iter.right = listLen4.head.getNext();
        listLen4Iter.idx = 0;
        listLen4Iter.canRemoveOrSet = false;
        listLen4Iter.forward = false;
        try {
            listLen4Iter.previous();
        } catch (NoSuchElementException e) {
            ifCaught = false;
        }
        assertEquals("check if exception was caught", false, ifCaught);
    }

    /**
     * Test the nextIndex method when idx is equal to size and tests nextIndex
     * method in the middle of the list
     */
    @Test
    public void testNextIndex() {
        listLen4Iter.left = listLen4.head.getNext().getNext();
        listLen4Iter.right = listLen4.head.getNext().getNext().getNext();
        listLen4Iter.idx = 2;
        listLen4Iter.forward = true;
        listLen4Iter.canRemoveOrSet = true;
        assertEquals(2, listLen4Iter.nextIndex());
    }

    /**
     * Test the previousIndex method in the middle of the list
     */
    @Test
    public void testPreviousIndex() {
        listLen4Iter.left = listLen4.head.getNext().getNext();
        listLen4Iter.right = listLen4.head.getNext().getNext().getNext();
        listLen4Iter.idx = 2;
        listLen4Iter.forward = true;
        listLen4Iter.canRemoveOrSet = true;
        assertEquals(1, listLen4Iter.previousIndex());
    }

    /**
     * Test the set method when a null element is passed in or when
     * canRemoveOrSet is set to false or when you are setting in the middle
     */
    @Test
    public void testSet() {
        boolean ifCaught = false;
        try {
            listLen4Iter.set(null);
        } catch (NullPointerException e) {
            ifCaught = true;
        }
        assertEquals("exception was caught", true, ifCaught);
        try {
            listLen4Iter.left = listLen4.head.getNext().getNext();
            listLen4Iter.right = listLen4.head.getNext().getNext().getNext();
            listLen4Iter.idx = 2;
            listLen4Iter.forward = true;
            listLen4Iter.canRemoveOrSet = false;
            listLen4Iter.set("Changed?");
        } catch (IllegalArgumentException e) {
            ifCaught = false;
        }
        assertEquals("check if exception was caught", false, ifCaught);

        listLen4Iter.left = listLen4.head.getNext().getNext();
        listLen4Iter.right = listLen4.head.getNext().getNext().getNext();
        listLen4Iter.idx = 2;
        listLen4Iter.forward = true;
        listLen4Iter.canRemoveOrSet = true;
        listLen4Iter.set("Changed?");
        assertEquals("check if left is changed", "Changed?",
                listLen4Iter.left.getElement());
        assertEquals("check if right is unchanged", "three",
                listLen4Iter.right.getElement());
    }

    /**
     * Test the remove method when canRemoveOrSet is false
     */
    @Test
    public void testRemoveTestOne() {
        boolean ifCaught = false;
        try {
            listLen4Iter.left = listLen4.head.getNext().getNext();
            listLen4Iter.right = listLen4.head.getNext().getNext().getNext();
            listLen4Iter.idx = 2;
            listLen4Iter.forward = true;
            listLen4Iter.canRemoveOrSet = false;
            listLen4Iter.remove();
        } catch (IllegalArgumentException e) {
            ifCaught = true;
        }
        assertEquals("check if exceptiion was caught", true, ifCaught);

    }

    /**
     * Test the remove method when calling remove when forward multiple times
     */
    @Test
    public void testRemoveTestTwo() {
        listLen4Iter.left = listLen4.head.getNext();
        listLen4Iter.right = listLen4.head.getNext().getNext();
        listLen4Iter.idx = 1;
        listLen4Iter.forward = true;
        listLen4Iter.canRemoveOrSet = true;
        listLen4Iter.remove();
        assertEquals("Valid remove when forward", null,
                listLen4Iter.left.getElement());
        assertEquals("Valid remove when forward, shouldn't change", "two",
                listLen4Iter.right.getElement());
        assertEquals("Index should decrement after removal", 0,
                listLen4Iter.idx);
        assertFalse("Prevent another remove or set",
                listLen4Iter.canRemoveOrSet);

        listLen4Iter.left = listLen4.head.getNext();
        listLen4Iter.right = listLen4.head.getNext().getNext();
        listLen4Iter.idx = 1;
        listLen4Iter.forward = true;
        listLen4Iter.canRemoveOrSet = true;
        listLen4Iter.remove();
        assertEquals("Valid remove when forward", null,
                listLen4Iter.left.getElement());
        assertEquals("Valid remove when forward, shouldn't change", "three",
                listLen4Iter.right.getElement());
        assertEquals("Index should decrement after removal", 0,
                listLen4Iter.idx);
        assertFalse("Prevent another remove or set",
                listLen4Iter.canRemoveOrSet);

        listLen4Iter.left = listLen4.head.getNext();
        listLen4Iter.right = listLen4.head.getNext().getNext();
        listLen4Iter.idx = 1;
        listLen4Iter.forward = true;
        listLen4Iter.canRemoveOrSet = true;
        listLen4Iter.remove();
        assertEquals("Valid remove when forward", null,
                listLen4Iter.left.getElement());
        assertEquals("Valid remove when forward, shouldn't change", "four",
                listLen4Iter.right.getElement());
        assertEquals("Index should decrement after removal", 0,
                listLen4Iter.idx);
        assertFalse("Prevent another remove or set",
                listLen4Iter.canRemoveOrSet);
    }

    /**
     * Test the add method when adding an invalid element or when adding to an
     * empty list
     * 
     */
    @Test
    public void testAdd() {
        boolean ifCaught = false;
        try {
            listLen4Iter.add(null);
        } catch (NullPointerException e) {
            ifCaught = true;
        }
        assertEquals("check if exception was caught", true, ifCaught);

        listEmptyListIter.add("hello");
        assertEquals("left is pointing to new element", "hello",
                listEmptyListIter.left.getElement());
        assertEquals("right is pointing to tail", null,
                listEmptyListIter.right.getElement());
        assertEquals("new element's next pointers are set", null,
                listEmptyListIter.left.getNext().getNext());
        assertEquals("new element's prev pointers are set", null,
                listEmptyListIter.right.getPrev().getPrev().getPrev());
        assertEquals("index is changed", 1, listEmptyListIter.idx);
        assertFalse("cannot remove immediately after",
                listEmptyListIter.canRemoveOrSet);
        assertEquals("new element is returned after calling previous", "hello",
                listEmptyListIter.previous());
    }

}