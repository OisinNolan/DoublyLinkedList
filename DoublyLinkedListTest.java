import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    @Test
    public void testGet() {
    		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    		list.push(1);
    		int result = list.get(0);
    		int expectedResult = 1;
    		assertEquals("Checking if the get function returns data from node at index 0", expectedResult, result);
    		list.push(2);
    		result = list.get(1);
    		expectedResult = 1;
    		assertEquals("Checking if the get function returns data from node at index pos, where pos > 0", expectedResult, result);
    		Integer nullResult = list.get(-2);
    		Integer expectedNullResult = null;
    		assertEquals("Checking if the get function returns data from node at index pos, where pos < 0", expectedNullResult, nullResult);
    		nullResult = list.get(10);
    		assertEquals("Checking if the get function returns data from node at index pos, where pos > getSize", expectedNullResult, nullResult);
    		nullResult = list.get(0);
    }

    @Test
    public void testGetEmpty() {
    		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    		Integer nullResult = list.get(0);
    		Integer expectedNullResult = null;
    		assertEquals("Checking if the get function returns data from node at index pos, where pos > getSize", expectedNullResult, nullResult);
    }

    @Test
    public void testDeleteAt() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.push(1);
		list.push(2);
		list.push(3);
		list.deleteAt(0);
		String expectedResult = "2,1";
		assertEquals("Checking if deleteAt deletes at pos 0", expectedResult, list.toString());
		list.push(3);
		list.deleteAt(1);
		expectedResult = "3,1";
		assertEquals("Checking if deleteAt deletes at pos, where pos > 0", expectedResult, list.toString());
		boolean expected = false;
		assertEquals("Checking if deleteAt returns false if pos > size", expected, list.deleteAt(5));
		assertEquals("Checking if deleting at a pos < 0 returns false", expected, list.deleteAt(-2));
    }

    @Test
    public void testDeleteAtLastElement() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.push(1);
		list.push(2);
		list.deleteAt(1);
		boolean expected = true;
		assertEquals("Checking if deleteAt works at one last element left", expected, list.deleteAt(0));
    }

    @Test
    public void testReverse() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.push(1);
		list.push(2);
		list.push(3);
		list.reverse();
		String expectedResult = "1,2,3";
		assertEquals("Checking if reverse() reverses the function", expectedResult, list.toString());
    }

    @Test
    public void testMakeUnique() {
	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
	list.push(1);
	list.push(2);
	list.push(3);
	list.push(1);
	list.push(2);
	list.push(3);
	list.push(1);
	list.push(2);
	list.push(3);
	list.makeUnique();
	String expectedResult = "3,2,1";
	assertEquals("Checking if makeUnique makes the function elements unique", expectedResult, list.toString());

    }

    @Test
    public void testEnqueue() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    	list.enqueue(1);
    	String expectedResult = "1";
    	assertEquals("Checking if enqueue adds an element to the head of the list", expectedResult, list.toString());
    	list.enqueue(2);
    	expectedResult = "2,1";
    	assertEquals("Checking if enqueue adds a second element to the head of the list", expectedResult, list.toString());
    }

    @Test
    public void testDequeue() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    	list.enqueue(1);
    	list.enqueue(2);
    	list.enqueue(3);
    	list.dequeue();
    	String expectedResult = "3,2";
    	assertEquals("Checking if dequeue removes a node from the tail", expectedResult, list.toString());
    }

    @Test
    public void testDequeueEmpty() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    Integer result = list.dequeue();
    Integer expectedResult = null;
	assertEquals("Checking if dequeue returns null on an empty set", expectedResult, result);
    }

    @Test
    public void testPopEmpty() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    Integer result = list.pop();
    Integer expectedResult = null;
	assertEquals("Checking if dequeue returns null on an empty set", expectedResult, result);
    }

    @Test
    public void testPopOne() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    	list.push(1);
    list.pop();
    String expectedResult = "";
	assertEquals("Checking if list is null after popping last node", expectedResult, list.toString());
    }

    @Test
    public void testDequeueOne() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    	list.enqueue(1);
    list.dequeue();
    String expectedResult = "";
	assertEquals("Checking if list is null after popping last node", expectedResult, list.toString());
    }

}
