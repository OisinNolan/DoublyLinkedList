import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author
 *  @version 09/10/18 11:13:22
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data:
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 *
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;

        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode)
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList()
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: θ(1)
     *
     * Justification:
     *  This will always be θ(1) because there is a maximum of 2 comparisons
     */
     public boolean isEmpty()
    {
    		if(head == null && tail == null) {
    			return true;
    		}
      return false;
    }

    /**
     * Worst-case asymptotic running time cost: θ(N)
     *
     * Justification:
     * This will always have a running time of N because it always loops through each N
     * elements
     * **/

    public int getSize() {
    		int size = 0;
    		DLLNode currentNode = head;
    		while(currentNode != null) {
    			size++;
    			currentNode = currentNode.next;
    		}
    		return size;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: O(N)
     *
     * Justification:
     *  This has a worst case running time of O(N) because in the case that pos == getSize() it
     *  will loop through each N elements one time, however it is possible for pos < getSize()
     *  in which case the runtime would decrease.
     */
    public void insertBefore( int pos, T data )
    {
      DLLNode newNode, prevNode, nextNode, currentNode;
      if(isEmpty()) {
    	  	newNode = new DLLNode(data, null, null);
    	  	head = newNode;
    	  	tail = newNode;
      } else if(pos <= 0) {
    	  	nextNode = head;
    	  	newNode = new DLLNode(data, null, head);
    	  	nextNode.prev = newNode;
    	  	head = newNode;
      } else if(pos >= getSize()) {
    	  	prevNode = tail;
    	  	newNode = new DLLNode(data, prevNode, null);
    	  	tail = newNode;
    	  	prevNode.next = newNode;
      } else {
    	  	currentNode = head;
    	  	nextNode = currentNode.next;
    	  	for(int i=0; i<=pos; i++) {
    	  		if(i == pos-1) {
    	  			break;
    	  		}
    	  		currentNode = currentNode.next;
    	  		nextNode = nextNode.next;
    	  	}
    	  	newNode = new DLLNode(data, currentNode, nextNode);
    	  	nextNode.prev = newNode;
    	  	currentNode.next = newNode;
      }
      return;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: O(N)
     *
     * Justification:
     *  In the worst case, where pos == getSize(), the function will loop through each N
     *  elements, creating a runtime of O(N)
     *
     */
    public T get(int pos)
    {
    	  if(isEmpty()) {
   		  return null;
   	  } else if(pos < 0) {
    		  return null;
    	  } else if(pos >= getSize()) {
    		  return null;
    	  }
    	  DLLNode currentNode = head;
    	  for(int i=1; i<=pos; i++) {
    		  currentNode = currentNode.next;
    	  }
    	  return currentNode.data;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified.
     *
     * Worst-case asymptotic running time cost: O(N)
     *
     * Justification:
     *  The worst case running time here will be O(N) as if pos == getSize() then the function
     *  will loop through exactly N nodes.
     */
    public boolean deleteAt(int pos) {
        int s = getSize();
    	  if(pos < 0 || isEmpty()) {
    		  return false;
    	  } else if(pos == 0 && s==1) {
    		  pop();
    		  head = null;
    		  tail = null;
    		  return true;
    	  } else if(pos == 0) {
    		  pop();
    		  return true;
    	  } else if(pos <= getSize()){
	      DLLNode currentNode = head;
	      for(int i=0; i<=pos; i++) {
	    	  	if(i == pos) {
	    	  		break;
	    	  	}
	    	  	currentNode = currentNode.next;
	      }
	      if(currentNode != null) {
		      DLLNode prevNode = currentNode.prev;
		      DLLNode nextNode = currentNode.next;
		      prevNode.next = nextNode;
		      if(nextNode != null) {
		    	  	nextNode.prev = prevNode;
		      }
		      return true;
	      }
    	  }
    	  return false;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: θ(N)
     *
     * Justification:
     *  This function will always loop through exactly N nodes, as it must
     *  to make sufficient changes to each, making the running time θ(N)
     */
    public void reverse()
    {
    		//swap head and tail
    		DLLNode temp = head;
    		head = tail;
    		tail = temp;
    		//foreach node, swap prev and next
    		DLLNode currentNode = head;
    		while(currentNode != null) {
    			DLLNode prevNode = currentNode.prev;
    			currentNode.prev = currentNode.next;
    			currentNode.next = prevNode;
    			currentNode = currentNode.next;
    		}
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: O(N^2)
     *
     * Justification:
     *  This will be at worstO(N^2) as the function must compare each node with all proceeding
     *  nodes in the set, effectively looping through the entire list N times in the worst case.
     *  O(N^3) can be avoided by using the fact that the index of the node to be deleted is
     *  already stored in j and so does not need to be passed as pos into another loop.
     */
     public void makeUnique()
    {
    	 	//compare each element with all remaining elements
    	 	//if same, remove element
    	 	DLLNode currentNode = head;
    	 	DLLNode compareNode, prevNode, nextNode;
    	 	int size = getSize();
    	 	for(int i=0; i<size; i++) {
    	 		compareNode = currentNode.next;
    	 		for(int j=i+1; j<size; j++) {
    	 			if(currentNode.data == compareNode.data || currentNode.data.equals(compareNode.data)) {
    	 				prevNode = compareNode.prev;
    	 			    nextNode = compareNode.next;
    	 			    prevNode.next = nextNode;
    	 			    if(nextNode != null) {
    	 			    		nextNode.prev = prevNode;
    	 			    }
    	 				j--;
    	 				size--;
    	 			}
    	 			compareNode = compareNode.next;
    	 		}
    	 		currentNode = currentNode.next;
    	 	}
    }


    /*----------------------- STACK API
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: θ(1)
     *
     * Justification:
     *  This makes a comparison and is not related to the number of nodes already in the list
     */
    public void push(T item)
    {
    		if(isEmpty()) {
    			DLLNode newNode = new DLLNode(item, null, null);
    			head = newNode;
    			tail = newNode;
    		} else {
    			DLLNode nextNode = head;
        		DLLNode newNode = new DLLNode(item, null, nextNode);
        		nextNode.prev = newNode;
        		head = newNode;
    		}
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: θ(1)
     *
     * Justification:
     *  This function has nothing to do with any nodes in the list other than the
     *  most recently added.
     */
    public T pop()
    {
    	  if(isEmpty()) {
    		  return null;
    	  }
    	  if(head == tail) {
    		  T data = head.data;
    		  head = null;
    		  tail = null;
    		  return data;
    	  }
      DLLNode nextNode = head.next;
      DLLNode poppedNode = head;
      head = nextNode;
      if(nextNode != null) {
    	  	 nextNode.prev = head;
      }
      return poppedNode.data;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: θ(1)
     *
     * Justification:
     *  This function, similar to push, is only concerned with a single node and therefore
     *  ignorant of any other N nodes in the list.
     */
    public void enqueue(T item)
    {
		if(isEmpty()) {
			DLLNode newNode = new DLLNode(item, null, null);
			head = newNode;
			tail = newNode;
		} else {
			DLLNode nextNode = head;
    		DLLNode newNode = new DLLNode(item, null, nextNode);
    		nextNode.prev = newNode;
    		head = newNode;
		}
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: θ(1)
     *
     * Justification:
     *  similar to pop(), this function is only concerned with a single node.
     */
    public T dequeue()
    {
    		if(isEmpty()) {
    			return null;
    		} else if(head == tail) {
  		  T data = head.data;
  		  head = null;
  		  tail = null;
  		  return data;
      	}
    		DLLNode prevNode = tail.prev;
    		DLLNode tailTemp = tail;
    		tail = prevNode;
    		tail.next = null;
    		return tailTemp.data;
    }


    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString()
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true;

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }
}
