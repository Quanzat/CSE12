
/**
 * Name: Quan Tran 
 * ID: A16191839               
 * Email: qutran@ucsd.edu              
 * Sources used: Lecture      
 * 
 * This file contain the class MyPrioirtyQueue, which is an implementation for 
 * the heap data structure based on MyMinHeap class. It contain methods to add/ 
 * remove from the queue.
 */

import java.util.Collection;

/**
 * This class implements the heap data structure using an instance variables
 * called the heap.
 */
public class MyPriorityQueue<E extends Comparable<E>> {

    public MyMinHeap<E> heap;

    /**
     * Constructor that creates an empty priority queue
     */
    public MyPriorityQueue() {
        heap = new MyMinHeap<>();
    }

    /**
     * Constructor that creates a priority queue from a collection
     * 
     * @param collection The collection used to intialize priority queue
     */
    public MyPriorityQueue(Collection<? extends E> collection) {
        heap = new MyMinHeap<>(collection);
    }

    /**
     * Adds an element to the priority queue
     * 
     * @param element the element to be added
     */
    public void push(E element) {
        heap.insert(element);
    }

    /**
     * Removes the element with the highest priority from the priority queue
     * 
     * @return the element with the highest priority
     */
    public E pop() {
        return heap.remove();
    }

    /**
     * Sees the element with the highest priority from the priority queue
     * 
     * @return the element with the highest priority
     */
    public E peek() {
        return heap.getMin();
    }

    /**
     * Finds the number of elements in the priority queue
     * 
     * @return the number of elements in the priority queue
     */
    public int getLength() {
        return heap.size();
    }

    /**
     * Remove all the elements from the priority queue.
     */
    public void clear() {
        heap.clear();
    }
}