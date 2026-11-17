import java.util.Scanner;

/**
 * View of the program
 * 
 * @author davidsage
 */


public class MainMenu {
    
    private Scanner inputScanner = new Scanner(System.in);
    private ParkController controller;

    // ---------------------------------------------------------------
    // Main loop
    // ---------------------------------------------------------------

    public void start(ParkController controller) {
        this.controller = controller;
        displayWelcome();
        if (!setUpGuest()) {
            return;
        }

        boolean running = true;
        while (running) {
            displayMenu();
            String choice = promptMenuChoice();
            if (choice == null) {
                break;
            }
            switch (choice) {
                case "1":
                    String count = promptTicketCount(controller.getTicketPrice());
                    if (count != null) {
                        controller.handleBuyTickets(count);
                    }
                    break;
                case "2":
                    String joinName = promptRideName(controller.getRideNames());
                    if (joinName != null) {
                        controller.handleJoinQueue(joinName);
                    }
                    break;
                case "3":
                    String runName = promptRideName(controller.getRideNames());
                    if (runName != null) {
                        controller.handleRunRide(runName);
                    }
                    break;
                case "4":
                    controller.handleViewStatus();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    displayError("Please enter a number from 1 to 5.");
            }
        }
        displayMessage("Thanks for visiting. Goodbye!");
    }


    private boolean setUpGuest() {
        String name = promptGuestName();
        if (name == null) {
            return false;
        }
        if (name.isEmpty()) {
            name = "Guest";
        }
        int age = -1;
        while (age < 0) {
            String text = promptAge();
            if (text == null) {
                return false;
            }
            age = controller.parsePositiveInt(text);
        }
        int height = -1;
        while (height < 0) {
            String text = promptHeight();
            if (text == null) {
                return false;
            }
            height = controller.parsePositiveInt(text);
        }
        String vip = promptVip();
        if (vip == null) {
            return false;
        }

        controller.createGuest(name, age, height, vip);
        return true;
    }

    // ---------------------------------------------------------------
    // Output
    // ---------------------------------------------------------------

    public void displayWelcome() {
        System.out.println("==============================");
        System.out.println("   WELCOME TO THE THEME PARK  ");
        System.out.println("==============================");
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("1. Buy Tickets");
        System.out.println("2. Join Queue");
        System.out.println("3. Run Ride");
        System.out.println("4. View Status");
        System.out.println("5. Exit");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String error) {
        System.out.println("[ERROR] " + error);
    }

    public void displayRideStatus(String rideName, int queueLength) {
        System.out.println(rideName + " - " + queueLength + " waiting");
    }

    public void displayRidersDispatched(String rideName, String[] riderNames) {
        if (riderNames.length == 0) {
            System.out.println(rideName + " ran empty. No riders were in line.");
            return;
        }
        System.out.println(rideName + " is departing with:");
        for (String name : riderNames) {
            System.out.println("  - " + name);
        }
    }

    // ---------------------------------------------------------------
    // Input
    // ---------------------------------------------------------------

    public String promptMenuChoice() {
        return prompt("Choose an option: ");
    }

    public String promptGuestName() {
        return prompt("What is your name? ");
    }

    public String promptAge() {
        return prompt("How old are you? ");
    }

    public String promptHeight() {
        return prompt("What is your height in inches? ");
    }

    public String promptVip() {
        return prompt("VIP guest? (y/n): ");
    }

    public String promptRideName(String[] rideNames) {
        System.out.println("Available rides: " + String.join(", ", rideNames));
        return prompt("Which ride? ");
    }

    public String promptTicketCount(double pricePerTicket) {
        System.out.println(String.format("Tickets cost $%.2f each.", pricePerTicket));
        return prompt("How many tickets? ");
    }

    private String prompt(String text) {
        System.out.print(text);
        if (!inputScanner.hasNextLine()) {
            System.out.println();
            return null;
        }
        return inputScanner.nextLine().trim();
    }

    public static void main(String[] args) {
        MainMenu view = new MainMenu();
        ParkController controller = new ParkController(view);
        view.start(controller);
    }
}
