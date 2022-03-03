
/**
 * Name: Quan Tran 
 * ID: A16191839               
 * Email: qutran@ucsd.edu              
 * Sources used: Lecture      
 * 
 * This file contain the tests for edge cases that were not considered in the
 * public tester, it mainly tests various method within three classes called 
 * MyDeque, MyStack, MyQueue.
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * This class contains tests for methods written in MyDeque, MyStack, MyQueue,
 * specifically, it tests edge cases that were not considered in the public
 * tester.
 */
public class CustomTester {
    /**
     * This helper method setup the deque to streamline testing.
     */
    static void setupDeque(MyDeque<Integer> deque, Object[] data, int front,
            int rear, int size) {
        deque.data = data;
        deque.front = front;
        deque.rear = rear;
        deque.size = size;
    }

    // ----------------MyDeque class----------------
    /**
     * Test the constructor when initial capacity is a negative integer.
     */
    @Test
    public void testMyDequeConstructor() {
        try {
            MyDeque<Integer> deque = new MyDeque<>(-1);
        } catch (IllegalArgumentException e) {
            // Test Passed!
        }
    }

    /**
     * Test the expandCapacity method when array is empty, when array contain
     * numbers in the middle, and when array contain numbers at the end.
     */
    @Test
    public void testMyDequeExpandCapacity() {
        // ------------ Test 1 ---------------
        // Setup the test
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] test = {};
        setupDeque(deque, test, 0, 0, 0);

        // Calling the method to expand
        deque.expandCapacity();

