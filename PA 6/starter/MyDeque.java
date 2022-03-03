/**
 * Name: Quan Tran 
 * ID: A16191839               
 * Email: qutran@ucsd.edu              
 * Sources used: Lecture        
 * 
 * This file contains a MyDeque class, which is an implementation for the Queue
 * ADT. Elements can be added/removed from the queue in either a FIFO or LIFO 
 * ordering.
 */

/**
 * This class implements the Deque ADT to implements other classes called
 * MyQueue and MyStacks
 */

public class MyDeque<E> implements DequeInterface<E> {
    Object[] data;
    int size;
    int rear;
    int front;
    int i;

    /**
     * This is a constructor for MyDeque whichInitialize the Object array data
     * with length of initialCapacity.
     * 
     * @param initialCapacity - input initial capacity.
     * @throws IllegalArgumentException - if size is less than 0.
     */
    public MyDeque(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException();
        data = new Object[initialCapacity];
    }

    /**
     * This return the size of the Object array.
     * 
     * @return - the array size.
     */
    public int size() {
        return this.size;
    }

    /**
     * This method expand the capacity by either increase the capacity to 10 if
     * size is 0, or double the current capacity if the method is called.
     */
    public void expandCapacity() {
        if (size == 0) {
            Object[] defaultCapacity = new Object[10];
            // Add in all the element that need to be added to the new object
            for (i = 0; i < data.length; i++) {
                defaultCapacity[i] = data[i];
            }
            data = defaultCapacity; // Set the data as the new object
        } else {
            Object[] doubleCapacity = new Object[data.length * 2];
            // Add in all the element that need to be added to the new object
            for (i = 0; i < data.length; i++) {
                doubleCapacity[i] = data[i];
            }
            data = doubleCapacity; // Set the data as the new object
            front = 0;
            // Rear is 0 if size is empty and size - 1 if size is not empty
            rear = size == 0 ? 0 : size - 1;
        }
    }

    /**
     * This method add the specified element to the front of the deque and
     * update front and size accordingly. It also expand the capacity of the
     * array if needed.
     * 
     * @param element - element to be added.
     * @throws NullPointerException - when element is null.
     */
    public void addFirst(Object element) {
        if (element == null) {
            throw new NullPointerException();
        }
        // Increase the capacity if deque is full
        if (size >= data.length) {
            this.expandCapacity();
        }
        // Index to add is one less the current front
        i = 0;
        if (size != 0) {
            i = front != 0 ? front - 1 : data.length - 1;
        }
        data[i] = element;
        front = i; // Update front to the correct position
        size++; // Update size
    }

    /**
     * This method add the specified element to the rear of the deque and update
     * rear and size accordingly. It also expand the capacity of the array if
     * needed.
     * 
     * @param element - element to be added.
     * @throws NullPointerException - when element is null.
     */
    public void addLast(Object element) {
        if (element == null) {
            throw new NullPointerException();
        }
        // Increase the capacity if deque is full
        if (size >= data.length) {
            this.expandCapacity();
        }
        // Index to add is at 0 if rear is already at the end, else add to
        // the index after rear
        i = 0;
        if (size != 0) {
            i = rear == this.data.length - 1 ? 0 : rear + 1;
        }
        data[i] = element;
        rear = i; // Update rear to the correct position
        size++; // Update size

    }

    /**
     * This method remove and return the removed element at the front of the
     * deque. It also update the front and size of deque accordingly.
     * 
     * @return - the element that was removed.
     */
    @SuppressWarnings("unchecked")
    public E removeFirst() {
        if (size == 0)
            return null;
        {
            Object removedElement = peekFirst(); // Storing the removed element
            data[front] = null;
            // If front is at the end, then front = 0; Otherwise, front + 1.
            front = front == this.data.length - 1 ? 0 : front + 1;
            size--; // Update size
            return (E) removedElement;
        }
    }

    /**
     * This method remove and return the removed element at the front of the
     * deque. It also update the front and size of deque accordingly.
     * 
     * @return - the element that was removed.
     */
    @SuppressWarnings("unchecked")
    public E removeLast() {
        if (size == 0)
            return null;
        {
            Object removedElement = peekLast(); // Storing the removed element
            data[rear] = null;
            // If rear is at the front, then set rear to the end; Otherwise,
            // set rear to one position less.
            rear = rear == 0 ? this.data.length - 1 : rear - 1;
            size--; // Update size
            return (E) removedElement;
        }
    }

    /**
     * This method @return the element at front of the deque if there is an
     * element exists. Otherwise, return null.
     */
    @SuppressWarnings("unchecked")
    public E peekFirst() {
        return (boolean) (this.size == 0) ? null : (E) this.data[front];
    }

    /**
     * This method @return the element at rear of the deque if there is an
     * element exists. Otherwise, return null.
     */
    @SuppressWarnings("unchecked")
    public E peekLast() {
        return (boolean) (this.size == 0) ? null : (E) this.data[rear];
    }
}
