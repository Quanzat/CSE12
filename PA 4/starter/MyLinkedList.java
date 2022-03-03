
/**
 * Name: Quan Tran, Marina Xu
 * ID: A16191839, A16235362
 * Email: qutran@ucsd.edu, max003@ucsd.edu
 * Sources used: Lecture and Zybook
 * 
 * The purpose of this file is to write methods
 * that can be used to alter Linked Lists. It includes
 * a Node class that helps alter Nodes added to Linked Lists.
 * It also include a Iterator class that helps iterate through
 * the Linked Lists.
 */

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * This class has many methods including get, getNth, size, remove add at index,
 * set at index, clear, and more. This class includes a Node subclass and
 * Iterator subclass. Moveover, it extends the AbstractList<E> class.
 */
public class MyLinkedList<E> extends AbstractList<E> {
    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         * 
         * @param element Element to add, can be null
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Set the parameter prev as the previous node
         * 
         * @param prev - new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Set the parameter next as the next node
         * 
         * @param next - new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Set the parameter element as the node's data
         * 
         * @param element - new element
         */
        public void setElement(E element) {
            this.data = element;
        }

        /**
         * Accessor to get the next Node in the list
         * 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Accessor to get the prev Node in the list
         * 
         * @return the previous node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Accessor to get the Nodes Element
         * 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        }
    }

    // Implementation of the MyLinkedList Class
    /**
     * Only Linked List 0-argument constructor is defined
     */
    public MyLinkedList() {
        // sets head and tail to dummy nodes
        head = new Node(null);
        tail = new Node(null);
        // sets head.next and tail.prev to each other
        head.setNext(tail);
        tail.setPrev(head);
        this.size = 0;
    }

    /**
     * Return the size of the Linked List as int
     * 
     * @return size of Linked List
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the data of the Node at the given index
     * 
     * @param index The index of the wanted Node
     * @return The data of the wanted Node
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // iterates through Linked List and returns data
        // at given index
        Node temp = head.getNext();
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getElement();
    }

    /**
     * Adds Node at the given index
     * 
     * @param index The index to add the Node at
     * @param data The data of the Node to be added
     */
    @Override
    public void add(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        }

