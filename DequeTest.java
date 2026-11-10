import junit.framework.TestCase;

/**
 * Tests the Deque class
 * 
 * @author nanaababio
 */
public class DequeTest
    extends TestCase
{
    //~ Fields ................................................................
    private Deque<Person> deque;
    private Person guest1;
    private Person guest2;
    private Person guest3;

    //~Public  Methods ........................................................
    public void setUp()
    {
        deque = new Deque<Person>();
        guest1 = new Person("David", false);
        guest2 = new Person("Miguel", false);
        guest3 = new Person("Nana", true);
    }
    
    /**
     * tests if a new deque is empty
     */
    public void testNewDequeIsEmpty()
    {
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }
    
    /**
     * tests adding a guest to back of deque
     */
    public void testAddToBack()
    {
        deque.addToBack(guest1);
        deque.addToBack(guest2);

        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());
    }
    
    /**
     * tests adding a guest to front of deque
     */
    public void testAddToFront()
    {
        deque.addToFront(guest1);
        deque.addToFront(guest2);

        assertEquals(guest2, deque.removeFromFront());
        assertEquals(guest1, deque.removeFromFront());
    }
    
    /**
     * tests that vip guests are added to the front
     */
    public void testVip()
    {
        deque.addToBack(guest1);
        deque.addToBack(guest2);
        deque.addToFront(guest3);

        assertEquals(guest3, deque.removeFromFront());
        assertEquals(guest1, deque.removeFromFront());
        assertEquals(guest2, deque.removeFromFront());
    }
    /**
     * tests removing a guest from front of deque
     */
    public void testRemoveFromFront()
    {
        deque.addToBack(guest1);
        deque.addToBack(guest2);

        assertEquals(guest1, deque.removeFromFront());
        assertEquals(1, deque.size());
    }

    /**
     * tests removing a guest from back of deque
     */
    public void testRemoveFromBack()
    {
        deque.addToBack(guest1);
        deque.addToBack(guest2);

        assertEquals(guest2, deque.removeFromBack());
        assertEquals(1, deque.size());
    }

    /**
     * tests finding a guest in the deque
     */
    public void testContains()
    {
        deque.addToBack(guest1);

        assertTrue(deque.contains(guest1));
        assertFalse(deque.contains(guest2));
    }

    /**
     * tests removing the only element from deque
     */
    public void testRemoveOnlyElement()
    {
        deque.addToBack(guest1);

        assertEquals(guest1, deque.removeFromFront());
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    /**
     * tests removing from an empty deque
     */
    public void testRemoveFromEmptyDeque()
    {
        try
        {
            deque.removeFromFront();
            fail("Expected IllegalStateException");
        }
        catch (IllegalStateException e)
        {
            assertNotNull(e);
        }
    }
    
    /**
     * tests adding and removing a null element
     */
    public void testAddAndRemoveNull()
    {
        deque.addToBack(null);

        assertEquals(1, deque.size());
        assertNull(deque.removeFromFront());
        assertTrue(deque.isEmpty());
    }

    /**
     * tests removing the first occurrence of duplicates
     */
    public void testRemoveDuplicateElements()
    {
        deque.addToBack("A");
        deque.addToBack("B");
        deque.addToBack("A");

        assertTrue(deque.remove("A"));
        assertEquals(2, deque.size());
        assertEquals("B", deque.removeFromFront());
        assertEquals("A", deque.removeFromFront());
    }

    /**
     * tests adding and removing multiple elements
     */
    public void testMultipleAddAndRemove()
    {
        deque.addToFront("A");
        deque.addToBack("B");
        deque.addToFront("C");

        assertEquals(3, deque.size());
        assertEquals("C", deque.removeFromFront());
        assertEquals("B", deque.removeFromBack());
        assertEquals("A", deque.removeFromFront());

        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
    }

    /**
     * tests contains after removing an element
     */
    public void testContainsAfterRemove()
    {
        deque.addToBack("A");
        deque.addToBack("B");

        deque.remove("A");

        assertFalse(deque.contains("A"));
        assertTrue(deque.contains("B"));
    }


}
