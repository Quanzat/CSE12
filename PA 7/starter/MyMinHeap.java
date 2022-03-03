
/**
 * Name: Quan Tran 
 * ID: A16191839               
 * Email: qutran@ucsd.edu              
 * Sources used: Lecture      
 * 
 * This file contain MyMinHeap class which implements methods for the Heap. 
 * It contains methods where elements could be added, remove, or swaps from the 
 * heap. It is also an implementation of the heap for MyPriorityQueue class.
 */

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class implements MyMinHeap and contains method to maniputlate it.
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {
    // Instance variables
    public ArrayList<E> data;
    int i;

    /**
     * A constructor that initializes data to an empty ArrayList.
     */
    public MyMinHeap() {
        this.data = new ArrayList<>();
    }

    /**
     * A constructor that initializes min-heap using element in collection.
     * 
     * @param collection - the collection of data.
     * @throws NullPointerException - when collection is empty or element of
     * collection is null.
     */
    public MyMinHeap(Collection<? extends E> collection) {
        if (collection == null || collection.contains(null)) {
            throw new NullPointerException();
        }

        // Initilize the data
        data = new ArrayList<>(collection);

        // Iterate through the data backward and percolating each element down
        for (i = size() - 1; i >= 0; i--) {
            this.percolateDown(i);
        }
    }

    /**
     * This method swap the elements at from and to indices in data.
     * 
     * @param from - the index contain an element to be swapped
     * @param to - the index where the element should be swapped with
     */
    protected void swap(int from, int to) {
        // Getting the elements to be swap
        E fromElem = this.data.get(from);
        E toElem = this.data.get(to);

        // Swapping the elements.
        this.data.set(from, toElem);
        this.data.set(to, fromElem);
    }

    /**
     * This method calculate and return the parent index of the parameter index.
     * 
     * @return - the index of the parent node.
     */
    protected int getParentIdx(int index) {
        return (index - 1) / 2;
    }

    /**
     * This method calculate and return the left child index of the parameter
     * index.
     * 
     * @return - the index of the left child node.
     */
    protected int getLeftChildIdx(int index) {
        return index * 2 + 1;
    }

    /**
     * This method calculate and return the right child index of the parameter
     * index.
     * 
     * @return - the index of the right child node.
     */
    protected int getRightChildIdx(int index) {
        return index * 2 + 2;
    }

    /**
     * This method return the indexes of the smallest child of the element at a
     * specific index.
     * 
     * @param index - the index of the parent node.
     * @return - the index of the smallest child || -1 if there is a leaf node.
     */
    protected int getMinChildIdx(int index) {
        // Acquiring the indexes for left node and right node
        int leftChildIndex = getLeftChildIdx(index);
        int rightChildIndex = getRightChildIdx(index);

        // Defaulting left index as child if right index node does not exist.
        if (rightChildIndex >= size()) {
            // Checking if any node is leaf node
            if (leftChildIndex >= size()) {
                return -1;
            }
            return leftChildIndex;
        }

        // Acquiring the left and right node to compares
        E leftChild = this.data.get(leftChildIndex);
        E rightChild = this.data.get(rightChildIndex);

        // Checking when left node is larger and return it
        if (leftChild.compareTo(rightChild) <= 0) {
            return leftChildIndex;
        }
        return rightChildIndex; // when right is larger
    }

    /**
     * This method swap the child index with the parent index if there is a
     * violation where the child is larger than the parent.
     * 
     * @param index - the index of the child node.
     * @return - nothing if parent and child are equals and when index is 0
     */
    protected void percolateUp(int index) {
        if (index < size()) {
            // Getting the parent index and element
            int parentIndex = this.getParentIdx(index);
            E parentElem = this.data.get(parentIndex);

            // Getting the child element
            E childElem = this.data.get(index);

            // Checking when index is 0 and when parent and child are equal
            if (parentElem.compareTo(childElem) == 0 || index == 0)
                return;
            {
                // Checking when parent is larger than child
                if (parentElem.compareTo(childElem) > 0) {
                    swap(index, parentIndex);

                    // Looping the method until no more violation exits.
                    percolateUp(parentIndex);
                }
            }
        }
    }

    /**
     * This method swap the parent index with the child index if there is a
     * violation where the parent is larger than the child.
     * 
     * @param index - the index of the parent node.
     * @return - nothing if the child index is a leaf node
     */
    protected void percolateDown(int index) {
        // Getting the child index
        int childIndex = getMinChildIdx(index);

        // Checking when index is a leaf node
        if (childIndex == -1) {
            return;
        }

        // Getting the parent and child element to compare
        E parentElem = this.data.get(index);
        E childElem = this.data.get(childIndex);

        // Checking when parent is larger than child
        if (parentElem.compareTo(childElem) > 0) {
            swap(index, childIndex);

            // Looping the method until no more violation exits.
            percolateDown(childIndex);
        }
    }

    /**
     * This method delete the element at specified index and return the deleted
     * element.
     * 
     * @param index - the index contain the element to be deleted.
     * @return - the deleted element.
     */
    protected E deleteIndex(int index) {
        // Storing the element and the index to be remove
        E removedElement = data.get(index);
        int deletedIndex = index;

        // Swapping the element to the end and delete it
        swap(index, size() - 1);
        data.remove(size() - 1);

        // Return if the element to remove was the last element
        if (deletedIndex == size() - 1) {
            return removedElement;
        }

        // Checking if index is within size
        if (index < size()) {
            // Getting the parent and child element to compare
            int parentIndex = getParentIdx(index);
            E parentElement = data.get(parentIndex);
            E childElem = this.data.get(index);

            // Percolate up when the parent is bigger
            if (parentElement.compareTo(childElem) > 0) {
                percolateUp(index);
            }

            // Percolate down when the parent is smaller or delete index is 0
            if (parentElement.compareTo(childElem) < 0 || deletedIndex == 0) {
                percolateDown(index);
            }
        }
        return removedElement;
    }

    /**
     * This method insert an element to the end of the tree and percolate up the
     * element if there is any violations.
     * 
     * @param element - the element to be insert.
     * @throws NullPointerException - when collection is empty or element of
     * collection is null.
     */
    public void insert(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        this.data.add(element);
        percolateUp(size() - 1);
    }

    /**
     * This method get the root element of the heap.
     * 
     * @return - the root element.
     */
    public E getMin() {
        if (size() == 0) {
            return null;
        }
        return this.data.get(0);
    }

    /**
     * This method remove and return the element at the root.
     * 
     * @return null if element is null || the deleted root if element exits.
     */
    public E remove() {
        if (size() == 0) {
            return null;
        }
        return deleteIndex(0);
    }

    /**
     * This method return the number elements in this min-heap.
     * 
     * @return - the size of the array.
     */
    public int size() {
        return this.data.size();
    }

    /**
     * This method clear the entire heap.
     */
    public void clear() {
        this.data.clear();
    }
}