/**
 * Name: Quan Tran
 * Email: qutran@ucsd.ued
 * Sources used: Lecture, Piazza, CSE 12 Discord
 * 
 * This is the implementation file for MyLinkedList. It contains all 
 * a nested class which contain a Node clas that provide and references 
 * to previous and next Node. It also contains serveral methods 
 * for MyLinkList.  
 */

import java.util.AbstractList;

/** 
 * A nested class that include a class for node and many methods 
 * that implement MyLinkedList. 
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	int i;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous 
	 * and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
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
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		head = new Node(null);
        tail = new Node(null);
		head.next =  tail;
		tail.prev = head;
		size = 0;
	}

	@Override
	/** 
	 * Return the number of nodes being stored
	 * @return this linkedlist's size
	 */
	public int size() {
		return size;
	}

	@Override
	/** 
	 * Get data within the node at position index.
	 * @param index where to get the data
	 * @return the data at specific position
	 * @throws IndexOutOfBoundsException if index is less than 0 or larger
	 * than the size of the linkedlist
	 */
	public E get(int index) {
		if ((index < 0) || (index >= size)){
			throw new IndexOutOfBoundsException();
		}

		else {
			Node curr = head;
			for (i = 0; i <= index; i++) {
				curr = curr.getNext();
			}

			E data = curr.getElement();
			return data; 
		}
	}

	@Override
	/** 
	 * Add a node into this list by index. The input index can be any 
	 * integer in between zero and the number of elements (inclusive on 
	 * both ends).
	 * @param index where to add the data
	 * @param data the data to add
	 * @throws NullPointerException if data is null
	 * @throws IndexOutOfBoundsException if index is less than 0 or larger
	 * than the size of the linkedlist
	 */
	public void add(int index, E data) {
		if (data == null){
			throw new NullPointerException();
		}

		else if ((index < 0) || (index > size)){
			throw new IndexOutOfBoundsException();
        } 

		else if ((size == 0) || (index == size)){
			add(data);
		}

		else {
			
            Node inputData = new Node(data);
			Node prevNode = head;
			for (i = 0; i <= index; i++) {
				prevNode = prevNode.getNext();
			}
			Node nextNode = prevNode.getNext();
			inputData.setNext(nextNode);
			inputData.setPrev(prevNode);
			nextNode.setPrev(inputData);
			prevNode.setNext(inputData);
			size++;
		}
    }

	/**
	 * 
	 */
	/** 
	 * Add a node at the end into this list
	 * @param data the data to add
	 * @return true always
	 * @throws NullPointerException if data is null
	 */
	public boolean add(E data) {
		if (data == null){
			throw new NullPointerException();
		}

 		else {
            Node inputData = new Node(data);
            if (size == 0) {
                head.setNext(inputData);
                inputData.setPrev(head);
                inputData.setNext(tail);
                tail.setPrev(inputData);
            } 

			else {
				Node prevNode = head;
				for (i = 0; i < size; i++) {
					prevNode = prevNode.getNext();
                }

                prevNode.setNext(inputData);
                inputData.setPrev(prevNode);
                inputData.setNext(tail);
                tail.setPrev(inputData);
            }
            size++;
        }
        return true; 
	}


	/** 
	 * Set the element for the node at index to data and return the element 
	 * that was previously stored in this node.
	 * @param index where to set the data
	 * @param data the data to set
	 * @return the element before getting replaced
	 * @throws NullPointerException if data is null
	 * @throws IndexOutOfBoundsException if index is less than 0 or bigger 
	 * or equal to the size of the linkedlist
	 */
	public E set(int index, E data) {
		if (data == null){
			throw new NullPointerException();
		}

		if ((index < 0) || (index >= size)){
			throw new IndexOutOfBoundsException();
		}

		else {
			Node curr = head;
			for (int i = 0; i <= index; i++) {
				curr = curr.getNext();
			}
			curr.setElement(data);
		}

		return this.get(index); 
	}

	/** 
	 * Remove the node from the position index in this list and return the 
	 * data within the removed node.
	 * @param index where to remove the data
	 * @return the element that was removed
	 * @throws IndexOutOfBoundsException if index is less than 0 or bigger 
	 * or equal to the size of the linkedlist
	 */
	public E remove(int index) {
		if ((index < 0) || (index >= size)){
			throw new IndexOutOfBoundsException();
		}

		else if (size == 1){
			clear();
			return head.getElement();
        } 
		
		else if (index == 0){
			head = head.getNext();
			size--;

			return head.getElement();

		}

		else {
			Node prevNode = head;
			for (i = 1; i <= index; i++)
			{
				prevNode = prevNode.getNext();
			}

			Node nextNode = prevNode.getNext().getNext();
			Node removedNode = prevNode.getNext();
			prevNode.setNext(nextNode);
			tail.setPrev(prevNode);

		size--;
		return removedNode.getElement();

		}
	}

	/** 
	 * Remove all nodes from the list
	 */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/** 
	 * Determine if the list is empty
	 */
	public boolean isEmpty() {
		if (size == 0){
			return true;
		}
		return false;
	}

	/** 
	 * A helper method that returns the Node at a specified index,
	 * not the content.
	 * @param index where to get the data
	 * @throws IndexOutOfBoundsException if index is less than 0 or bigger 
	 * or equal to the size of the linkedlist
	 */
	protected Node getNth(int index) {
		if ((index < 0) || (index >= size)){
			throw new IndexOutOfBoundsException();
		}

		Node curr = head;
		for (i = 0; i < size; i++){
			curr = curr.getNext();
			if (i == index){
				return curr;
			}
		}
		return curr;
	}
}


