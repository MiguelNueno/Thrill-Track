import junit.framework.TestCase;

/**
 *  Tests the Ride class
 * 
 *  @author nanaababio
 *  @version Nov 10, 2026
 */
public class RideTest
    extends TestCase
{
    //~ Fields ................................................................
    private Ride ride;

    //~Public  Methods ........................................................
    public void setUp()
    {
        ride = new Ride("Thunder Coaster", 3, 5);
    }


    /**
     * tests constructor 1
     */
    public void testConstructor()
    {
        assertEquals("Thunder Coaster", ride.getName());
        assertEquals(3, ride.getCapacity());
        assertEquals(5, ride.getTicketCost());
        assertEquals(-1, ride.getminHeight());
        assertEquals(0, ride.getLine().size());
    }

    /**
     * tests constructor 2
     */
    public void testConstructorWithMinimumHeight()
    {
        Ride smallRide = new Ride("Kiddie Coaster", 5, 2, 36);

        assertEquals("Kiddie Coaster", smallRide.getName());
        assertEquals(5, smallRide.getCapacity());
        assertEquals(2, smallRide.getTicketCost());
        assertEquals(36, smallRide.getminHeight());
        assertEquals(0, smallRide.getLine().size());
    }

    /**
     * tests running a ride with an empty line
     */
    public void testRunRideEmptyLine()
    {
        Person[] riders = ride.runRide();

        assertEquals(0, riders.length);
        assertEquals(0, ride.getLine().size());
    }

    /**
     * tests running a ride with zero capacity
     */
    public void testRunRideZeroCapacity()
    {
        Ride smallRide = new Ride("Closed", 0, 5);

        Person[] riders = smallRide.runRide();

        assertEquals(0, riders.length);
        assertEquals(0, smallRide.getLine().size());
    }
    
    /**
     * tests adding a regular person to the ride
     */
    public void testAddPerson()
    {
        Person person = new Person("Auburn");

        ride.addPerson(person);

        assertEquals(1, ride.getLine().size());
        assertEquals(person, ride.getLine().removeFromFront());
    }

    /**
     * tests adding multiple regular people
     */
    public void testAddMultiplePeople()
    {
        Person person1 = new Person("Auburn");
        Person person2 = new Person("Isabella");

        ride.addPerson(person1);
        ride.addPerson(person2);

        assertEquals(2, ride.getLine().size());
        assertEquals(person1, ride.getLine().removeFromFront());
        assertEquals(person2, ride.getLine().removeFromFront());
    }

    /**
     * tests that VIP people are added to the front
     */
    public void testAddVipPerson()
    {
        Person regular = new Person("Valeria", false);
        Person vip = new Person("Isabella", true);

        ride.addPerson(regular);
        ride.addPerson(vip);

        assertEquals(2, ride.getLine().size());
        assertEquals(vip, ride.getLine().removeFromFront());
        assertEquals(regular, ride.getLine().removeFromFront());
    }

    /**
     * Tests that multiple VIP people are added to the front
     */
    public void testAddMultipleVipPeople()
    {
        Person regular = new Person("Auburn", false);
        Person vip1 = new Person("Valeria", true);
        Person vip2 = new Person("Isabella", true);

        ride.addPerson(regular);
        ride.addPerson(vip1);
        ride.addPerson(vip2);

        assertEquals(3, ride.getLine().size());
        assertEquals(vip2, ride.getLine().removeFromFront());
        assertEquals(vip1, ride.getLine().removeFromFront());
        assertEquals(regular, ride.getLine().removeFromFront());
    }
    
    

}
