/**
 * Represents a person with attributes such as name, age, height, VIP status, and wallet.
 * 
 * @author davidsage
 */

public class Person {
    private static int idCounter = 0; // counts the number of person objects for id assignement

    int id;
    boolean inQueue;
    boolean vip;
    String name;
    int age;
    int height;
    Wallet wallet;

    public Person(String name, boolean vip) {
        idCounter++;
        id = idCounter;
        this.name = name;
        this.vip = vip;
        this.inQueue = false;
        this.age = 0;
        this.height = 0;
        this.wallet = null;
    }

    public Person(String name, boolean vip, int age, int height) {
        idCounter++;
        id = idCounter;
        this.name = name;
        this.vip = vip;
        this.inQueue = false;
        this.age = age;
        this.height = height;
        this.wallet = null;
    }

    public Person(String name) {
        idCounter++;
        id = idCounter;
        this.name = name;
        this.vip = false;
        this.inQueue = false;
        this.age = 0;
        this.height = 0;
        this.wallet = null;
    }

    public Person(String name, boolean vip, int age, int height, int cash, int tickets) {
        idCounter++;
        id = idCounter;
        this.name = name;
        this.vip = vip;
        this.inQueue = false;
        this.age = age;
        this.height = height;
        this.wallet = new Wallet(cash, tickets);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVip() {
        return vip;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }

    public Wallet getWallet() {
        return wallet;
    }
    
}
