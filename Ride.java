/**
 * Represents a ride in an amusement park.
 * 
 * @author davidsage
 */

public class Ride {
    private String name;
    private int capacity;
    private Deque<Person> line;
    private int cost;
    private int minHeight;
    private int totalRidersServed;
    private int totalTicketsCollected;

    public Ride(String name, int capacity, int cost) {
        this.name = name;
        this.capacity = capacity;
        this.line = new Deque<>();
        this.cost = cost;
        minHeight = -1; // Default minimum height is -1 if not specified
        this.totalRidersServed = 0;
        this.totalTicketsCollected = 0;
    }

    
    public Ride(String name, int capacity, int cost, int minHeight) {
        this(name, capacity, cost);
        this.minHeight = minHeight;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTicketCost() {
        return cost;
    }

    public int getminHeight() {
        return minHeight;
    }

    public Deque<Person> getLine() {
        return line;
    }
    
    public int getTotalRidersServed() {
        return totalRidersServed;
    }

    public int getTotalTicketsCollected() {
        return totalTicketsCollected;
    }

    public void addPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Cannot add a null "
                + "person to the ride line.");
        }
        if (person.isVip()) {
            line.addToFront(person);
        } else {
            line.addToBack(person);
        }
    }

    public Person[] runRide() {
        int numRiders = Math.min(capacity, line.size());
        Person[] riders = new Person[numRiders];

        for (int i = 0; i < numRiders; i++) {
            riders[i] = line.removeFromFront();
        }

        totalRidersServed += numRiders;
        totalTicketsCollected += numRiders * cost;
        
        return riders;
    }

}
