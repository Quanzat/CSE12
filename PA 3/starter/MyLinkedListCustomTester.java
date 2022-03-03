
/**
 * Name: Quan Tran
 * Email: qutran@ucsd.edu
 * Sources used: Lecture, Piazza, CSE 12 Discord
 * 
 * This is a hidden tester file for PA3. It contains test cases 
 * for the MyLinkedList.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class contain custom test to further test the methods written in
 * MyLinkedList.java
 * 
 * IMPORTANT: Do not change the method headers and points are awarded only if
 * your test cases cover cases that the public tester file does not take into
 * account.
 */
public class MyLinkedListCustomTester {
    private MyLinkedList<Integer> intEmptyList;
    private MyLinkedList<String> StrEmptyList;
    private MyLinkedList<String> filledStringList;
    private String[] listData = { "UCSD", "CSE", "Winter 2022", "PA3" };

    int i;

    /**
     * This sets up the test fixture. JUnit invokes this method before every
     * testXXX method. The @Before tag tells JUnit to run this method before
     * each test.
     */
    @Before
    public void setUp() throws Exception {
        intEmptyList = new MyLinkedList<Integer>();
        StrEmptyList = new MyLinkedList<>();
        filledStringList = new MyLinkedList<>();
    }

    private void populateStringList() {
        MyLinkedList<String>.Node node_0 = this.filledStringList.new Node(
                this.listData[0]);
        MyLinkedList<String>.Node node_1 = this.filledStringList.new Node(
                this.listData[1]);
        MyLinkedList<String>.Node node_2 = this.filledStringList.new Node(
                this.listData[2]);
        MyLinkedList<String>.Node node_3 = this.filledStringList.new Node(
                this.listData[3]);

        this.filledStringList.head.next = node_0;
        node_0.prev = this.filledStringList.head;
        node_0.next = node_1;
        node_1.prev = node_0;
        node_1.next = node_2;
        node_2.prev = node_1;
        node_2.next = node_3;
        node_3.prev = node_2;
        node_3.next = this.filledStringList.tail;
        this.filledStringList.tail.prev = node_3;
        this.filledStringList.size = 4;

    }

    /**
     * Stress testing by adding multiple elements. Also testing adding null.
     */
    @Test
    public void testAdd() {
        this.populateStringList();

        // Adding each element 10 times
        for (i = 0; i < 10; i++) {
            this.intEmptyList.add(i);
            this.filledStringList.add("Hi");
        }
        assertEquals("The size should be 10", Integer.valueOf(10),
                Integer.valueOf(this.intEmptyList.size));
        assertEquals("The size should be 14", 14, this.filledStringList.size);

        // Adding null
        try {
            this.intEmptyList.add(null);
            this.StrEmptyList.add(null);
        } catch (NullPointerException e) {
            // Exception is successfully caught. Passing the test case.
        }

    }

    /**
     * Test the add method when element is in the middle of the list
     */
    @Test
    public void testAddWithIndexTestOne() {
        this.populateStringList();

        MyLinkedList<String>.Node oldLastNode = this.filledStringList.tail.prev;
        this.filledStringList.add(3, "Gary Gillespie");

        assertEquals("Tail node should point back the new tail", "PA3",
                this.filledStringList.tail.prev.data);
        assertEquals("Previous last node should points to the new added node",
                "Gary Gillespie", oldLastNode.prev.data);
        assertEquals("Check size is updated", 5, this.filledStringList.size);
    }

    /**
     * Stress testing the add method by adding null multiple time
     */
    @Test
    public void testAddWithIndexTestTwo() {
        this.populateStringList();

        try {
            for (i = 0; i < 10; i++) {
                this.StrEmptyList.add(0, null);
            }
        } catch (NullPointerException e) {
            // Exception is successfully caught. Passing the test case.
        }
    }

