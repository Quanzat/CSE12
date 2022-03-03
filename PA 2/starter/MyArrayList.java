/**
 * Name: Quan Tran
 * ID: A16191839
 * Email: qutran@ucsd.edu
 * Sources used: Lecture and piazza
 * 
 * This file contain all the method that will be used to complete both jUnit
 * tests in  MyArrayListHiddenTester.java and MyArrayListPublicTester.java
 *
 */


 /**
 * This class defined many methods that will be used to accomplish many
 * different operation to an arraylist. These method will be used in test
 * files to validate if the method was implement correctly. 
 */

@SuppressWarnings("unchecked")

public class MyArrayList <E> {
    Object[] data;
    int size;

    /**
     * Initialized the Object array list with a default length of 5
     * Set the size to 0
     */
    public MyArrayList(){
        this.data = new Object[5];
        this.size = 0;
    }

    /**
     * Initialized the Object array list with a default 
     * length of initialCapacity
     * Throw an exception if initialCapacity is invalid 
     * (i.e., any value less than 0)
     */
    public MyArrayList(int initialCapacity){
        if (initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        this.size =  0;
        this.data = new Object[initialCapacity];
    }

    /**
     * Initialize the instance variables with the input array 
     * of capacity equal to the length of arr
     * If arr is equal to null, go to default behaviors of creating ArrayList
     * with the default capacity
     */
    public MyArrayList (E[] arr){
        if (arr == null){
            this.data = new Object[5];
            this.size = 0;
        }
        else{
            this.data = new Object[arr.length];
            this.size = arr.length;
            /**
             * copy element of arr into data
             */
            for (int i = 0; i < arr.length; i++){
                this.data[i] = arr[i];
            }
        }
    }

    /**
     * If capacity is a non-zero number, double the capacity by 2
     * If capacity is 0, create an ArrayList with the default capacity of 5
     * If capacity is less than the size in the array, set the capacity equal
     * to the requiredCapacity. However, if requiredCapacity is less than the array
     * then throw an exception error message
     */
    public void expandCapacity (int requiredCapacity){
        if (requiredCapacity < data.length){
            throw new IllegalArgumentException();
        }
        else if (data.length != 0){
            this.data = new Object[data.length * 2];
        }
        else if (data.length == 0){
            this.data = new Object[5];
        }
        
        if (data.length < size){
            this.data = new Object[requiredCapacity];
        }
        else if (data.length < requiredCapacity){
            this.data = new Object[requiredCapacity];
        }
    }

    /**
     * Return the array capacity
     */
    public int getCapacity(){
        size = this.data.length;
        return size;
    }

    /**
     * Insert an element at a specific index
     */
    public void insert(int index, E element){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if (this.data.length <= size){
            expandCapacity(size);
        }
        for (int i = size; i >= index + 1; i--) {
            this.data[i] = this.data[i - 1];
        }
        this.data[index] = element;
        size++;
    }

    /**
     * Insert an element at the end of the array
     * If the array capacity is already full, expand the capacity
     * accordingly to the expandCapacity method
     */
    public void append(E element){
        if (this.data.length <= size){
            expandCapacity(size);
        }
        insert(size, element);

    }

    /**
     * Insert an element at the beginning of the array
     * If the array capacity is already full, expand the capacity
     * accordingly to the expandCapacity method
     */
    public void prepend(E element){
        if (this.data.length == size){
            expandCapacity((int) element);
        }
        insert(0, element);
    }

    /**
     * Get an element at the specified index
     */
    public E get(int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        return (E) this.data[index];
    }

    /**
     * Set the given element at the specified index and return 
     * the overwritten element.
     */
    public E set(int index, E element){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < data.length; i++){
            if (index == i){
                this.data[i] = element;
            }
        }
        return element;
    }

    /**
     * Remove and return the element at the specified index.
     */
    public E remove(int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
           data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return (E) this.data[index];
    }

    /**
     * Return the number of elements that exist in the arraylist
     */
    public int size(){
        return size;
    }
}