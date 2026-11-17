/**
 * Incomplete view of model
 * 
 * @author davidsage
 */


public class MainMenu {
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
        System.out.println("5. View Ride Analytics");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
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

    public void displayRideAnalytics(String rideName, int ridersServed, int ticketsCollected, double dollarValue) {
        System.out.printf("%-16s | Riders Served: %-3d | Tickets Collected: %-4d ($%.2f)%n",
            rideName, ridersServed, ticketsCollected, dollarValue);
    }

    public void displayAnalyticsSummary(int totalRiders, int totalTickets, double totalRevenue) {
        System.out.println("------------------------------------------------------------------");
        System.out.printf("PARK TOTALS      | Riders Served: %-3d | Tickets Collected: %-4d ($%.2f)%n",
            totalRiders, totalTickets, totalRevenue);
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

    public static void main(String[] args) {
        new ParkController(new MainMenu()).start();
    }
}