        else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        // if size is 0, add new node between the head and tail
        else if (size == 0) {
            Node newN = new Node(data);
            newN.setNext(tail);
            newN.setPrev(head);
            head.setNext(newN);
            tail.setPrev(newN);
            size++;
        }
        // otherwise, create new node and update next and prev
        // values accordingly
        else {
            if (index == 0) {
                Node curN = head;
                Node newN = new Node(data);
                Node sucN = curN.getNext();
                newN.setNext(sucN);
                newN.setPrev(curN);
                curN.setNext(newN);
                sucN.setPrev(newN);
                size++;
            }

            else if (index == size) {
                this.add(data);
            }

            else {
                Node curN = this.getNth(index - 1);
                Node newN = new Node(data);
                Node sucN = curN.getNext();
                newN.setNext(sucN);
                newN.setPrev(curN);
                curN.setNext(newN);
                sucN.setPrev(newN);
                size++;
            }
        }
    }

    /**
     * Adds Node to the end of the Linked List
     * 
     * @param data The data of the Node to be added
     * @return true if the new Node is added
     */
    public boolean add(E data) {
        Node newN = new Node(data);
        if (data == null) {
            throw new NullPointerException();
        }
        // if size is zero, add data between head and tail
        else if (size == 0) {
            head.setNext(newN);
            tail.setPrev(newN);
            size++;
        }
        // otherwise, update the next and prev values accordingly
        else {
            newN.setPrev(tail.prev);
            newN.setNext(tail);
            tail.getPrev().setNext(newN);
            tail.setPrev(newN);
            size++;
        }
        return true;
    }

    /**
     * Sets a Node at a given index and return the data of the Node there
     * 
     * @param index The index to put the Node
     * @param data The data of the Node to be added
     * @return The data of the Node originally at that index
     */
    public E set(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        }

        else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // iterate through the Linked List to find the index
        // then replace the Node at that index with the new Node
        else {
            Node curN = this.getNth(index);
            Node temp = head.getNext();
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            Node before = temp.getPrev();
            Node after = temp.getNext();
            Node newN = new Node(data);
            newN.setNext(after);
            newN.setPrev(before);
            before.setNext(newN);
            after.setPrev(newN);
            return curN.getElement();
        }
    }

    /**
     * Removes the Node at the given index
     * 
     * @param index The index of the Node that needs to be removed
     * @return The data of the Node that was removed
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // get the Node at the index, make Nodes out of its next and prev
        // values, then replace the Node
        else if (index == 0) {
            Node curN = this.getNth(0);
            Node sucN = curN.getNext();
            Node prevN = head;
            sucN.setPrev(prevN);
            prevN.setNext(sucN);
            size--;
            return curN.getElement();
        }

        else if (index == size - 1) {
            Node curN = tail.getPrev();
            Node sucN = curN.getNext();
            Node prevN = curN.getPrev();
            sucN.setPrev(prevN);
            prevN.setNext(sucN);
            size--;
            return curN.getElement();
        }

        else {
            Node curN = this.getNth(index);
            Node sucN = curN.getNext();
            Node prevN = curN.getPrev();
            sucN.setPrev(prevN);
            prevN.setNext(sucN);
            size--;
            return curN.getElement();
        }

    }

    /**
     * Clears the Linked List and updates the size
     */
    public void clear() {
        if (size == 0) {
            size = 0;
        }
        // remove all elements and set head and tail to each other
        else {
            head.setNext(tail);
            tail.setPrev(head);
            size = 0;
        }
    }

    /**
     * Checks if the Linked List is empty
     * 
     * @return true if the List is empty, false otherwise
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets and returns the Node at given index
     * 
     * @param index The index of the Node that needs to be returned
     * @return The Node at that index
     */
    protected Node getNth(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // iterate through the list to find the Node, return the Node
        else {
            Node temp = head.getNext();
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            return temp;
        }
    }

    protected class MyListIterator implements ListIterator<E> {
        // class variables
        Node left, right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        // MyListIterator constructor
        public MyListIterator() {
            this.left = head;
            this.right = head.next;
            idx = 0;
            forward = true;
            canRemoveOrSet = false;
        }

        /**
         * Return true if there is an element node when going in the forward
         * (head to tail) direction from the current iterator position.
         * 
         * @return true if element is going forwards and false otherwis.
         */
        public boolean hasNext() {
            if (right == null) {
                canRemoveOrSet = true;
                return true;
            }
            // If the next element is not null then canRemoveOrSet is true and
            // there is a next element
            else if (right.getElement() != null) {
                canRemoveOrSet = true;
                return true;
            }
            // If size is 0 then canRemoveOrSet is false and there is not a next
            // element
            else if (size == 0) {
                canRemoveOrSet = false;
                return false;
            }
            // Anything else return false
            else {
                canRemoveOrSet = false;
                return false;
            }
        }

        /**
         * Return the next element in the list when going forward, and move the
         * iterator forward by one node.
         * 
         * @return the next forward element.
         * @throws NoSuchElementException if there is no such element.
         */
        public E next() {
            Node nextNode = head;
            // If no element is next then throw an exeception
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }
            // If right is null or at the front, set the return element as the
            // first element in the list and move the iterator forward by one
            // node
            else if (right == null || right == head.getNext()) {
                nextNode = head.getNext();
                right = nextNode.getNext();
                left = nextNode;
                idx++;
            }
            // Otherwise, set the return element as the next element in the list
            // and move the iterator forward by one node
            else {
                nextNode = right;
                left = right;
                right = right.getNext();
                idx++;
            }
            // Condition for foward and canRemoveORSet is always true
            forward = true;
            canRemoveOrSet = true;

            // Return the element
            return (E) nextNode.getElement();
        }

        /**
         * Return true if there is an element node when going in the backward
         * (tail to head) direction from the current iterator position.
         * 
         * @return true if element is going backward and false otherwise.
         */

        public boolean hasPrevious() {
            // If left is null, then canRemoveOrSet is true but no previous
            // element exist
            if (left == null) {
                canRemoveOrSet = true;
                return false;
            }
            // If left is not null, then canRemoveOrSet true and there is a
            // previous element
            else if (left.getElement() != null) {
                canRemoveOrSet = true;
                return true;
            }
            // If size is 0, then canRemoveOrSet is false and no previous
            // element exist
            else if (size == 0) {
                canRemoveOrSet = false;
                return false;
            }
            // Otherise return false
            else {
                canRemoveOrSet = false;
                return false;
            }
        }

        /**
         * This method return the next element in the list when going backward,
         * and move the iterator backward by one node.
         * 
         * @return the previous element
         * @throws NoSuchElementException if there is no such element.
         */
        public E previous() {
            // If no previous element exist then throw an exception
            if (hasPrevious() == false) {
                throw new NoSuchElementException();
            }
            // Otherwise, set the return element as the previous element in the
            // list and move the iterator backward by one node
            else {
                right = left;
                left = left.getPrev();
                idx--;
            }
            // Condition for forward is always false but canRemoveOrSet is true
            forward = false;
            canRemoveOrSet = true;

            // Return the element
            return right.getElement();
        }

        /**
         * This method return the index of the element that would be returned by
         * a call to next() or return the list size if at the end of the list.
         * 
         * @return the index of the next element.
         */
        public int nextIndex() {
            // If idx is size then return the size
            if (idx == size) {
                return size;
            }
            // Otherwise, return idx
            else {
                return idx;
            }
        }

        /**
         * This method return the index of the element that would be returned by
         * a call to previous() or return -1 if at the start of the list.
         * 
         * @return the index of the previous element.
         */
        public int previousIndex() {
            // If idx is 0 then return a -1
            if (idx == 0) {
                return -1;
            }
            // Otherwise, return the previous idx
            else {
                return idx - 1;
            }
        }

        /**
         * Insert the given item into the list. The value of the current index
         * of the list iterator is increased by one.
         * 
         * @param element the value to add.
         * @throws NullPointerException if element is null.
         */
        public void add(E element) {
            // Create new node with the input element
            Node newN = new Node(element);
            // If element is null then throw an exception
            if (element == null) {
                throw new NullPointerException();
            }
            // If size is 0 then add the element between head and tails
            else if (size == 0) {
                head.setNext(newN);
                tail.setPrev(newN);
                newN.setNext(tail);
                newN.setPrev(head);
                this.left = newN;
                this.right = tail;
            }
            // Otherwise, add the new node between previous and next node
            else {
                Node curN = this.left;
                Node sucN = this.right;
                newN.setNext(sucN);
                newN.setPrev(curN);
                sucN.setPrev(newN);
                curN.setNext(newN);
                this.left = newN;
            }
            // The list size and idx is increase by 1
            size++;
            idx++;
            // The new node can not be remove or set after add
            canRemoveOrSet = false;

        }

        /**
         * For the node returned by the most recent next/previous call, replace
         * its value with the new value element.
         * 
         * @param element the value to set.
         * @throws NullPointerException if element is null.
         * @throws IllegalArgumentException() if canRemoveORSet == false
         */
        public void set(E element) {
            // Create new node with the input element
            Node newN = new Node(element);
            // If element is null, throw an exception
            if (element == null) {
                throw new NullPointerException();
            }
            // If canRemoveOrSet is false, throw an exception
            else if (canRemoveOrSet == false) {
                throw new IllegalArgumentException();
            }
            // Otherwise:
            else {
                // If forward is true, set the node's element returned by the
                // most recent called as the input element
                if (forward == true) {
                    Node leftN = this.left;
                    Node rightN = this.right.getPrev();
                    newN.setNext(rightN);
                    newN.setPrev(leftN);
                    rightN.setPrev(newN);
                    leftN.setNext(newN);
                    this.left = newN;
                }
                // If forward is false, set the right node's element to the new
                // input element
                else if (forward == false) {
                    Node leftN = this.left;
                    Node rightN = this.right.getNext();
                    newN.setNext(rightN);
                    newN.setPrev(leftN);
                    rightN.setPrev(newN);
                    leftN.setNext(newN);
                    this.right = newN;
                }
            }
        }

        /**
         * Remove the last element node returned by the most recent
         * next/previous call.
         * 
         * @throws IllegalArgumentException() if canRemoveORSet == false.
         */
        public void remove() {
            // If canRemoveOrSet is false, throw an exception
            if (canRemoveOrSet == false) {
                throw new IllegalArgumentException();
            }
            // Otherwise if foward is true:
            else if (forward == true) {
                Node leftN = null;
                // If idx is 0:
                if (idx == 1) {
                    // Set the left node as head
                    leftN = head;
                    // Initialized the right node as the next right node
                    Node rightN = this.right;
                    // Establishing a new link between the left and right node
                    leftN.setNext(rightN);
                    rightN.setPrev(leftN);
                    // Set left as head
                    this.left = head;
                }
                // Otherwise:
                else {
                    // Set the left node as the previous node before left
                    leftN = this.left.getPrev();
                    // Initialized the rightN as the next node
                    Node rightN = this.right;
                    // Establishing a new link between the left and right node
                    leftN.setNext(rightN);
                    rightN.setPrev(leftN);
                    // Set left as the node before left
                    this.left = leftN;
                }
                // Decrease idx and the size, then set canRemoveOrSet to false
                idx--;
                size--;
                canRemoveOrSet = false;
            }
            // Otherwise if forward is false:
            else if (forward == false) {
                Node rightN = null;
                // If idx is size:
                if (idx == size) {
                    // Set the right node as the tail
                    rightN = tail;
                    // Initialized leftN as the left node
                    Node leftN = this.left;
                    // Establishing a new link between the left and right node
                    leftN.setNext(rightN);
                    rightN.setPrev(leftN);
                    // Set the right node as the tail node
                    this.right = tail;
                }
                // Otherwise:
                else {
                    // Set right node as the next right
                    rightN = this.right.getNext();
                    // Initialized left node as the left node
                    Node leftN = this.left;
                    // Establishing a new link between the left and right node
                    leftN.setNext(rightN);
                    rightN.setPrev(leftN);
                    // Set the right node as the next right node
                    this.right = rightN;
                }
                // Decrease the size and set canRemoveOrSet to false
                size--;
                canRemoveOrSet = false;
            }
        }
    }

    /**
     * Create a new MyListIterator and return it.
     */
    public ListIterator<E> listIterator() {
        MyListIterator list = new MyListIterator();
        return list;
    }

    /**
     * Create a new MyListIterator and return it.
     */
    public Iterator<E> iterator() {
        MyListIterator list = new MyListIterator();
        return list;
    }
}