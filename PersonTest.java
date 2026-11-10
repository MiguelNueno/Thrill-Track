import junit.framework.TestCase;

/**
 *  Tests the Person class
 * 
 *  @author nanaababio
 *  @version Nov 9, 2026
 */
public class PersonTest
    extends TestCase
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    
    /**
     * tests the basic person constructor
     */
    public void testBasicConstructor()
    {
        Person person = new Person("Auburn");

        assertTrue(person.getId() > 0);
        assertEquals("Auburn", person.getName());
        assertEquals(0, person.getAge());
        assertEquals(0, person.getHeight());
        assertFalse(person.isVip());
        assertFalse(person.isInQueue());
        assertNull(person.getWallet());
    }

    /**
     * tests creating a person with VIP status
     */
    public void testConstructorWithVip()
    {
        Person person = new Person("Valeria", true);

        assertEquals("Valeria", person.getName());
        assertTrue(person.isVip());
    }

    /**
     * tests setting a person's age and height through the constructor
     */
    public void testConstructorWithAgeAndHeight()
    {
        Person person = new Person("Isabella", false, 20, 68);

        assertEquals(20, person.getAge());
        assertEquals(68, person.getHeight());
        assertFalse(person.isVip());
    }

    /**
     * tests changing a person's queue status
     */
    public void testQueueStatus()
    {
        Person person = new Person("Auburn");

        assertFalse(person.isInQueue());

        person.setInQueue(true);
        assertTrue(person.isInQueue());

        person.setInQueue(false);
        assertFalse(person.isInQueue());
    }

    /**
     * tests that a person can be created with a wallet
     */
    public void testWalletConstructor()
    {
        Person person = new Person("Valeria", true, 20, 68, 25, 10);

        assertNotNull(person.getWallet());
        assertEquals(25, person.getWallet().getCashBalance());
        assertEquals(10, person.getWallet().getTicketBalance());
    }

    /**
     * tests that two different people receive different IDs
     */
    public void testDifferentPeopleHaveDifferentIds()
    {
        Person person1 = new Person("Auburn");
        Person person2 = new Person("Isabella");

        assertTrue(person1.getId() != person2.getId());
    }
    /**
     * tests a person with a null name
     */
    public void testPersonWithNullName()
    {
        Person person = new Person(null);
    
        assertNull(person.getName());
        assertFalse(person.isVip());
    }

    /**
     * tests a person with an empty wallet
     */
    public void testPersonWithEmptyWallet()
    {
        Person person = new Person("Valeria", false, 18, 170, 0, 0);
    
        assertEquals(0, person.getWallet().getCashBalance());
        assertEquals(0, person.getWallet().getTicketBalance());
    }
}
