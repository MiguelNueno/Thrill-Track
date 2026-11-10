import java.util.Scanner;


/**
 * Incomplete controller of model
 * 
 * 
 * @author davidsaage
 */
public class ParkController {
     
    private static final double TICKET_PRICE = 2.00;
    private static final int STARTING_CASH = 50;
 
    private Person activeGuest;
    private Ride[] availableRides;
    private MainMenu view;
    private Scanner inputScanner;
 
    public ParkController(MainMenu view) {
        this.view = view;
        this.inputScanner = new Scanner(System.in);
        this.availableRides = createRides();
        seedBackgroundGuests();
    }
 
    // ---------------------------------------------------------------
    // Main loop
    // ---------------------------------------------------------------
 
    public void start() {
        view.displayWelcome();
        activeGuest = createActiveGuest();
        if (activeGuest == null) {
            return;
        }
 
        boolean running = true;
        while (running) {
            view.displayMenu();
            String line = readLine();
            if (line == null) {
                break;
            }
            switch (line.trim()) {
                case "1":
                    promptBuyTickets();
                    break;
                case "2":
                    view.displayMessage("Which ride?");
                    showRideNames();
                    String joinName = readLine();
                    if (joinName != null) handleJoinQueue(joinName.trim());
                    break;
                case "3":
                    view.displayMessage("Which ride?");
                    showRideNames();
                    String runName = readLine();
                    if (runName != null) handleRunRide(runName.trim());
                    break;
                case "4":
                    showStatus();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    view.displayError("Please enter a number from 1 to 5.");
            }
        }
        view.displayMessage("Thanks for visiting. Goodbye!");
    }
 
    // ---------------------------------------------------------------
    // Handlers
    // ---------------------------------------------------------------
 
    public void handleBuyTickets(int ticketCount, double pricePerTicket) {
        if (ticketCount <= 0) {
            view.displayError("Ticket count must be greater than zero.");
            return;
        }
        double total = ticketCount * pricePerTicket;
        Wallet wallet = activeGuest.getWallet();
        if (wallet.getCashBalance() < total) {
            view.displayError(String.format(
                "Not enough cash. %d tickets cost $%.2f but you have $%d.",
                ticketCount, total, wallet.getCashBalance()));
            return;
        }
        if (wallet.buyTickets(ticketCount, pricePerTicket)) {
            view.displayMessage(String.format(
                "Bought %d tickets for $%.2f. You now have %d tickets.",
                ticketCount, total, wallet.getTicketBalance()));
        } else {
            view.displayError("Purchase failed.");
        }
    }
 
    public void handleJoinQueue(String rideName) {
        Ride ride = findRide(rideName);
        if (ride == null) {
            view.displayError("No ride named \"" + rideName + "\".");
            return;
        }
        if (activeGuest.isInQueue()) {
            view.displayError("You are already in a line. Wait for that ride to run first.");
            return;
        }
        if (activeGuest.getHeight() < ride.getminHeight()) {
            view.displayError(ride.getName() + " requires a height of at least "
                + ride.getminHeight() + " inches.");
            return;
        }
        if (activeGuest.getWallet().getTicketBalance() < ride.getTicketCost()) {
            view.displayError(ride.getName() + " costs " + ride.getTicketCost()
                + " tickets but you have " + activeGuest.getWallet().getTicketBalance() + ".");
            return;
        }
        if (!activeGuest.getWallet().deductTickets(ride.getTicketCost())) {
            view.displayError("Could not deduct tickets.");
            return;
        }
        ride.addPerson(activeGuest);
        activeGuest.setInQueue(true);
        view.displayMessage("You joined the line for " + ride.getName()
            + (activeGuest.isVip() ? " at the front (VIP)." : "."));
    }
 
    public void handleRunRide(String rideName) {
        Ride ride = findRide(rideName);
        if (ride == null) {
            view.displayError("No ride named \"" + rideName + "\".");
            return;
        }
        Person[] riders = ride.runRide();
        String[] names = new String[riders.length];
        for (int i = 0; i < riders.length; i++) {
            riders[i].setInQueue(false);
            names[i] = riders[i].getName();
        }
        view.displayRidersDispatched(ride.getName(), names);
    }
 
    private Ride findRide(String name) {
        if (name == null) return null;
        for (Ride r : availableRides) {
            if (r.getName().equalsIgnoreCase(name.trim())) {
                return r;
            }
        }
        return null;
    }
 
    // ---------------------------------------------------------------
    // Input helpers
    // ---------------------------------------------------------------
 
    private void promptBuyTickets() {
        view.displayMessage(String.format("Tickets cost $%.2f each. How many?", TICKET_PRICE));
        String line = readLine();
        if (line == null) return;
        try {
            int count = Integer.parseInt(line.trim());
            handleBuyTickets(count, TICKET_PRICE);
        } catch (NumberFormatException e) {
            view.displayError("Please enter a whole number.");
        }
    }
 
    private void showStatus() {
        Wallet w = activeGuest.getWallet();
        view.displayMessage(String.format("%s: $%d cash, %d tickets%s",
            activeGuest.getName(), w.getCashBalance(), w.getTicketBalance(),
            activeGuest.isInQueue() ? " (in a line)" : ""));
        for (Ride r : availableRides) {
            view.displayRideStatus(r.getName(), r.getLine().size());
        }
    }
 
    private void showRideNames() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < availableRides.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(availableRides[i].getName());
        }
        view.displayMessage("(" + sb + ")");
    }
 
    /** Returns null if input has ended. */
    private String readLine() {
        return inputScanner.hasNextLine() ? inputScanner.nextLine() : null;
    }
 
    private int readPositiveInt(String prompt) {
        while (true) {
            view.displayMessage(prompt);
            String line = readLine();
            if (line == null) return -1;
            try {
                int n = Integer.parseInt(line.trim());
                if (n > 0) return n;
            } catch (NumberFormatException ignored) { }
            view.displayError("Please enter a positive whole number.");
        }
    }
 
    private Person createActiveGuest() {
        view.displayMessage("What is your name?");
        String name = readLine();
        if (name == null) return null;
        name = name.trim().isEmpty() ? "Guest" : name.trim();
 
        int age = readPositiveInt("How old are you?");
        if (age < 0) return null;
        int height = readPositiveInt("What is your height in inches?");
        if (height < 0) return null;
 
        view.displayMessage("VIP guest? (y/n)");
        String vip = readLine();
        if (vip == null) return null;
        boolean isVip = vip.trim().equalsIgnoreCase("y");
 
        return new Person(name, isVip, age, height, STARTING_CASH, 0);
    }
 
    // ---------------------------------------------------------------
    // Setup data
    // ---------------------------------------------------------------
 
    private Ride[] createRides() {
        return new Ride[] {
            new Ride("Roller Coaster", 4, 5, 48),
            new Ride("Carousel", 6, 2, 0),
            new Ride("Ferris Wheel", 4, 3, 36)
        };
    }
 
    /** A few other guests so the lines are not empty and runRide has something to dispatch. */
    private void seedBackgroundGuests() {
        String[] names = {"Alex", "Sam", "Jordan"};
        for (int i = 0; i < names.length; i++) {
            Person p = new Person(names[i], false, 25, 66, 0, 0);
            availableRides[i % availableRides.length].addPerson(p);
            p.setInQueue(true);
        }
    }
}
