/**
 * Name: Quan Tran
 * ID: A16191839
 * Email: qutran@ucsd.edu
 * Sources used: Piazza, Lecture
 * 
 * This file implement all of the hidden test the methods implemented in 
 * MyArrayList.java.
 */

 //IMPORTANT: Do not change the headers!

import static org.junit.Assert.*;
import org.junit.*;

@SuppressWarnings("unchecked")

public class MyArrayListHiddenTester {
    static final int DEFAULT_CAPACITY = 5;
    static final int MY_CAPACITY = 3;

    Object[] arr = new Object[5];
    Integer[] list = { 1, 2, 3 };
    Integer[] fullList = { 1, 2, 3, 4, 5};

    private MyArrayList listEmpty, listDefaultCap, listCustomCapacity, listWithNull, listWithInt, listwithfullInt;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test */
    @Before
    public void setUp() throws Exception {
        listEmpty = new MyArrayList();
        listDefaultCap = new MyArrayList(DEFAULT_CAPACITY);
        listCustomCapacity = new MyArrayList(MY_CAPACITY);
        listWithNull = new MyArrayList(arr);
        listWithInt = new MyArrayList<Integer>(list);
        listwithfullInt = new MyArrayList<Integer>(fullList);
        
    }

    /**
     * Aims to test the capacity argument constructor when the input
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg(){
        boolean exceptionThrown = false;
        try {
            int initialCapacity = -1;
            listDefaultCap = new MyArrayList(initialCapacity);
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue("Except was not thrown for the capacity argument constructor when the input is not valid", exceptionThrown);
    }
    /**
     * Aims to test the Array argument constructor when the input
     * is null
     */
    @Test
    public void testConstructorNullArg(){
        listWithNull = new MyArrayList(null);
        assertEquals(5, listWithNull.data.length);
        
        
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        listwithfullInt.append(Integer.valueOf(5));
        assertEquals(10, listwithfullInt.data.length);
        assertEquals(6, listwithfullInt.size);


    }

    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        listEmpty.prepend(null);
        assertEquals(null, listEmpty.data[0]);
        assertEquals(5, listEmpty.data.length);
        assertEquals(1, listEmpty.size);
        
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test
    public void testInsertOutOfBound(){
        boolean exceptionThrown = false;
        try {
            listWithInt.insert(-1, Integer.valueOf(5));
        }
        catch (IndexOutOfBoundsException e) {
            exceptionThrown = true;
        }
        assertTrue("Except not thrown for the get method when input index is out of bound", exceptionThrown);
    }
    
    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        for (int i = 0; i < 1000; i++){
            listwithfullInt.append(Integer.valueOf(5));
        }
        assertEquals(5, listwithfullInt.data[1000]);
        assertEquals(1280, listwithfullInt.data.length);
        assertEquals(1005, listwithfullInt.size);
    }
    

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound(){
        boolean exceptionThrown = false;
        try {
            listWithInt.get(-1);
        }
        catch (IndexOutOfBoundsException e) {
            exceptionThrown = true;
        }
        assertTrue("Except not thrown for the get method when input index is out of bound", exceptionThrown);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound(){
        boolean exceptionThrown = false;
        try {
            listWithInt.set(-1, 6);
        }
        catch (IndexOutOfBoundsException e) {
            exceptionThrown = true;
        }
        assertTrue("Except not thrown for the set method when input index is out of bound", exceptionThrown);
    }


    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound(){
        boolean exceptionThrown = false;
        try {
            listWithInt.remove(-1);
        }
        catch (IndexOutOfBoundsException e) {
            exceptionThrown = true;
        }
        assertTrue("Except not thrown for the remove method when input index is out of bound", exceptionThrown);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller(){
        boolean exceptionThrown = false;
        try {
            listDefaultCap.expandCapacity(3);
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue("Except not thrown for the remove method when input index is out of bound", exceptionThrown);

    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than double(2x) the current capacity
     */
    @Test
    public void testExpandCapacityExplode(){
        listwithfullInt.expandCapacity(40);
        assertEquals(40, listwithfullInt.data.length);
        assertEquals(5, listwithfullInt.size);

        listEmpty.expandCapacity(30);
        assertEquals(30, listEmpty.data.length);
        assertEquals(0, listEmpty.size);
    }
}