    /**
     * Test the get method when index is out of bound and when list is empty
     */
    @Test
    public void testGet() {
        this.populateStringList();

        try {
            this.filledStringList.get(5);
            this.filledStringList.get(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // Exception is successfully caught. Passing the test case.
        }

        try {
            this.intEmptyList.get(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // Exception is successfully caught. Passing the test case.
        }
    }

    /**
     * Test the getNth method when the index is out of bound and when list is
     * empty
     */
    @Test
    public void testGetNth() {
        this.populateStringList();
        try {
            this.filledStringList.getNth(5);
            this.filledStringList.getNth(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // Exception is successfully caught. Passing the test case.
        }

        try {
            this.intEmptyList.getNth(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // Exception is successfully caught. Passing the test case.
        }
    }

    /**
     * Test the set method when: - index is at the end of the list - index is at
     * 3rd position - index is out of range - list is empty
     */
    @Test
    public void testSet() {
        this.populateStringList();

        // Setting the last string to CSE 100
        this.filledStringList.set(2, "CSE 100");
        assertEquals("Value at index 2 should be reset", "CSE 100",
                this.filledStringList.get(2));

        // Fill the empty list and then set the 3rd element to 6
        for (i = 0; i < 10; i++) {
            this.intEmptyList.add(3);
        }
        this.intEmptyList.set(3, 6);
        assertEquals("Value at index 3 should be reset",
                this.intEmptyList.set(3, 6), this.intEmptyList.get(3));

        // Set an element in an empty list
        try {
            this.StrEmptyList.set(0, "Hello World");
            fail();
        } catch (IndexOutOfBoundsException e) {
            // Exception is successfully caught. Passing the test case.
        }

        // Set an element at an index out of range
        try {
            this.filledStringList.set(-1, "Hello World");
            fail();
        } catch (IndexOutOfBoundsException e) {
            // Exception is successfully caught. Passing the test case.
        }

    }

    /**
     * Test for the remove method when index is out of bound
     */
    @Test
    public void testRemoveTestOne() {
        try {
            this.intEmptyList.remove(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // Exception is successfully caught. Passing the test case.
        }

        try {
            this.filledStringList.remove(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // Exception is successfully caught. Passing the test case.
        }
    }

    /**
     * Test the remove method when index is in the middle of the list
     */
    @Test
    public void testRemoveTestTwo() {
        this.populateStringList();

        String removedData = this.filledStringList.remove(2);
        assertEquals("Removed node should be returned Correctly", "Winter 2022",
                removedData);
        assertSame("Removed node should be returned Correctly",
                this.filledStringList.head.next,
                this.filledStringList.tail.prev.prev);
        assertEquals("The size should be 3", 3, this.filledStringList.size());

        String removedData1 = this.filledStringList.remove(2);
        assertEquals("Removed node should be returned Correctly", "PA3",
                removedData1);
        assertSame("Removed node should be returned Correctly",
                this.filledStringList.head.next,
                this.filledStringList.tail.prev.prev);
        assertEquals("The size should be 2", 2, this.filledStringList.size());
    }

    /**
     * Test the clear method when list is empty
     */
    @Test
    public void testClear() {
        this.intEmptyList.clear();
        ;
        assertEquals("The size should be 0", 0, this.intEmptyList.size);
        assertTrue("LinkedList should be empty", intEmptyList.isEmpty());

        this.StrEmptyList.clear();
        ;
        assertEquals("The size should be 0", 0, this.StrEmptyList.size);
        assertTrue("LinkedList should be empty", StrEmptyList.isEmpty());

    }

    /**
     * Test the size method after doing multiple operation
     */
    @Test
    public void testSize() {
        // Add 1 element
        this.intEmptyList.add(1);
        assertEquals("The size should be 1", 1, this.intEmptyList.size);
        this.StrEmptyList.add("CSE");
        assertEquals("The size should be 10", 1, this.StrEmptyList.size);

        // Add 9 more elements
        for (i = 0; i < 9; i++) {
            this.intEmptyList.add(i);
            this.StrEmptyList.add("CSE");
        }
        assertEquals("The size should be 10", 10, this.intEmptyList.size);
        assertEquals("The size should be 10", 10, this.StrEmptyList.size);

        // Remove 1 element
        this.intEmptyList.remove(0);
        assertEquals("The size should be 9", 9, this.intEmptyList.size);

        this.StrEmptyList.remove(0);
        assertEquals("The size should be 9", 9, this.StrEmptyList.size);

        // Set a new element at index 3
        this.intEmptyList.set(3, 10);
        assertEquals("The size should be 9", 9, this.intEmptyList.size);

        this.StrEmptyList.set(3, "UCSD");
        assertEquals("The size should be 9", 9, this.StrEmptyList.size);

        // Set a new element at index 3
        this.intEmptyList.clear();
        ;
        assertEquals("The size should be 0", 0, this.intEmptyList.size);

        this.StrEmptyList.clear();
        ;
        assertEquals("The size should be 0", 0, this.StrEmptyList.size);
    }
}