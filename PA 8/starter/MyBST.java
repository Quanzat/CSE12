
/**
 * Name: Quan Tran 
 * ID: A16191839               
 * Email: qutran@ucsd.edu              
 * Sources used: Lecture      
 * 
 * TODO:
 */
import java.util.ArrayList;

/**
 * TODO:
 */
@SuppressWarnings("unchecked")
public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;

    public int size() {
        return size;
    }

    public V insert(K key, V value) {
        MyBSTNode<K, V> currNode = root;
        MyBSTNode<K, V> toInsert = new MyBSTNode<K, V>(key, value, currNode);

        if (key == null)
            throw new NullPointerException();

        if (root == null) {
            root = new MyBSTNode<K, V>(key, value, null);
            size++;
        }

        while (currNode != null) {
            K currKey = currNode.getKey();
            V currVal = currNode.getValue();
            V toReturn = null;
            int toCompare = key.compareTo(currKey);

            if (toCompare == 0) {
                toReturn = currVal;
                currNode.setValue(value);
                return toReturn;
            }

            else if (toCompare > 0) {
                if (currNode.getRight() == null) {
                    currNode.setRight(toInsert);
                    size++;
                    return null;
                }
                
                else {
                    currNode = currNode.getRight();
                }
            }

            else {
                if (currNode.getLeft() == null) {
                    currNode.setLeft(toInsert);
                    size++;
                    return null;
                }

                else {
                    currNode = currNode.getLeft();
                }
            }
        }
        return null;
    }

    public V search(K key) {
        if (key == null) {
            return null;
        }

        MyBSTNode<K, V> currNode = root;

        while (currNode != null) {
            K currKey = currNode.getKey();
            V currVal = currNode.getValue();
            int toCompare = key.compareTo(currKey);

            if (toCompare == 0) {
                return currVal;
            }

            else if (toCompare > 0) {
                currNode = currNode.getRight();
            }

            else {
                currNode = currNode.getLeft();
            }
        }
        return null;
    }

    private MyBSTNode nodeSearch(MyBSTNode currNode, K Key) {
        if (currNode == null) {
            return null;
        }

        while (currNode != null) {
            K currKey = (K) currNode.getKey();

            if (currKey.equals(Key)) {
                return currNode;
            }
            if (currKey.compareTo(Key) < 0) {
                currNode = currNode.getRight();
            }

            else {
                currNode = currNode.getLeft();
            }
        }
        return null;
    }

    public V remove(K key) {    

        if (key == null) {
            return null;
        }

        MyBSTNode<K, V> currNode = root;
        MyBSTNode<K, V> removeNode = nodeSearch(currNode, key);

        if (removeNode != null) {
            MyBST.MyBSTNode<K, V> removeParent = removeNode.getParent();
            K removeKey = removeNode.getKey();
            V removeVal = removeNode.getValue();

            if (removeNode.getRight() == null && removeNode.getLeft() == null) {
                if (removeKey == root.key) {
                    this.root = null;
                }

                else {
                    if (removeParent.getRight() == removeNode) {
                        removeParent.setRight(null);
                    }

                    else {
                        removeParent.setLeft(null);
                    }
                }
            }

            else if (removeNode.getRight() != null && removeNode.getLeft() == null) {
                currNode = removeNode.getRight();;
                currNode.setParent(null);
                removeParent.setRight(currNode);
            }

            else if (removeNode.getRight() == null && removeNode.getLeft() != null) {
                currNode = removeNode.getLeft();
                ;
                currNode.setParent(null);
                removeParent.setRight(currNode);
            }

            else {
                MyBST.MyBSTNode<K, V> removeSuccessor = removeNode.successor();
                MyBST.MyBSTNode<K, V> successorParent = removeSuccessor.getParent();

                K successorKey = removeSuccessor.getKey();
                V successorVal = removeSuccessor.getValue();

                removeNode.setKey(successorKey);
                removeNode.setValue(successorVal);

                if (successorParent.getRight() == removeSuccessor) {
                    successorParent.setRight(null);
                }
                else {
                    successorParent.setLeft(null);
                }
            }
            size--;
            return removeVal;
        }
        return null;
    }

    public ArrayList<MyBSTNode<K, V>> inorder() {
        MyBSTNode<K, V> currNode = root;
        ArrayList<MyBSTNode<K, V>> list = new ArrayList<>();

        if (size == 0) {
            return list;
        }

        while (currNode.getLeft() != null) {
            currNode = currNode.getLeft();
        }

        for (int i = 0; i < size; i++){
            list.add(currNode);
            currNode = currNode.successor();
        }
        return list;
    }

    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * 
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * 
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey() {
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * 
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue() {
            return value;
        }

        /**
         * Return the parent
         * 
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Return the left child
         * 
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Return the right child
         * 
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * 
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * 
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Set the parent
         * 
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Set the left child
         * 
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Set the right child
         * 
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * This method returns the in order successor of current node object. It
         * can be served as a helper method when implementing inorder().
         * 
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor() {
            // If there is a right subtree, then curr is the right child
            if (this.getRight() != null) {
                MyBSTNode<K, V> curr = this.getRight();

                // While there is a left child of the curr node, curr is set the
                // left child as it continously move down the tree
                while (curr.getLeft() != null) {
                    curr = curr.getLeft();
                }
                // Return the last curr node when the loop end as the successor
                return curr;
            }
            // Otherwise:
            else {
                // get the parent of the node and initialized curr
                MyBSTNode<K, V> parent = this.getParent();
                MyBSTNode<K, V> curr = this;

                // While parent is not null and curr is the right child of the
                // parent, keep moving curr up the tree
                while (parent != null && curr == parent.getRight()) {
                    curr = parent;
                    parent = parent.getParent();
                }
                // return the last parent when the loop end as the successor
                return parent;

            }
        }

        /**
         * This method returns the in order predecessor of current node object.
         * It can be served as a helper method when implementing inorder().
         * 
         * @return the predecessor of current node object
         */
        public MyBSTNode<K, V> predecessor() {
            if (this.getLeft() != null)

            {
                MyBSTNode<K, V> curr = this.getLeft();

                if (curr.getRight() == null) {
                    curr = getLeft();
                }

                else {
                    while (curr.getRight() != null) {
                        curr = curr.getRight();
                    }
                }
                return curr;
            }

            else {
                MyBSTNode<K, V> parent = this.getParent();
                MyBSTNode<K, V> curr = this;

                while (parent != null && curr == parent.getLeft()) {
                    curr = parent;
                    parent = parent.getParent();
                }
                // return the last parent when the loop end as the successor
                return parent;
            }

        }

        /**
         * This method compares if two node objects are equal.
         * 
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null
                    : this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null
                            : this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * 
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}