        // Checking length, size, front, and rear
        assertEquals(10, deque.data.length);
        assertEquals(0, deque.size);
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);

        // ------------ Test 2 ---------------
        // Setup the test
        MyDeque<Integer> deque2 = new MyDeque<>(5);
        Integer[] test2 = { null, 3, 6, 2, null };
        Integer[] toTest2 = { null, 3, 6, 2, null, null, null, null, null,
                null };
        setupDeque(deque2, test2, 1, 3, 3);

        // Calling the method to expand
        deque2.expandCapacity();

        // Checking length, size, front, and rear
        assertEquals(10, deque2.data.length);
        assertEquals(3, deque2.size);
        assertEquals(0, deque2.front);
        assertEquals(2, deque2.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 10; i++) {
            assertEquals(toTest2[i], deque2.data[i]);
        }

        // ------------ Test 3 ---------------
        MyDeque<Integer> deque3 = new MyDeque<>(5);
        Integer[] test3 = { null, null, 5, 4, 3 };
        Integer[] toTest3 = { null, null, 5, 4, 3, null, null, null, null,
                null };
        setupDeque(deque3, test3, 2, 4, 3);

        // Calling the method to expand
        deque3.expandCapacity();

        // Checking length, size, front, and rear
        assertEquals(10, deque3.data.length);
        assertEquals(3, deque3.size);
        assertEquals(0, deque3.front);
        assertEquals(2, deque3.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 10; i++) {
            assertEquals(toTest3[i], deque3.data[i]);
        }
    }

    /**
     * Test the addFirst method when array is empty, when array contain numbers
     * at the end, and when array is full.
     */
    @Test
    public void testAddFirst() {
        // ------------ Test 1 ---------------
        // Setup the test
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] test = {};
        Integer[] toTest = { 6, null, null, null, null, null, null, null, null,
                null };
        setupDeque(deque, test, 0, 0, 0);

        // Calling the method to add
        deque.addFirst(6);

        // Checking length, size, front, and rear
        assertEquals(10, deque.data.length);
        assertEquals(1, deque.size);
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 10; i++) {
            assertEquals(toTest[i], deque.data[i]);
        }

        // ------------ Test 2 ---------------
        // Setup the test
        MyDeque<Integer> deque2 = new MyDeque<>(5);
        Integer[] test2 = { null, null, 3, 6, 2 };
        Integer[] toTest2 = { null, 6, 3, 6, 2 };
        setupDeque(deque2, test2, 2, 4, 3);

        // Calling the method to add
        deque2.addFirst(6);

        // Checking length, size, front, and rear
        assertEquals(5, deque2.data.length);
        assertEquals(4, deque2.size);
        assertEquals(1, deque2.front);
        assertEquals(4, deque2.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 5; i++) {
            assertEquals(toTest2[i], deque2.data[i]);
        }

        // ------------ Test 3 ---------------
        MyDeque<Integer> deque3 = new MyDeque<>(5);
        Integer[] test3 = { 1, 2, 5, 4, 3 };
        Integer[] toTest3 = { 1, 2, 5, 4, 3, null, null, null, null, 6 };
        setupDeque(deque3, test3, 0, 4, 5);

        // Calling the method to add
        deque3.addFirst(6);

        // Checking length, size, front, and rear
        assertEquals(10, deque3.data.length);
        assertEquals(6, deque3.size);
        assertEquals(9, deque3.front);
        assertEquals(4, deque3.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 10; i++) {
            assertEquals(toTest3[i], deque3.data[i]);
        }
    }

    /**
     * Test the addLast method when array is empty, when array contain numbers
     * at the end, and when array is full.
     */
    @Test
    public void testAddLast() {
        // ------------ Test 1 ---------------
        // Setup the test
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] test = {};
        Integer[] toTest = { 6, null, null, null, null, null, null, null, null,
                null };
        setupDeque(deque, test, 0, 0, 0);

        // Calling the method to add
        deque.addLast(6);

        // Checking length, size, front, and rear
        assertEquals(10, deque.data.length);
        assertEquals(1, deque.size);
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 10; i++) {
            assertEquals(toTest[i], deque.data[i]);
        }

        // ------------ Test 2 ---------------
        // Setup the test
        MyDeque<Integer> deque2 = new MyDeque<>(5);
        Integer[] test2 = { null, null, 3, 6, 2 };
        Integer[] toTest2 = { 6, null, 3, 6, 2 };
        setupDeque(deque2, test2, 2, 4, 3);

        // Calling the method to add
        deque2.addLast(6);

        // Checking length, size, front, and rear
        assertEquals(5, deque2.data.length);
        assertEquals(4, deque2.size);
        assertEquals(2, deque2.front);
        assertEquals(0, deque2.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 5; i++) {
            assertEquals(toTest2[i], deque2.data[i]);
        }

        // ------------ Test 3 ---------------
        MyDeque<Integer> deque3 = new MyDeque<>(5);
        Integer[] test3 = { 1, 2, 5, 4, 3 };
        Integer[] toTest3 = { 1, 2, 5, 4, 3, 6, null, null, null, null };
        setupDeque(deque3, test3, 0, 4, 5);

        // Calling the method to add
        deque3.addLast(6);

        // Checking length, size, front, and rear
        assertEquals(10, deque3.data.length);
        assertEquals(6, deque3.size);
        assertEquals(0, deque3.front);
        assertEquals(5, deque3.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 10; i++) {
            assertEquals(toTest3[i], deque3.data[i]);
        }
    }

    /**
     * Test the removeFirst method when array is empty, when array contain
     * numbers at the end, and when array is full.
     */
    @Test
    public void testRemoveFirst() {
        // ------------ Test 1 ---------------
        // Setup the test
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] test = {};
        setupDeque(deque, test, 0, 0, 0);

        // Calling the method and checking if the method return the correctly
        assertEquals(null, deque.removeFirst());

        // Checking length, size, front, and rear
        assertEquals(0, deque.data.length);
        assertEquals(0, deque.size);
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);

        // ------------ Test 2 ---------------
        // Setup the test
        MyDeque<Integer> deque2 = new MyDeque<>(5);
        Integer[] test2 = { null, null, 3, 6, 2 };
        Integer[] toTest2 = { null, null, null, 6, 2 };
        setupDeque(deque2, test2, 2, 4, 3);

        // Calling the method and checking if the method return the correctly
        assertEquals(Integer.valueOf(3), deque2.removeFirst());

        // Checking length, size, front, and rear
        assertEquals(5, deque2.data.length);
        assertEquals(2, deque2.size);
        assertEquals(3, deque2.front);
        assertEquals(4, deque2.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 5; i++) {
            assertEquals(toTest2[i], deque2.data[i]);
        }

        // ------------ Test 3 ---------------
        MyDeque<Integer> deque3 = new MyDeque<>(5);
        Integer[] test3 = { 1, 2, 5, 4, 3 };
        Integer[] toTest3 = { null, 2, 5, 4, 3 };
        setupDeque(deque3, test3, 0, 4, 5);

        // Calling the method and checking if the method return the correctly
        assertEquals(Integer.valueOf(1), deque3.removeFirst());

        // Checking length, size, front, and rear
        assertEquals(5, deque3.data.length);
        assertEquals(4, deque3.size);
        assertEquals(1, deque3.front);
        assertEquals(4, deque3.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 5; i++) {
            assertEquals(toTest3[i], deque3.data[i]);
        }
    }

    /**
     * Test the removeLast method when array is empty, when array contain
     * numbers at the end, and when array is full.
     */
    @Test
    public void testRemoveLast() {
        // ------------ Test 1 ---------------
        // Setup the test
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] test = {};
        setupDeque(deque, test, 0, 0, 0);

        // Calling the method and checking if the method return the correctly
        assertEquals(null, deque.removeLast());

        // Checking length, size, front, and rear
        assertEquals(0, deque.data.length);
        assertEquals(0, deque.size);
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);

        // ------------ Test 2 ---------------
        // Setup the test
        MyDeque<Integer> deque2 = new MyDeque<>(5);
        Integer[] test2 = { null, null, 3, 6, 2 };
        Integer[] toTest2 = { null, null, 3, 6, null };
        setupDeque(deque2, test2, 2, 4, 3);

        // Calling the method and checking if the method return the correctly
        assertEquals(Integer.valueOf(2), deque2.removeLast());

        // Checking length, size, front, and rear
        assertEquals(5, deque2.data.length);
        assertEquals(2, deque2.size);
        assertEquals(2, deque2.front);
        assertEquals(3, deque2.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 5; i++) {
            assertEquals(toTest2[i], deque2.data[i]);
        }

        // ------------ Test 3 ---------------
        MyDeque<Integer> deque3 = new MyDeque<>(5);
        Integer[] test3 = { 1, 2, 5, 4, 3 };
        Integer[] toTest3 = { 1, 2, 5, 4, null };
        setupDeque(deque3, test3, 0, 4, 5);

        // Calling the method and checking if the method return the correctly
        assertEquals(Integer.valueOf(3), deque3.removeLast());

        // Checking length, size, front, and rear
        assertEquals(5, deque3.data.length);
        assertEquals(4, deque3.size);
        assertEquals(0, deque3.front);
        assertEquals(3, deque3.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 5; i++) {
            assertEquals(toTest3[i], deque3.data[i]);
        }
    }

    /**
     * Test the peekFirst method when array is empty, when array contain numbers
     * at the end, and when array is full.
     */
    @Test
    public void testPeekFirst() {
        // ------------ Test 1 ---------------
        // Setup the test
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] test = {};
        setupDeque(deque, test, 0, 0, 0);

        // Calling the method and checking if the method return the correctly
        assertEquals(null, deque.peekFirst());

        // Checking length, size, front, and rear
        assertEquals(0, deque.data.length);
        assertEquals(0, deque.size);
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);

        // ------------ Test 2 ---------------
        // Setup the test
        MyDeque<Integer> deque2 = new MyDeque<>(5);
        Integer[] test2 = { null, null, 3, 6, 2 };
        Integer[] toTest2 = { null, null, 3, 6, 2 };
        setupDeque(deque2, test2, 2, 4, 3);

        // Calling the method and checking if the method return the correctly
        assertEquals(Integer.valueOf(3), deque2.peekFirst());

        // Checking length, size, front, and rear
        assertEquals(5, deque2.data.length);
        assertEquals(3, deque2.size);
        assertEquals(2, deque2.front);
        assertEquals(4, deque2.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 5; i++) {
            assertEquals(toTest2[i], deque2.data[i]);
        }

        // ------------ Test 3 ---------------
        MyDeque<Integer> deque3 = new MyDeque<>(5);
        Integer[] test3 = { 1, 2, 5, 4, 3 };
        Integer[] toTest3 = { 1, 2, 5, 4, 3 };
        setupDeque(deque3, test3, 0, 4, 5);

        // Calling the method and checking if the method return the correctly
        assertEquals(Integer.valueOf(1), deque3.peekFirst());

        // Checking length, size, front, and rear
        assertEquals(5, deque3.data.length);
        assertEquals(5, deque3.size);
        assertEquals(0, deque3.front);
        assertEquals(4, deque3.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 5; i++) {
            assertEquals(toTest3[i], deque3.data[i]);
        }
    }

    /**
     * Test the peekLast method when array is empty, when array contain numbers
     * at the end, and when array is full.
     */
    @Test
    public void testPeekLast() {
        // ------------ Test 1 ---------------
        // Setup the test
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] test = {};
        setupDeque(deque, test, 0, 0, 0);

        // Calling the method and checking if the method return the correctly
        assertEquals(null, deque.peekLast());

        // Checking length, size, front, and rear
        assertEquals(0, deque.data.length);
        assertEquals(0, deque.size);
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);

        // ------------ Test 2 ---------------
        // Setup the test
        MyDeque<Integer> deque2 = new MyDeque<>(5);
        Integer[] test2 = { null, null, 3, 6, 2 };
        Integer[] toTest2 = { null, null, 3, 6, 2 };
        setupDeque(deque2, test2, 2, 4, 3);

        // Calling the method and checking if the method return the correctly
        assertEquals(Integer.valueOf(2), deque2.peekLast());

        // Checking length, size, front, and rear
        assertEquals(5, deque2.data.length);
        assertEquals(3, deque2.size);
        assertEquals(2, deque2.front);
        assertEquals(4, deque2.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 5; i++) {
            assertEquals(toTest2[i], deque2.data[i]);
        }

        // ------------ Test 3 ---------------
        MyDeque<Integer> deque3 = new MyDeque<>(5);
        Integer[] test3 = { 1, 2, 5, 4, 3 };
        Integer[] toTest3 = { 1, 2, 5, 4, 3 };
        setupDeque(deque3, test3, 0, 4, 5);

        // Calling the method and checking if the method return the correctly
        assertEquals(Integer.valueOf(3), deque3.peekLast());

        // Checking length, size, front, and rear
        assertEquals(5, deque3.data.length);
        assertEquals(5, deque3.size);
        assertEquals(0, deque3.front);
        assertEquals(4, deque3.rear);

        // Checking element if it is in correct orders
        for (int i = 0; i < 5; i++) {
            assertEquals(toTest3[i], deque3.data[i]);
        }
    }

    // ----------------MyStack class----------------
    /**
     * Test MyQueue when stacks is called with multiple methods such as push and
     * peek.
     */
    @Test
    public void testMyStack() {
        MyStack<Integer> stackTest = new MyStack<>(6);
        assertTrue(stackTest.empty());
        stackTest.push(1);
        stackTest.push(2);
        stackTest.push(3);

        for (int i = 0; i < 10; i++) {
            stackTest.push(6);
        }
        assertEquals(Integer.valueOf(6), stackTest.peek());
        assertEquals(13, stackTest.size());
    }

    // ----------------MyQueue class----------------
    /**
     * Test MyQueue when queue is called with multiple methods such as enqueue
     * and peek.
     */
    @Test
    public void testMyQueue() {
        MyQueue<Integer> queueTest = new MyQueue<Integer>(4);
        assertTrue(queueTest.empty());
        queueTest.enqueue(1);
        queueTest.enqueue(2);
        queueTest.enqueue(3);

        for (int i = 0; i < 10; i++) {
            queueTest.enqueue(i);
        }
        assertEquals(Integer.valueOf(1), queueTest.peek());
        assertEquals(13, queueTest.size());
    }
}
