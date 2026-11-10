public class Wallet {
    private int balance;
    private int tickets;

    public Wallet(int balance, int tickets) {
        this.balance = balance;
        this.tickets = tickets;
    }

    public int getCashBalance() {
        return balance;
    }

    public int getTicketBalance() {
        return tickets;
    }

    public void addCash(int amount) {
        balance += amount;
    }

    public boolean buyTickets(int count, double pricePerTicket) {
        double totalCost = count * pricePerTicket;
        if (balance >= totalCost) {
            balance -= totalCost;
            tickets += count;
            return true;
        } else {
            return false; // Not enough balance to buy tickets
        }
    }

    public boolean deductTickets(int count) {
        if (tickets >= count) {
            tickets -= count;
            return true;
        } else {
            return false; // Not enough tickets to deduct
        }
    }
}
